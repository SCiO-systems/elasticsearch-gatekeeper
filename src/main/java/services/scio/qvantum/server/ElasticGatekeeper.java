package services.scio.qvantum.server;

//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.ServerApi;
//import com.mongodb.ServerApiVersion;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.camel.spi.PropertiesComponent;
import org.apache.camel.support.DefaultRegistry;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import services.scio.qvantum.route.DataIndexerRouter;
import services.scio.qvantum.route.GardianEsIndexRouter;
import org.apache.camel.CamelContext;
//import org.apache.camel.component.mongodb.MongoDbComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

public class ElasticGatekeeper extends Main {

    public ElasticGatekeeper() {
    }

    public static void main(String[] args) throws Exception {
        ElasticGatekeeper gatekeeperIndex = new ElasticGatekeeper();
//        ElasticGatekeeperOptions options = new ElasticGatekeeperOptions();

        CamelContext camelContext = new DefaultCamelContext();
//        CommandLineParser parser = new DefaultParser();
//        CommandLine cmd = parser.parse(options.createOptions(), args);

        PropertiesComponent properties = camelContext.getPropertiesComponent();
        properties.setLocation("classpath:/env.properties");


        // Elasticsearch Properties
        String esURL = properties.loadPropertiesAsMap().get("ES_URL").toString();
        int esPORT = Integer.parseInt(properties.loadPropertiesAsMap().get("ES_PORT").toString());
        String esConnectionString = esURL + ":" + esPORT;

        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost(esURL, esPORT)).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(HttpHost.create(esConnectionString)) .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder
                                .setConnectTimeout(36000000 )  //10 hours
                                .setSocketTimeout(36000000);
                    }}));

        // And create the API client
//        ElasticsearchClient client = new ElasticsearchClient(transport);

        GardianEsIndexRouter gardianEsIndexRouter = new GardianEsIndexRouter("http://dev.elasticsearch.scio.services:9200");

        DefaultRegistry registry = (DefaultRegistry) camelContext.getRegistry();
        registry.bind("es_client", client);
//        camelContext.addRoutes(gardianEsIndexRouter);

//        System.out.println(gardianEsIndexRouter.getIndexName());

        // Z3RGcTRvZ0Jfc1QwRlQ1Q05mUXQ6ZXNBa3ZxektURS1OeWZkSzBVd21tZw==

        //camelContext.addRoutes(new CreateGardianAliasRouter("http://prod.elasticsearch.scio.services:9200", gardianEsIndexRouter.getIndexName()));
//        camelContext.addRoutes(new DataIndexerRouter(esConnectionString, gardianEsIndexRouter.getIndexName()));
        camelContext.addRoutes(new DataIndexerRouter());

        camelContext.start();
        gatekeeperIndex.run();
    }
}
