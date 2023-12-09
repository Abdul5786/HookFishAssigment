package com.Abdul.HookiFish;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HookiFishApplication {

	public static void main(String[] args) {
		SpringApplication.run(HookiFishApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}



}
