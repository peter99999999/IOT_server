package IotServer;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyJsonDecoder extends ByteToMessageDecoder{

	public MyJsonDecoder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		int wrtIdx = in.writerIndex();
		int idx = in.readerIndex();//this.m_idx;
		int braceCount=0;
		int byteCount=0;
		for (/* use current idx */; idx < wrtIdx; idx++) 
		{
	            
			    byteCount++;
			    byte c = in.getByte(idx);
	            if(c=='{')
	            {
	            	braceCount++;
	            	System.out.println("The json getByte start:");
	            	System.out.print('{');
	            }
	            else if(c=='}')
	            {
	            	System.out.print('}');
	            	braceCount--;
	            	System.out.println("braceCount="+braceCount);
	            	if(braceCount==0)
		            {
	            		 System.out.println("");
		            	 System.out.println("The idx is:"+idx+",the wrtIdx is:"+wrtIdx);	 
		            	 //ByteBuf json =in.retainedSlice(in.readerIndex(), idx+1-in.readerIndex());//The function don't update the in.readerIndex(); 
		            	 ByteBuf json =in.readBytes(byteCount);
		            	 System.out.println("The json is:"+json.toString(io.netty.util.CharsetUtil.US_ASCII));	
		            	 if (json != null) 
		            	 {
		                        out.add(json);
		                 }
		            	 //in.readerIndex(idx + 1);		            	
		            	 byteCount=0;
		            
		            }
	            }
	            else
	            {
	            	System.out.printf("%c", c);
	            }
	            
		}
		
	}

}
