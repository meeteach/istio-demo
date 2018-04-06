package com.pay.istio.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author niuyandong
 * @since 2018年4月6日 下午3:41:57
 */
@SpringBootApplication
@Configuration
public class HelloworldApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }
    
}

@RestController
class HelloworldController {
    
    @Value("${version}")
    public String version = "1.0";
    
    @GetMapping("/hello/{name}")
    public Map<String, String> hello(@Value("${greeting}") String greetingTemplate, @PathVariable String name)
        throws UnknownHostException {
        Map<String, String> response = new HashMap<>();
        
        String hostname = InetAddress.getLocalHost().getHostName();
        String greeting = greetingTemplate.replaceAll("\\$name", name).replaceAll("\\$hostname", hostname).replaceAll("\\$version", version);
        
        response.put("greeting", greeting);
        response.put("version", version);
        response.put("hostname", hostname);
        
        return response;
    }
}
