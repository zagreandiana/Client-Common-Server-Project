package ro.ubb.server.repository;

import com.sun.xml.bind.v2.model.core.ID;
import ro.ubb.common.model.BaseEntity;
import ro.ubb.common.model.Song;

import java.util.List;

public interface SongRepository extends Repository<Song, Long> {
    List<Song> findByOrderByTimeDesc();

    Song findAllByTitle(String title);

    List<Song> findAllByAlbumId(Integer id);
}
