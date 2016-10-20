package io.vertx.reproducer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by bruce.henderson on 20/10/2016.
 */
public class Server extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create().setDeleteUploadedFilesOnEnd(true));
        router.post("/post").handler(this::post);

        server.requestHandler(router::accept).listen(8080);

    }

    private void post(RoutingContext context) {

        System.out.println("Uploaded OK");

        context.response().setStatusCode(200).end("OK");
    }
}
