/**
 * 
 */
package edu.rupp.sss.service.transform;

import java.util.HashMap;

/**
 * @author sovannoty
 *
 */
public interface TransformStrategy {
	
	void preTransform(Object payLoad);
	
	Object doTransform(Object payLoad);
	
	void postTransform(Object payLoad);
	
	Boolean support(Object payLoad)  throws Exception;
	
	
	HashMap<String,Object> toMap(String jsonString) throws Exception;

}
