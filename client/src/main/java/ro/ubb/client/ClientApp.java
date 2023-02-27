package ro.ubb.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.client.ui.TextUi;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.client.config");

        TextUi textUi = context.getBean(TextUi.class);
        textUi.start();
    }
}