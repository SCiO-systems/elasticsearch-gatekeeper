package services.scio.qvantum.route;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class CreateGardianAliasRouter extends RouteBuilder {

    private final String eSConnectionString;
    private final String indexName;

    public CreateGardianAliasRouter(String eSConnectionString, String indexName) {
        this.eSConnectionString = eSConnectionString;
        this.indexName = indexName;
    }

    @Override
    public void configure() throws Exception {

        from("timer://mimeTypeAlias?repeatCount=1")
                .setHeader(Exchange.HTTP_METHOD).constant("POST")
                .enrich().simple(eSConnectionString +  "/" + indexName + "/_alias/test_gatekeeper_index?bridgeEndpoint=true")
                .log("SUCCESS IN CREATING ALIAS FOR GATEKEEPER INDEX.");
    }
}
