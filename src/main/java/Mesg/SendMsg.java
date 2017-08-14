package Mesg;

import com.google.gson.Gson;

import IotServer.DevicesManager;
import IotServer.NettyEchoServerHandler;
import Mesg.MsgObj.DeviceInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

public class SendMsg {
    static SendMsg mSendMsg=null;
	private SendMsg() {
		// TODO Auto-generated constructor stub
	}
	
	static SendMsg getInstance()
	{
		if(mSendMsg==null)
		{
			mSendMsg=new SendMsg();
		}
		return mSendMsg;
	}
	private void setDeviceInfo(DeviceInfo deviceInfo,MesgCase.Msg msgCase)
	{
		ChannelId channelId=null;
		Gson gson = new Gson();
		
		MsgObj msgObj=new MsgObj();	
		MsgObj.MsgGeneral msgGeneral=msgObj.new MsgGeneral();
		
		
		if(msgCase.equals(MesgCase.Msg.RETURN_INFO_TO_MOBILE.toString()))
		{
			msgGeneral.setMsgCase(msgCase.toString());
			channelId=DevicesManager.getInstance().GetMobileIp(deviceInfo.getId());
		}
		else if
		(
				msgCase.equals(MesgCase.Msg.MOBILE_SET_DEVICE.toString())
				//||
				//msgCase.equals(MesgCase.Msg.MOBILE_GET_DEVICE_MSG.toString())
		)
		{
			msgGeneral.setMsgCase(msgCase.toString());
			channelId=DevicesManager.getInstance().GetDeviceIp(deviceInfo.getId());
		}
		
		msgGeneral.setMsg(gson.toJson(deviceInfo));
		sendMsgSub( channelId,gson.toJson(msgGeneral));
		
	}
	private void sendMsgSub(ChannelId channelId,String msg)
	{
		ChannelGroup channels =NettyEchoServerHandler.getChannelGroup();
		Channel chanel=null;
		if(channelId!=null)
		{
			chanel=channels.find(channelId);
			if(chanel!=null)
			//for (Channel chanel: channels)
			{
					String sendMsgStr=msg+System.getProperty("line.separator");
					chanel.writeAndFlush(sendMsgStr);
					System.out.print("The server send out msg is:"+sendMsgStr);
			}
		}
		else
		{
			System.out.print("error,the channelid is null,and the send msg is:"+msg);
		}
		
	}
	public void deviceInfoToMobile(DeviceInfo deviceInfo)
	{
		setDeviceInfo( deviceInfo,MesgCase.Msg.RETURN_INFO_TO_MOBILE);
	}
	
	
	
	public void mobileSetDevice(DeviceInfo deviceInfo)
	{
		setDeviceInfo( deviceInfo,MesgCase.Msg.MOBILE_SET_DEVICE);
	}
	
	void sendMsg(String info,ChannelId channelId)
	{
		sendMsgSub(channelId, info);
	}

}
