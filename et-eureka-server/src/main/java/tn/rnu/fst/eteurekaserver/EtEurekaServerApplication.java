package tn.rnu.fst.eteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EtEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtEurekaServerApplication.class, args);
    }

}
