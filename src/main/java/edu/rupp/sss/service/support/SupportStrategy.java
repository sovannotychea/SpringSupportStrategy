/**
 * 
 */
package edu.rupp.sss.service.support;

/**
 * @author sovannoty
 *
 */
public interface SupportStrategy {
	
	void preStuff(Object payLoad);
	
	void doStuff(Object payLoad);
	
	void postStuff(Object payLoad);
	
	Boolean support(Object payLoad)  throws Exception;

}
