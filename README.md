Steps to reproduce :

* Run the test.

The test performs a sequence of uploads, one of which is likely to show the problem.

Here's an example of the exception :
```
Oct 20, 2016 11:41:16 AM io.vertx.core.impl.ContextImpl
SEVERE: Unhandled exception
java.lang.IllegalStateException: File handle is closed
	at io.vertx.core.file.impl.AsyncFileImpl.checkClosed(AsyncFileImpl.java:450)
	at io.vertx.core.file.impl.AsyncFileImpl.check(AsyncFileImpl.java:445)
	at io.vertx.core.file.impl.AsyncFileImpl.closeInternal(AsyncFileImpl.java:474)
	at io.vertx.core.file.impl.AsyncFileImpl.close(AsyncFileImpl.java:117)
	at io.vertx.core.http.impl.HttpServerFileUploadImpl.handleComplete(HttpServerFileUploadImpl.java:203)
	at io.vertx.core.http.impl.HttpServerFileUploadImpl.resume(HttpServerFileUploadImpl.java:128)
	at io.vertx.core.http.impl.HttpServerFileUploadImpl.resume(HttpServerFileUploadImpl.java:38)
	at io.vertx.core.streams.impl.PumpImpl.lambda$new$0(PumpImpl.java:62)
	at io.vertx.core.file.impl.AsyncFileImpl.checkDrained(AsyncFileImpl.java:285)
	at io.vertx.core.file.impl.AsyncFileImpl.lambda$doWrite$0(AsyncFileImpl.java:151)
	at io.vertx.core.file.impl.AsyncFileImpl$1.lambda$completed$0(AsyncFileImpl.java:394)
	at io.vertx.core.impl.ContextImpl.lambda$wrapTask$2(ContextImpl.java:316)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:418)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:440)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:873)
	at java.lang.Thread.run(Thread.java:745)
```
