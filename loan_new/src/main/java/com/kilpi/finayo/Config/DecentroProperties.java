package com.kilpi.finayo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "app.decentro")
@Setter
@Getter
public class DecentroProperties {	

	private String clientId;
	private String clientSecret;
	private String moduleSecret;

}
