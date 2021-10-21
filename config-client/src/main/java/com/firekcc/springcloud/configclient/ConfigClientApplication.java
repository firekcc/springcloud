package com.firekcc.springcloud.configclient;

import io.leopard.javahost.JavaHost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

    static {
//        loadVHost();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
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
