package ro.ubb.common.service;

import ro.ubb.common.model.Artist;

import java.util.List;

public interface ArtistService {
    void create(Artist artist);

    Artist readOne(Long id);

    List<Artist> readAll();

    void update(Artist artist);

    void delete(Long id);

    List<Artist> alphabeticalSortByFirstName();

    List<Artist> sortArtistsByStartDateActivity();
}
