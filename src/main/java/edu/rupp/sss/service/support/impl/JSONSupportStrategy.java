/**
 * 
 */
package edu.rupp.sss.service.support.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.rupp.sss.service.support.SupportStrategy;

/**
 * @author sovannoty
 *
 */
@Service
public class JSONSupportStrategy implements SupportStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.springsupportstratergy.SupportStrategy#preStuff(java.lang.Object)
	 */
	public void preStuff(Object payLoad) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.springsupportstratergy.SupportStrategy#doStuff(java.lang.Object)
	 */
	public void doStuff(Object payLoad) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.springsupportstratergy.SupportStrategy#postStuff(java.lang.Object)
	 */
	public void postStuff(Object payLoad) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.rupp.springsupportstratergy.SupportStrategy#support(java.lang.Object)
	 */
	public Boolean support(Object payLoad)  throws Exception {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree((String) payLoad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
