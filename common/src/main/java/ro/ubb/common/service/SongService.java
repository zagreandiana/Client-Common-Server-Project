package ro.ubb.common.service;

import com.sun.xml.bind.v2.model.core.ID;
import ro.ubb.common.model.Song;

import java.util.List;

public interface SongService {
    void create(Song song);

    Song readOne(Long id);

    List<Song> readAll();

    void update(Song song);

    void delete(Long id);

    List<Song> sortByLength();

    Song findByTitle(String title);

    List<Song> groupSongsByAlbumId(Integer id);
}
