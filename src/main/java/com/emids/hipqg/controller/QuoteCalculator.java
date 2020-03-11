package com.emids.hipqg.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emids.hipqg.model.CustomerModel;
import com.emids.hipqg.service.QuoteService;
import com.emids.hipqg.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Path("/quote")
public class QuoteCalculator {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuoteCalculator.class);

	@Autowired
	private QuoteService service;

	@POST
	@Path("/calculate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello(String body) throws JsonProcessingException {
		System.out.println(body);
		CustomerModel customerModel = null;
		try {
			customerModel = (CustomerModel) CommonUtils.parsePayload(body, CustomerModel.class);
		} catch (Exception ex) {
			LOGGER.debug("{}", ex);
			LOGGER.info("{}", ex.getMessage());
			LOGGER.error("Exception occured while parsing the data {}", ex.getMessage());
			return Response.status(500)
					.entity(new ObjectMapper().writeValueAsString("There was an error. Please check the logs."))
					.build();
		}
		Double calculateQuote = service.calculateQuote(customerModel);
		return Response.ok().entity(calculateQuote).build();
	}

}
