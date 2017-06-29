package Mesg;
/*
 {"msgCase":"deviceInit","msg":"{'id':'1'}"}
 {"msgCase":"mobileInit","msg":"{'id':'1'}"}
 {"msgCase":"deviceSendInfo","msg":"{'id':'1','led':1}"}
 {"msgCase":"mobileGetDeviceMsg","msg":"{'id':'1'}"}
 {"msgCase":"mobileSetDevice","msg":"{'id':'1','led':1}"}
 */
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import IotServer.DevicesManager;
import io.netty.channel.ChannelId;

public class RecMsgDecode {
	static private RecMsgDecode mRecMsgDecode=null;
	private RecMsgDecode() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void decodeMsg(String info,ChannelId channelId)
	{
		Gson gson = new Gson();
		MsgObj.MsgGeneral msgGeneralObj;
	
		
		try {
			msgGeneralObj = gson.fromJson(info, MsgObj.MsgGeneral.class);
			String msgStr=msgGeneralObj.getMsg();
		    String msgCase=msgGeneralObj.getMsgCase();
		    
		    	if(MesgCase.Msg.DEVICE_INIT.equals(msgCase))		
		    	{
					try {
						MsgObj.IdInfo initDeviceObj = gson.fromJson(msgGeneralObj.getMsg(), MsgObj.IdInfo.class);
						DevicesManager.getInstance().InitDeviceIp(initDeviceObj.getId(), channelId);
						SendMsg.getInstance().sendMsg(info,channelId);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
		    	}
		    	else if(MesgCase.Msg.MOBILE_INIT.equals(msgCase))
		    	{
					try {
						MsgObj.IdInfo initMobileObj=gson.fromJson(msgGeneralObj.getMsg(), MsgObj.IdInfo.class);
						DevicesManager.getInstance().AddDeviceMobileIp(initMobileObj.getId(), channelId);
						SendMsg.getInstance().sendMsg(info,channelId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	else if(MesgCase.Msg.DEVICE_SEND_INFO.equals(msgCase))
		    	{
					try {
						MsgObj.DeviceInfo deviceInfo=gson.fromJson(msgGeneralObj.getMsg(), MsgObj.DeviceInfo.class);
						DevicesManager.getInstance().SetLed(deviceInfo.getId(), deviceInfo.getLed());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	else if(MesgCase.Msg.MOBILE_GET_DEVICE_MSG.equals(msgCase))
		    	{
					try {
						MsgObj.IdInfo idInfo=gson.fromJson(msgGeneralObj.getMsg(), MsgObj.IdInfo.class);
						SendMsg.getInstance().deviceInfoToMobile( DevicesManager.getInstance().GetDeviceInfo(idInfo.getId()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}	
		    	else if(MesgCase.Msg.MOBILE_SET_DEVICE.equals(msgCase))
		    	{
		    		MsgObj.DeviceInfo deviceInfo=gson.fromJson(msgGeneralObj.getMsg(), MsgObj.DeviceInfo.class);
		    		SendMsg.getInstance().mobileSetDevice(deviceInfo);
		    	}
		    		
		    
			
	       
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("the info in not a MsgObj.MsgGeneral.class");	
		}    
	}
	
	
	static public RecMsgDecode getInstance()
	{
		if(mRecMsgDecode==null)
		{
			mRecMsgDecode=new RecMsgDecode();
		}
		return mRecMsgDecode;
	}

}
