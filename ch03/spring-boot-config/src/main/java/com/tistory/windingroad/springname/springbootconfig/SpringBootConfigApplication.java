package com.tistory.windingroad.springname.springbootconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigApplication.class, args);
    }

    private static Logger log = LoggerFactory.getLogger(SpringBootConfigApplication.class);

    // mvn spring-boot:run -Dserver.ip=192.168.12.1
    //@Value("${server.ip}")
    @Value("${myapp.server-ip}")
    String serverIp;

    @Autowired
    MyAppProperties props;

    @Bean
    CommandLineRunner values() {
        return args -> {
            log.info(" > 서버 IP: " + serverIp);
            log.info(" > 애플리케이션 명: " + props.getName());
            log.info(" > 애플리케이션 정보: " + props.getDescription());
        };
    }

    @Component
    @ConfigurationProperties(prefix = "myapp")
    public static class MyAppProperties {
        private String name;
        private String description;
        private String serverIp;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getServerIp() {
            return serverIp;
        }

        public void setServerIp(String serverIp) {
            this.serverIp = serverIp;
        }
    }

}
