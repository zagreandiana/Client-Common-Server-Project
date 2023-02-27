package ro.ubb.server.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.common.service.*;
import ro.ubb.server.service.*;

@Configuration
@Import({JPAConfig.class})
@PropertySources({@PropertySource(value = "classpath:local/db.properties")})
public class AppLocalConfig {
    /**
     * Enables placeholders usage with SpEL expressions.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    RmiServiceExporter userServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("userService");
        exporter.setServiceInterface(UserService.class);
        exporter.setService(userService());

        return exporter;
    }

    @Bean
    RmiServiceExporter artistServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("artistService");
        exporter.setServiceInterface(ArtistService.class);
        exporter.setService(artistService());

        return exporter;
    }

    @Bean
    RmiServiceExporter bandServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("bandService");
        exporter.setServiceInterface(BandService.class);
        exporter.setService(bandService());

        return exporter;
    }

    @Bean
    RmiServiceExporter songServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("songService");
        exporter.setServiceInterface(SongService.class);
        exporter.setService(songService());

        return exporter;
    }

    @Bean
    RmiServiceExporter albumServiceExporter() {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("albumService");
        exporter.setServiceInterface(AlbumService.class);
        exporter.setService(albumService());

        return exporter;
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    SongService songService() {
        return new SongServiceImpl();
    }

    @Bean
    AlbumService albumService() {
        return new AlbumServiceImpl();
    }

    @Bean
    ArtistService artistService() {
        return new ArtistServiceImpl();
    }

    @Bean
    BandService bandService() {return new BandServiceImpl();}
}
