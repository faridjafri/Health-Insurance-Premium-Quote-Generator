package com.emids.hipqg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class HelloController {

	@GET
	@Path("/say")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {
		Map<String, String> map = new HashMap<>();
		map.put("Hi", "asdf");
		return Response.ok().entity(map).build();
	}

}
