package services.scio.qvantum.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import services.scio.qvantum.models.DocumentMap;

import java.io.IOException;

public class ElasticBatchAggregationStrategy implements AggregationStrategy {

    private String indexName;

    public ElasticBatchAggregationStrategy (String index) {this.indexName = index;}


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        String indexName = this.indexName;
        DocumentMap document = newExchange.getIn().getBody(DocumentMap.class);
        IndexRequest indexRequest = new IndexRequest(indexName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            indexRequest.source(mapper.writeValueAsString(document), XContentType.JSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        BulkRequest bulkRequest;

        if (oldExchange == null) {
            bulkRequest = new BulkRequest();
        }else{
            bulkRequest = oldExchange.getIn().getBody(BulkRequest.class);
        }

        bulkRequest.add(indexRequest);
        newExchange.getIn().setBody(bulkRequest,BulkRequest.class);
        return newExchange;
    }

}

