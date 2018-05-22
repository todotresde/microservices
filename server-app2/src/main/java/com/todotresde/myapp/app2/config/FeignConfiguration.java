package com.todotresde.myapp.app2.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.todotresde.myapp.app2")
public class FeignConfiguration {

}
