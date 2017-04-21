package IotServer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

@Controller
public class HttpHandler implements Filter{

	private int count;
	public HttpHandler() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/greeting")		
	@ResponseBody
	public String helloWorld() {	
		count++;
		String test="Good,";
		return test+"greeting "+count;			
	}
	@RequestMapping("/getInfo")		
	@ResponseBody
	public String getInfo(@RequestParam(value="id",defaultValue="1") int id) {	
		
		String msg="the pc getting your info" ;
		ChannelId channelId;
		DataObj dataObj=DataStore.getInstance().getObjInfo( id);	
		channelId=DataStore.getInstance().getIdConnect(id);
		if(channelId!=null)
		{
			ChannelGroup channels =NettyEchoServerHandler.getChannelGroup();
			Channel chanel=channels.find(channelId);
			if(chanel!=null)
			{
				chanel.writeAndFlush( msg + '\n');
			}
		}
		
		if(dataObj!=null)
		{
			return ""+dataObj.getValue();
		}
		else
		{
			return "not connect yet";
		}
	}
	
	
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub	
		HttpServletResponse response_2 = (HttpServletResponse) response;  
		response_2.setHeader("Access-Control-Allow-Origin", "*"); 
		response_2.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request, response);  
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}				
																


}
