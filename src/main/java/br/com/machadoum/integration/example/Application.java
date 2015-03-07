package br.com.machadoum.integration.example;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
     }
		
	@Bean
	public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
		SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
		camelContext.addRoutes(routeBuilder());
		return camelContext;
	}
	
	@Bean
	public RouteBuilder routeBuilder() {
		return new Route();
	}
	
	@Bean
	public ConnectionFactory ConnectionFactory(@Value("${broker.url}") String url) {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(url);
		return activeMQConnectionFactory;
	}
}