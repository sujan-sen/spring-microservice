package com.springms.gatewayservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

//This config class can be used for 
//Overriding default Ribbon algorithm from Zuul
@ConditionalOnProperty(name="enable.custom.ribbon.config", havingValue="true")
public class RibbonConfiguration {
	@Autowired
	IClientConfig ribbonClientConfig;

	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}

	@Bean
	public IRule ribbonRule(IClientConfig config) {
		//return new AvailabilityFilteringRule();
		return new RoundRobinRule();
	}
	
	@Bean
    public ServerList<Server> ribbonServerList() {
        //return new ConfigurationBasedServerList(); 
		return new StaticServerList<>(new Server("localhost", 9001));
	}
}
        