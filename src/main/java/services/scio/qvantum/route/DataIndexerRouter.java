package services.scio.qvantum.route;

import org.apache.camel.model.dataformat.JsonLibrary;
import services.scio.qvantum.aggregator.ElasticBatchAggregationStrategy;
import services.scio.qvantum.exceptions.ExceptionHandler;
import services.scio.qvantum.exceptions.HttpOperationFailedExceptionHandler;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import services.scio.qvantum.models.DocumentMap;
import services.scio.qvantum.process.ESIndexDataProcessor;
import services.scio.qvantum.process.GetOrCreateIndex;

public class DataIndexerRouter extends RouteBuilder {

    public DataIndexerRouter() {
    }

    @Override
    public void configure() throws Exception {

        onException(HttpOperationFailedException.class)
                .handled(true)
                .process(new HttpOperationFailedExceptionHandler())
                .to("log:?level=ERROR&showAll=true&showCaughtException=true");

        onException(Exception.class)
                .handled(true)
                .process(new ExceptionHandler())
                .to("log:?level=ERROR&showAll=true&showCaughtException=true");

        from("kafka:{{KAFKA_INPUT_TOPIC}}?brokers={{KAFKA_BROKER}}" +
                "&autoOffsetReset&breakOnFirstError=false")
                .routeId("DataIndexer")
                .unmarshal().json(JsonLibrary.Jackson, DocumentMap.class)
                .process(new GetOrCreateIndex())
                .aggregate(constant(true), new ElasticBatchAggregationStrategy())
                .completionSize(3)
                .completionTimeout(60000)
                .process(new ESIndexDataProcessor())
                .log("End of batch data integration...");

    }
}
