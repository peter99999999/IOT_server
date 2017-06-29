package Mesg;

public class MesgCase {

	
	
		String MESG="msg";
		String MESG_CASE="msgCase";	
	    
	
	
	public enum Msg {
		
		//MESG_CASE("msgCase"),
		DEVICE_INIT("deviceInit"),
		MOBILE_INIT("mobileInit"),
		DEVICE_SEND_INFO("deviceSendInfo"),
		MOBILE_GET_DEVICE_MSG("mobileGetDeviceMsg"),
		MOBILE_SET_DEVICE("mobileSetDevice"),
		RETURN_INFO_TO_MOBILE("returnInfoToMobile");
		private String msg;
		private Msg(String msg)
		{
			this.msg=msg;
		}
		public boolean equals(String msg)
		{
			return this.msg.equals(msg);
		}
		
		public String toString()
		{
			return this.msg;
		}
	}

}
