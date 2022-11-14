package se.replyto.camel.praktikdemo;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;


@Component
public class FirstRouteBuilder extends org.apache.camel.builder.RouteBuilder {
    private static final String SOURCE_FOLDER = "C:\\Users\\juris\\lia";
    private static final String DESTINATION_FOLDER = "C:\\Users\\juris\\lia\\out";
    private static final long DURATION_MILLIS = 10000;



    @Override
    public void configure() throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new FirstRouteBuilder() {

            public void configure() throws Exception {

                from("file:" + SOURCE_FOLDER + "?noop=true").convertBodyTo(String.class).process(exchange -> {

                    System.out.println("Headers :"+ exchange.getMessage().getHeaders());
                    System.out.println(exchange.getIn().getBody());
                    exchange.getMessage().setHeader("CamelFileName",exchange.getMessage().getHeader("CamelFileName") + ".out");
                })

                .to("file:"+ DESTINATION_FOLDER + "?fileName=${header.CamelFileName}");
            }
        });

        context.start();
        Thread.sleep(DURATION_MILLIS);

        context.stop();
    }

}

