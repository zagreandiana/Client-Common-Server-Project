package ro.ubb.server.repository;

import ro.ubb.common.model.Artist;

import java.sql.Date;
import java.util.List;

public interface ArtistRepository  extends Repository<Artist, Long>{


    List<Artist> findAllByOrderByActivityStartDateAsc();

    List<Artist> findAllByOrderByFirstNameAsc();


}
