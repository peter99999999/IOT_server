package Iot.common.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter
public class booleanToIntegerConverter implements AttributeConverter<Boolean, Integer>{
		        
	    	@Override
	        public Integer convertToDatabaseColumn(Boolean value) {        
	            return (value != null && value) ? 1 : 0;            
	            }    

	        @Override
	        public Boolean convertToEntityAttribute(Integer value) {
	              if(value==0)
	              {
	            	  return false;
	              }
	              else
	              {
	            	  return true;
	              }
	            }
	        

}
