package IotServer;

import java.net.InetSocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	ByteBuf in = (ByteBuf) msg;
    	String clientIP;									
    	String info=in.toString(io.netty.util.CharsetUtil.US_ASCII);									//获取客户端发过来的信�?
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();									
		clientIP = insocket.getAddress().getHostAddress();							
		System.out.println("clientIP:"+clientIP);							//获取客户端的IP									
        System.out.println("Server receive:"+info);									
        ctx.write(msg);
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
}