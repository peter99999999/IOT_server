package Iot;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import Iot.Timer.TimerDataRepository;


@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses=Timer.TimerDataRepository.class)
//@EnableJpaRepositories(basePackageClasses={Timer.TimerDataRepository.class,Timer.TimerData.class})
public class ServerMain implements CommandLineRunner{
	
	@Autowired
	TimerDataRepository timerDataRepository;
	//@Autowired
	//CityRepository cityRepository;
	
	static final boolean SSL = System.getProperty("ssl") != null;
	static final int PORT = Integer.parseInt(System.getProperty("port", "1010"));
	/*public ServerMain() {
		// TODO Auto-generated constructor stub
	}
*/
	@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
            	//Due to CONFIDENTIAL and /*, this will cause Tomcat to redirect every request to HTTPS. 
            	//You can configure multiple patterns and multiple constraints if you need more control over what is and is not redirected.
            	
            	SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;

  }
	@Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        
        //Set the scheme that will be assigned to requests received through this connector
        //@param scheme The new scheme
        connector.setScheme("http");
        
        //Set the port number on which we listen for requests.
        // @param port The new port number
        connector.setPort(80);       
       
        //Set the secure connection flag that will be assigned to requests received through this connector.
        //@param secure The new secure connection flag
        //if connector.setSecure(true),the http use the http and https use the https;else if connector.setSecure(false),the http redirect to https;
        connector.setSecure(false);
        
        //redirectPort The redirect port number (non-SSL to SSL)
        connector.setRedirectPort(443);
        return connector;
    }

	public static void main(String[] args) throws Exception {
		
		
		SpringApplication.run(ServerMain.class, args);
	}
	
	
	/*private static void nettyServerInit() throws Exception
	{
		// TODO Auto-generated method stub
				int i;
				i=0;
				//DevicesManager devicesManager=new DevicesManager();
				devicesManager.InitDeviceIp("0001", "10.99.137.131");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.132");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.133");
				devicesManager.AddDeviceMobileIp("0001", "10.99.137.133");
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
	}*/
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		/*TimerData timerData=new TimerData();
		timerData.setOffTime("11:00");
		timerData.setOnTime("12:00");
		timerData.setPruductID("1234HIJ789");
		timerData.setTimerOn(true);
		timerData.setRepeatCase(3);
		timerDataRepository.save(timerData);*/
		
	//	nettyServerInit();
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