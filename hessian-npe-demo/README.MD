## Dubbo调用端hessian反序列化抛NPE问题复现Demo

### 问题简述
很多人在使用dubbo时, dubbo调用端碰到过如下异常, 截止dubbo 2.6.5版本还是有这个问题:
```java
java.lang.NullPointerException
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.alibaba.com.caucho.hessian.io.JavaDeserializer.instantiate(JavaDeserializer.java:312)
	at com.alibaba.com.caucho.hessian.io.JavaDeserializer.readObject(JavaDeserializer.java:201)
	at com.alibaba.com.caucho.hessian.io.SerializerFactory.readObject(SerializerFactory.java:526)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObjectInstance(Hessian2Input.java:2820)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObject(Hessian2Input.java:2743)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObject(Hessian2Input.java:2278)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObject(Hessian2Input.java:2717)
	at com.alibaba.com.caucho.hessian.io.Hessian2Input.readObject(Hessian2Input.java:2278)
	at com.alibaba.dubbo.common.serialize.hessian2.Hessian2ObjectInput.readObject(Hessian2ObjectInput.java:84)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcResult.decode(DecodeableRpcResult.java:121)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DecodeableRpcResult.decode(DecodeableRpcResult.java:143)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboCodec.decodeBody(DubboCodec.java:90)
	at com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec.decode(ExchangeCodec.java:125)
	at com.alibaba.dubbo.remoting.exchange.codec.ExchangeCodec.decode(ExchangeCodec.java:85)
	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboCountCodec.decode(DubboCountCodec.java:46)
	at com.alibaba.dubbo.remoting.transport.netty.NettyCodecAdapter$InternalDecoder.messageReceived(NettyCodecAdapter.java:133)
	at org.jboss.netty.channel.SimpleChannelUpstreamHandler.handleUpstream(SimpleChannelUpstreamHandler.java:80)
	at org.jboss.netty.channel.DefaultChannelPipeline.sendUpstream(DefaultChannelPipeline.java:564)
	at org.jboss.netty.channel.DefaultChannelPipeline.sendUpstream(DefaultChannelPipeline.java:559)
	at org.jboss.netty.channel.Channels.fireMessageReceived(Channels.java:274)
	at org.jboss.netty.channel.Channels.fireMessageReceived(Channels.java:261)
	at org.jboss.netty.channel.socket.nio.NioWorker.read(NioWorker.java:349)
	at org.jboss.netty.channel.socket.nio.NioWorker.processSelectedKeys(NioWorker.java:280)
	at org.jboss.netty.channel.socket.nio.NioWorker.run(NioWorker.java:200)
	at org.jboss.netty.util.ThreadRenamingRunnable.run(ThreadRenamingRunnable.java:108)
	at org.jboss.netty.util.internal.DeadLockProofWorker$1.run(DeadLockProofWorker.java:44)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
```

### 如何重现该问题
- 在IDEA里以Run模式执行hessian-npe-demo-server工程中的Provider类的main方法
- 再以Run或Debug模式执行hessian-npe-demo-client工程中的Consumer类的main方法, 稍等几秒, 就会抛出我们期待的异常

该问题详细分析文档见https://www.yuque.com/fa902k/id5z6r/sr041v