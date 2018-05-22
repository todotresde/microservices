package com.todotresde.myapp.app1.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.todotresde.myapp.app1")
public class FeignConfiguration {

}
