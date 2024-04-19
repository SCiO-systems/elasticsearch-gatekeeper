package services.scio.qvantum.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import services.scio.qvantum.models.ElasticIndexMapping;

import java.time.Instant;

public class GardianEsIndexRouter extends RouteBuilder {

    private final String eSConnectionString;
    private String indexName;

    public GardianEsIndexRouter(String eSConnectionString) {
        this.eSConnectionString = eSConnectionString;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public void configure() throws Exception {


        try{
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(HttpHost.create(eSConnectionString)));

            // raw_resolved_data_06022023_fair_calculate
            indexName = "test1-index";
//            indexName = "gardian_index_test";

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
//            ElasticIndexMapping mapping = new ElasticIndexMapping();


//            createIndexRequest.settings(
//                    "{" +
//                            "        \"number_of_shards\": 1, " +
//                            "        \"analysis\": {" +
//                            "            \"filter\": {" +
//                            "                \"autocomplete_filter\": { " +
//                            "                    \"type\":     \"edge_ngram\"," +
//                            "                    \"min_gram\": 2," +
//                            "                    \"max_gram\": 20" +
//                            "                }," +
//                            "                \"shingle\": {" +
//                            "                   \"type\": \"shingle\"," +
//                            "                   \"min_shingle_size\": 2," +
//                            "                   \"max_shingle_size\": 3" +
//                            "              }"+
//                            "            }," +
//                            "            \"analyzer\": {" +
//                            "                \"autocomplete\": {" +
//                            "                    \"type\":      \"custom\"," +
//                            "                    \"tokenizer\": \"standard\"," +
//                            "                    \"filter\": [" +
//                            "                        \"lowercase\"," +
//                            "                        \"autocomplete_filter\" " +
//                            "                    ]" +
//                            "                }," +
//                            "               \"trigram\": {" +
//                            "                 \"type\": \"custom\"," +
//                            "                 \"tokenizer\": \"standard\"," +
//                            "                 \"filter\": [\"lowercase\",\"shingle\"]" +
//                            "               }"+
//                            "            }" +
//                            "        }" +
//                            "    }"
//                    ,
//                    XContentType.JSON
//            );


            createIndexRequest.mapping(
                    "{" +
                            "\"properties\" : {" +
                            "\t\"embeddings\": {\n" +
                            "\t\t\"type\":\"dense_vector\",\n" +
                            "\t\t\"dims\":3,\n" +
                            "\t\t\"index\":true,\n" +
                            "\t\t\"similarity\":\"cosine\"\n" +
                            "\t}}}",
                    XContentType.JSON
            );


            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
