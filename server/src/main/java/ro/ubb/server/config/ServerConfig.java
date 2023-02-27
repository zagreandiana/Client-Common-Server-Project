package ro.ubb.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ro.ubb.server.repository", "ro.ubb.server.service"})
public class ServerConfig {


}
