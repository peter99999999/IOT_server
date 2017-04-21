package IotServer;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

public class DataStore {

	private Map<Integer,DataObj> mStoreMap;
	private Map<Integer,ChannelId> mIdConnectMap;
	static private volatile DataStore m_pInstance=null;
	DataObj objInfo;
	
	private  DataStore() {
		// TODO Auto-generated constructor stub
		mStoreMap=new HashMap<Integer,DataObj>();
		mIdConnectMap=new HashMap<Integer,ChannelId>();
	}
	static public DataStore getInstance() 
	{
		synchronized (DataStore.class)
		{
			if(m_pInstance==null)
			{
				m_pInstance=new DataStore();
			}
		}
		return m_pInstance;
	}
	
	public DataObj getObjInfo(Integer id) {
		DataObj tmpObjInfo=mStoreMap.get(id);
		return tmpObjInfo;
	}
	public void setObjInfo(DataObj objInfo) {
		System.out.println("current thread id is:"+Thread.currentThread().getId());
		Gson gson = new Gson();
		String json = gson.toJson(objInfo);  
		System.out.println("DataStore objInfo:"+json);	
		mStoreMap.put(objInfo.getId(), objInfo);
	}
	
	public void recordIdConnect(Integer id,ChannelId channelId)
	{
		mIdConnectMap.put(id,channelId);
	}
	public ChannelId getIdConnect(Integer id)
	{
		return mIdConnectMap.get(id);
	}
	

}
