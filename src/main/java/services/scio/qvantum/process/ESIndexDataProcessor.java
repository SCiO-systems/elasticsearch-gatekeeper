package services.scio.qvantum.process;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.common.xcontent.XContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import services.scio.qvantum.models.DocumentMap;

import java.io.IOException;

public class ESIndexDataProcessor implements Processor {

    private String indexName;

    public ESIndexDataProcessor(String indexName) {
        this.indexName = indexName;
    }
    public void process(Exchange exchange) throws Exception {
        BulkRequest bulkRequest =  exchange.getIn().getBody(BulkRequest.class);

        RestHighLevelClient client = exchange.getContext().getRegistry()
                .lookupByNameAndType("es_client",RestHighLevelClient.class);

        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("Error in bulk request");
            e.printStackTrace();
        }
    }

}
