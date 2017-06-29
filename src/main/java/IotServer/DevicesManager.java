package IotServer;

import java.util.HashMap;
import java.util.Iterator;

import Mesg.MsgObj;
import io.netty.channel.ChannelId;

public class DevicesManager {
	HashMap<String ,DeviceInfo> deviceList;
	static DevicesManager mDevicesManager=null;
	static public DevicesManager getInstance()
	{
		if(mDevicesManager==null)
		{
			mDevicesManager=new DevicesManager();
		}
		return mDevicesManager;
	}
	private class DeviceInfo
	{
		public ChannelId deviceIp;
		public HashMap<ChannelId,Boolean> mobileIp;
		public int led;
	}

	private DevicesManager() {
		// TODO Auto-generated constructor stub
		deviceList=new HashMap<String,DeviceInfo>();
		
	}
	private DeviceInfo GetDeviceInfoInstance(String deviceId)
	{	
		DeviceInfo deviceInfo;
		if(deviceList.containsKey(deviceId))
		{
			deviceInfo=deviceList.get(deviceId);
			
		}
		else
		{
			deviceInfo=new DeviceInfo();
			deviceInfo.deviceIp=null;
			deviceInfo.mobileIp=new HashMap<ChannelId,Boolean>();
			
		}
		return deviceInfo;
	}
	public void InitDeviceIp(String deviceId,ChannelId deviceIp)
	{
		DeviceInfo deviceInfo=GetDeviceInfoInstance( deviceId);
		if(deviceInfo!=null)
		{
			deviceInfo.deviceIp=deviceIp;
			deviceList.put(deviceId, deviceInfo);
		}
		
		
	}
	
	public void AddDeviceMobileIp(String deviceId,ChannelId mobileIp)
	{
		DeviceInfo deviceInfo=GetDeviceInfoInstance( deviceId);
		if(deviceInfo!=null)
		{
			deviceInfo.mobileIp.put(mobileIp, true);
			deviceList.put(deviceId, deviceInfo);
		}
	}
	
	public ChannelId GetDeviceIp(String deviceId)
	{
		DeviceInfo deviceInfo;
		deviceInfo= deviceList.get(deviceId);
		if(deviceInfo!=null)
		{
			return deviceInfo.deviceIp;
		}
		else
		{
			return null;
		}
	}
	
	public ChannelId GetMobileIp(String deviceId)
	{
		DeviceInfo deviceInfo;
		deviceInfo= deviceList.get(deviceId);
		if(deviceInfo!=null)
		{
			Iterator<ChannelId> chanlIds;
			chanlIds=deviceInfo.mobileIp.keySet().iterator();
			if(chanlIds.hasNext())
			{//get the first channel id for test at first.
				return chanlIds.next();
			}
			
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	public void SetLed(String deviceId,int onOff)
	{
		DeviceInfo deviceInfo=GetDeviceInfoInstance( deviceId);
		if(deviceInfo!=null)
		{
			deviceInfo.led=onOff;
		}
	}
	public int GetLed(String deviceId)
	{
		int led=0;
		DeviceInfo deviceInfo=GetDeviceInfoInstance( deviceId);
		if(deviceInfo!=null)
		{
			led=deviceInfo.led;
		}
		return led;
	}
	
	public MsgObj.DeviceInfo GetDeviceInfo(String deviceId)
	{
		MsgObj msgObj=new MsgObj(); 
		MsgObj.DeviceInfo  deviceInfoToMobile=msgObj.new DeviceInfo();
		deviceInfoToMobile.setId(deviceId);
		deviceInfoToMobile.setLed(GetLed(deviceId));
		return deviceInfoToMobile;
	}

}
