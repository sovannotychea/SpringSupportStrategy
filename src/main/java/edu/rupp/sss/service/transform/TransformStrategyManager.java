/**
 * 
 */
package edu.rupp.sss.service.transform;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sovannoty
 *
 */
@Service
public class TransformStrategyManager {

	@Autowired
	public Map<String, TransformStrategy> services;

	public Map<String, TransformStrategy> getServices() {
		return services;
	}

	public void setServices(Map<String, TransformStrategy> services) {
		this.services = services;
	}

	public TransformStrategy getSupportStrategy(Object payLoad) throws Exception {
		TransformStrategy supportStrategy = null;
		for (Map.Entry<String, TransformStrategy> entry : services.entrySet()) {
			if (entry.getValue().support(payLoad)) {
				return entry.getValue();
			}
		}
		return supportStrategy;
	}

}
