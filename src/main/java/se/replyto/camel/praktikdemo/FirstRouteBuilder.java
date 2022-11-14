package se.replyto.camel.praktikdemo;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class FirstRouteBuilder extends org.apache.camel.builder.RouteBuilder {

    @Override
    public void configure() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new FirstRouteBuilder() {
            public void configure() {
                from("file:C:\\Users\\juris\\lia?noop=true")
                        .to("file:C:\\Users\\juris\\lia\\out");
            }
        });

        context.start();
        Thread.sleep(10000);

        context.stop();
    }

}

