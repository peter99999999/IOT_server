package IotServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HttpHandler {

	public HttpHandler() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/greeting")		
	@ResponseBody
	public String helloWorld() {	
		String test="Good,";
		return test+"greeting2";			
	}				
																


}
