package tn.rnu.fst.etconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EtConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtConfigServerApplication.class, args);
    }

}
