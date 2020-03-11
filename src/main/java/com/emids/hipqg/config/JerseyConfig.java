package com.emids.hipqg.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.emids.hipqg.controller.HelloController;
import com.emids.hipqg.controller.QuoteCalculator;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(HelloController.class);
		register(QuoteCalculator.class);
	}
}
