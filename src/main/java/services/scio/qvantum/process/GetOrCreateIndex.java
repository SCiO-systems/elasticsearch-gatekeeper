package services.scio.qvantum.process;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import services.scio.qvantum.models.DocumentMap;
import services.scio.qvantum.models.IndexInfo;

public class GetOrCreateIndex implements Processor {

    public GetOrCreateIndex() {
    }

    public void process(Exchange exchange) throws Exception {

        CamelContext context = exchange.getContext();
        IndexInfo info = context.getRegistry().lookupByNameAndType("indexInfo", IndexInfo.class);
        if (info == null) {
            DocumentMap body = exchange.getIn().getBody(DocumentMap.class);
            Integer dimensions = body.getEmbeddings().size();
            String modelNAme = body.getModel();
            info = new IndexInfo(modelNAme,dimensions);
            String indexName = info.getIndexName();
            RestHighLevelClient client = context.getRegistry()
                    .lookupByNameAndType("es_client", RestHighLevelClient.class);
            CreateANewIndex(client, info);

            context.getRegistry().bind("indexName", info.getIndexName());
            context.getRegistry().bind("indexInfo", info);
        }
    }

    private void CreateANewIndex (RestHighLevelClient client, IndexInfo info) throws Exception {
        String indexName = info.getIndexName();
        if (CheckIfIndexExists(client, indexName)) {
            System.out.println("Index '" + indexName + "' already exists.");
        }
        else {
            String indexSettingsAndMappings = "{\n" +
                    "  \"mappings\": {\n" +
                    "    \"properties\": {\n" +
                    "      \"embeddings\": {\n" +
                    "        \"type\": \"dense_vector\",\n" +
                    "        \"dims\": " + info.getDims() + ",\n" +
                    "        \"index\": true,\n" +
                    "        \"similarity\": \"cosine\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            System.out.println("Creating a new index with indexName = " + indexName);
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            request.source(indexSettingsAndMappings, XContentType.JSON);
            client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println("Index created successfully.");
        }

    }

    private boolean CheckIfIndexExists (RestHighLevelClient client, String indexName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

}
