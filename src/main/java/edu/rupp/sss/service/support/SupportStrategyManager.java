/**
 * 
 */
package edu.rupp.sss.service.support;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sovannoty
 *
 */
@Service
public class SupportStrategyManager {

	@Autowired
	public Map<String, SupportStrategy> services;

	public Map<String, SupportStrategy> getServices() {
		return services;
	}

	public void setServices(Map<String, SupportStrategy> services) {
		this.services = services;
	}

	public SupportStrategy getSupportStrategy(Object payLoad) throws Exception {
		SupportStrategy supportStrategy = null;
		for (Map.Entry<String, SupportStrategy> entry : services.entrySet()) {
			if (entry.getValue().support(payLoad)) {
				return entry.getValue();
			}
		}
		return supportStrategy;
	}

}
