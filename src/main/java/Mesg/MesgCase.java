package Mesg;

public class MesgCase {

	
	
		String MESG="msg";
		String MESG_CASE="msgCase";	
	    
	
	
	public enum Msg {
		
		//MESG_CASE("msgCase"),
		DEVICE_INIT("deviceInit"),//receive from device,and then server send it back to device as the ACK
		MOBILE_INIT("mobileInit"),//receive from mobile,and then server send it back to mobile as the ACK
		DEVICE_SEND_INFO("deviceSendInfo"),//receive from device,after server receive it,will store it but not send out yet.the device will send it with timer duration or receive the "MOBILE_SET_DEVICE"
		MOBILE_GET_DEVICE_MSG("mobileGetDeviceMsg"),//receive from mobile,after server receive it ,will use the "RETURN_INFO_TO_MOBILE" to send the device info to mobile immediately
		MOBILE_SET_DEVICE("mobileSetDevice"),//receive from mobile,after server receive it ,also use the "MOBILE_SET_DEVICE" to send it to device
		RETURN_INFO_TO_MOBILE("returnInfoToMobile");//server to mobile,server send it's latest device info to mobile
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
