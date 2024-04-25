package services.scio.qvantum.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.CamelContext;
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
import services.scio.qvantum.models.IndexInfo;

import java.io.IOException;

public class ElasticBatchAggregationStrategy implements AggregationStrategy {


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        CamelContext context = newExchange.getContext();
        String indexName = context.getRegistry().lookupByNameAndType("indexName", String.class);
        IndexInfo info = context.getRegistry().lookupByNameAndType("indexInfo", IndexInfo.class);
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
//        System.out.println("In aggregation got " + info.toString());

        bulkRequest.add(indexRequest);
        newExchange.getIn().setBody(bulkRequest,BulkRequest.class);
        return newExchange;
    }

}

