package services.scio.qvantum.exceptions;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HttpOperationFailedExceptionHandler implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
//        BaseResponse baseResponse = new BaseResponse();
//
//        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
//        System.out.println("HTTP OPERATION FAILED");
////        exception.printStackTrace();
//
//        baseResponse.setCode("404");
//        baseResponse.setResponse(exception.toString());
//        exchange.getIn().setBody(baseResponse);
//        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
    }
}
