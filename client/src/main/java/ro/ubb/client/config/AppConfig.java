package ro.ubb.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.client.ui.TextUi;
import ro.ubb.common.service.*;

@Configuration
public class AppConfig {

    @Bean
    TextUi textUi() {
        return new TextUi();
    }

    @Bean
    RmiProxyFactoryBean userServiceInvoker() {
        RmiProxyFactoryBean invoker = new RmiProxyFactoryBean();

        invoker.setServiceInterface(UserService.class);
        invoker.setServiceUrl("rmi://localhost:1099/userService");

        return invoker;
    }

    @Bean
    RmiProxyFactoryBean artistServiceInvoker() {
        RmiProxyFactoryBean invoker = new RmiProxyFactoryBean();

        invoker.setServiceInterface(ArtistService.class);
        invoker.setServiceUrl("rmi://localhost:1099/artistService");

        return invoker;
    }

    @Bean
    RmiProxyFactoryBean bandServiceInvoker() {
        RmiProxyFactoryBean invoker = new RmiProxyFactoryBean();

        invoker.setServiceInterface(BandService.class);
        invoker.setServiceUrl("rmi://localhost:1099/bandService");

        return invoker;
    }

    @Bean
    RmiProxyFactoryBean songServiceInvoker() {
        RmiProxyFactoryBean invoker = new RmiProxyFactoryBean();

        invoker.setServiceInterface(SongService.class);
        invoker.setServiceUrl("rmi://localhost:1099/songService");

        return invoker;
    }

    @Bean
    RmiProxyFactoryBean albumServiceInvoker() {
        RmiProxyFactoryBean invoker = new RmiProxyFactoryBean();

        invoker.setServiceInterface(AlbumService.class);
        invoker.setServiceUrl("rmi://localhost:1099/albumService");

        return invoker;
    }
}
