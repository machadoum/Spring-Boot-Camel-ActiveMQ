package br.com.machadoum.integration.example;

import org.apache.camel.builder.RouteBuilder;

public class Route extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jms:test.input")
		.log("${body}")
		.to("jms:test.output");
	}
}