package IotServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

@SpringBootApplication
public class ServerMain implements CommandLineRunner{
	static final boolean SSL = System.getProperty("ssl") != null;
	static final int PORT = Integer.parseInt(System.getProperty("port", "1010"));
	public ServerMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		
		
		SpringApplication.run(ServerMain.class, args);
	}
	
	private static void nettyServerInit() throws Exception
	{
		// TODO Auto-generated method stub
				int i;
				i=0;
				//DevicesManager devicesManager=new DevicesManager();
				/*devicesManager.InitDeviceIp("0001", "10.99.137.131");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.132");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.133");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.133");*/
				new JsonObjectDecoder();
				System.out.println("hello,netty server,hot plug");	
				 String Ip = null;  
				    try {  
				        Ip = InetAddress.getLocalHost().getHostAddress();  
				    } catch (UnknownHostException e) {  
				        e.printStackTrace();  
				    } 
				    System.out.println("MY ip is:"+Ip);	
				// Configure SSL.
			    final SslContext sslCtx;
			    if (SSL) {
			        SelfSignedCertificate ssc = new SelfSignedCertificate();
			        sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
			    } else {
			        sslCtx = null;
			    }

			    // Configure the server.
			    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
			    EventLoopGroup workerGroup = new NioEventLoopGroup();
			    try {
			        ServerBootstrap b = new ServerBootstrap();
			        b.group(bossGroup, workerGroup)
			         .channel(NioServerSocketChannel.class)
			         .option(ChannelOption.SO_BACKLOG, 100)
			         .handler(new LoggingHandler(LogLevel.INFO))
			         .childHandler(new ChannelInitializer<SocketChannel>() {
			             @Override
			             public void initChannel(SocketChannel ch) throws Exception {
			                 ChannelPipeline p = ch.pipeline();
			                 if (sslCtx != null) {
			                     p.addLast(sslCtx.newHandler(ch.alloc()));
			                 }
			                 //p.addLast(new LoggingHandler(LogLevel.INFO));
			                 //p.addLast(new NettyEchoServerHandler());
			                 //p.addLast(new JsonObjectDecoder(),new NettyEchoServerHandler());//it seems the JsonObjectDecoder() have some bug(will receive serval json sometimes in handler size),and I have to add my:MyJsonDecoder()
			                 p.addLast(new StringEncoder());//	for server to client encode,if not this,it default is ByteBuf,and client can't receive it		                
			                 p.addLast(new MyJsonDecoder(),new NettyEchoServerHandler());
			                 //p.addLast(new TestChannelHandler());
			                 
			                
			             }
			         });

			        // Start the server.
			        ChannelFuture f = b.bind(PORT).sync();

			        // Wait until the server socket is closed.
			        f.channel().closeFuture().sync();
			    } finally {
			        // Shut down all event loops to terminate all threads.
			        bossGroup.shutdownGracefully();
			        workerGroup.shutdownGracefully();
			    }
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		nettyServerInit();
	}

}


/*
@SpringBootApplication								
public class Main implements CommandLineRunner{								
								
	  							
	public static void main(String[] args) throws Exception {							
		SpringApplication.run(Main.class, args);						
	}							
								
	public void run(String... arg0) throws Exception {							
		// TODO Auto-generated method stub						
								
								
		      //logger.info(pEntity.toString());						
			 System.out.println("hello,netty server");					
	}							
								
}								
*/