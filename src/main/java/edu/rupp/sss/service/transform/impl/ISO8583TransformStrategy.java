/**
 * 
 */
package edu.rupp.sss.service.transform.impl;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.rupp.sss.model.ISO8583;
import edu.rupp.sss.service.transform.TransformStrategy;

/**
 * @author sovannoty
 *
 */
@Service
public class ISO8583TransformStrategy implements TransformStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rupp.sss.service.transform.TransformStrategy#preTransform(java.lang.
	 * Object)
	 */
	public void preTransform(Object payLoad) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.rupp.sss.service.transform.TransformStrategy#doTransform(java.lang.
	 * Object)
	 */
	public Object doTransform(Object payLoad) {
		ISO8583 iso8583Object = new ISO8583();
		try {
			// Create Packager based on XML that contain DE type
			GenericPackager packager = new GenericPackager("src/main/resources/jpos.xml");
			// Create ISO Message
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.unpack(((String) payLoad).getBytes());
			iso8583Object.setMIT(isoMsg.getMTI());
			
			for (int i = 1; i <= isoMsg.getMaxField(); i++) {
				if (isoMsg.hasField(i)) {
					set(iso8583Object, "F"+ StringUtils.leftPad(String.valueOf(i), 3, "0") , isoMsg.getString(i) );
				}
			}
			
		} catch (Exception e) {
			return null;
		}
		return iso8583Object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.sss.service.transform.TransformStrategy#postTransform(java.lang.
	 * Object)
	 */
	public void postTransform(Object payLoad) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.sss.service.transform.TransformStrategy#support(java.lang.Object)
	 */
	public Boolean support(Object payLoad) throws Exception {
		try {
			// Create Packager based on XML that contain DE type
			GenericPackager packager = new GenericPackager("src/main/resources/jpos.xml");
			// Create ISO Message
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.unpack(((String) payLoad).getBytes());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public synchronized static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}

	
	public HashMap<String,Object> toMap(String jsonString) throws Exception {
		HashMap<String,Object> result = new ObjectMapper().readValue(jsonString, HashMap.class);
		return result;
	}

}
