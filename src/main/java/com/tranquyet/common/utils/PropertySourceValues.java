package com.tranquyet.common.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class PropertySourceValues implements EnvironmentAware {
	private String value;
	private String key;

	@Override
	public void setEnvironment(Environment environment) {
		this.value = environment.getProperty(key);
	}

}
