package ro.ubb.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("ro.ubb.server.config");
    }
}
