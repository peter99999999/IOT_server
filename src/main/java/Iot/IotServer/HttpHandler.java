package Iot.IotServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import Iot.Timer.TimerData;
import Iot.Timer.TimerDataRepository;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

@Controller
public class HttpHandler implements Filter{
    final String OK="ok"; 
	private int count;
	@Autowired
	TimerDataRepository timerDataRepository;
	
	public HttpHandler() {
		// TODO Auto-generated constructor stub
	}
	/* @RequestMapping("/index")
		public String indxe() {
			return "index.html";
		 //return "redirect:/index_.html";
		// return "redirect:/index.html";
		}*/
/*	 
	@RequestMapping("/")
	@ResponseBody
	public String homepage() {
		return "new cert,Hello, world. Welcome";
	}*/
	@RequestMapping("/lp")//for little program test		
	@ResponseBody
	//public String lp(@RequestBody String data) {	
	public String lp(@RequestParam(value="x",defaultValue="") String x,@RequestParam(value="y",defaultValue="") String y) {
		
		
		//return "get"+count+":"+data;	
		return "x:"+x+",y:"+y;
		
	}
	
	@RequestMapping("/gettimers")		
	@ResponseBody
	public ArrayList<TimerData> getTimers(@RequestParam(value="pruductID",defaultValue="") String pruductID,@RequestParam(value="functionID",defaultValue="") int functionID) {	
		ArrayList<TimerData> timerList=timerDataRepository.findByPruductIDAndFunctionIDAllIgnoringCase(pruductID,functionID);
		return timerList;
	}
	
	@RequestMapping("/gettimer")		
	@ResponseBody
	public TimerData getTimer(@RequestParam(value="id",defaultValue="") Long id)
	{
		return timerDataRepository.findOne(id);
	}
	
	@RequestMapping("/addtimer")		
	@ResponseBody
	public String addTimer(@RequestBody String timer)
	{
		
		Gson gson=new Gson();
		TimerData timerOBJ=gson.fromJson(timer, TimerData.class);
		timerDataRepository.save(timerOBJ);
		return timer;
	}
	
	@RequestMapping("/greeting")		
	@ResponseBody
	public String helloWorld() {	
		
		//Gson gson=new Gson();
		//String gsonstr=gson.toJson(timerList);
		count++;
		String test="Good,";
		return test+"greeting "+count;	
		
	}
	@RequestMapping("/getInfo")		
	@ResponseBody
	public String getInfo(@RequestParam(value="id",defaultValue="") int id) {	
		
		/*String msg="the pc getting your info" ;
		ChannelId channelId;
		
		channelId=DataStore.getInstance().getIdConnect(id);
		if(channelId!=null)
		{
			ChannelGroup channels =NettyEchoServerHandler.getChannelGroup();
			Channel chanel=channels.find(channelId);
			if(chanel!=null)
			{
				chanel.writeAndFlush( msg + '\n');
			}
		}*/
		
		/*DataObj dataObj=DataStore.getInstance().getObjInfo( id);	
		if(dataObj!=null)
		{
			return ""+dataObj.getValue();
		}
		else
		{
			return "not connect yet";
		}*/
		return OK;
	}
	
	@RequestMapping("/sendInfo")		
	@ResponseBody
	public String sendInfo(@RequestParam(value="message",defaultValue="") String message) {	
		ChannelGroup channels =NettyEchoServerHandler.getChannelGroup();
		for(Channel chanel:channels)
		{
			chanel.writeAndFlush( message + '\n');
		}
		return OK;
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub	
		HttpServletResponse response_2 = (HttpServletResponse) response;  
		response_2.setHeader("Access-Control-Allow-Origin", "*"); 
		response_2.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request, response);  
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}				
																


}
