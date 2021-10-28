package com.firekcc.springcloud.configclient;

import io.leopard.javahost.JavaHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ConfigClientApplication {

    static {
//        loadVHost();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${config-from}")
    String configFrom;

    @GetMapping(value = "/config")
    public String hi(){
        return configFrom;
    }


    private static void loadVHost() {
        String eurekaHostName = "eureka-master";
        String vhostConfigPath = "/config/vhost.properties";
        try {
            InetAddress.getByName(eurekaHostName);
        } catch (UnknownHostException e) {
            Properties properties = new Properties();
            try {
                properties.load(ConfigClientApplication.class.getResourceAsStream(vhostConfigPath));
                JavaHost.updateVirtualDns(properties);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
