package Mesg;

public class MsgObj {

	public MsgObj() {
		// TODO Auto-generated constructor stub
	}
	
	public class MsgGeneral
	{
		String msg;
		String msgCase;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getMsgCase() {
			return msgCase;
		}
		public void setMsgCase(String msgCase) {
			this.msgCase = msgCase;
		}
		
	}
	public class IdInfo
	{
		String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
	}

	public class DeviceInfo
	{
		int led;
		String id;
		public int getLed() {
			return led;
		}
		public void setLed(int led) {
			this.led = led;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		
	}
	
	

	


}
