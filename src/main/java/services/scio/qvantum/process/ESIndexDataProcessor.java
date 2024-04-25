package services.scio.qvantum.process;

import org.apache.camel.CamelContext;
import org.apache.camel.component.kafka.KafkaConstants;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class ESIndexDataProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        BulkRequest bulkRequest =  exchange.getIn().getBody(BulkRequest.class);
        CamelContext context = exchange.getContext();
        String indexName = context.getRegistry().lookupByNameAndType("indexName", String.class);

        RestHighLevelClient client = exchange.getContext().getRegistry()
                .lookupByNameAndType("es_client",RestHighLevelClient.class);

        Long timestamp = exchange.getIn().getHeader(KafkaConstants.TIMESTAMP, Long.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = sdf.format(new Date(timestamp));
        System.out.println("Formatted Timestamp: " + formattedTimestamp);

        try {
            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("Error in bulk request");
            e.printStackTrace();
        }
    }

}
