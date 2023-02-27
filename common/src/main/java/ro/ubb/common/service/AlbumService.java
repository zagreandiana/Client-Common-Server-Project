package ro.ubb.common.service;

import ro.ubb.common.model.Album;

import java.util.List;

public interface AlbumService {

    void create(Album album);

    Album readOne(Long id);

    List<Album> readAll();

    void update(Album album);

    void delete(Long id);

    List<Album> filterByGenre(String genre);

    List<Album> sortAsc(String sortValue);
}
