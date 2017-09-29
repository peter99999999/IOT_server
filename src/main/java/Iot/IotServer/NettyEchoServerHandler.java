package Iot.IotServer;

import java.net.InetSocketAddress;

import com.google.gson.Gson;

import Iot.Mesg.RecMsgDecode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

@Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
	 static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	Gson gson = new Gson();
    	ByteBuf in = (ByteBuf) msg;
    	String strobjS[]; 
    										
    	String info=in.toString(io.netty.util.CharsetUtil.US_ASCII);								//;	//获取客户端发过来的信�?
    								
        System.out.println("NettyEchoServerHandler,Server receive:"+info);	

        RecMsgDecode.getInstance().decodeMsg(info,ctx.channel().id());
      //ctx.write(msg);//the msg will send to client 
        /*DataObj dataObj;
		try {
			dataObj = gson.fromJson(info, DataObj.class);
			DataStore.getInstance().setObjInfo(dataObj);
	    	DataStore.getInstance().recordIdConnect(dataObj.getId(),ctx.channel().id());
	      
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("the info in not a DataObj.class");	
		}   */ 
		
    	/*String msg_2="the pc getting your info" ; 
    	for (Channel c: channels) {
			 //c.writeAndFlush( msg_2 + '\n');
    		if (c != ctx.channel()) {
    			                  c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg_2 + '\n');
    			             } else {
    			                  c.writeAndFlush("[you] " + msg_2 + '\n');
    			              }
		}*/
    }

    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
    	String clientIP;
    	super.channelActive(ctx);
    	channels.add(ctx.channel());
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();									
		clientIP = insocket.getAddress().getHostAddress();							
		System.out.println("Welcome:"+clientIP+",connect!");							//获取客户端的IP		
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		String clientIP;
		super.channelInactive(ctx);
		InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();									
		clientIP = insocket.getAddress().getHostAddress();							
		System.out.println("Bye,"+clientIP);							//获取客户端的IP	
	}

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
    
    static public ChannelGroup getChannelGroup()
    {
    	return channels;
    }
}