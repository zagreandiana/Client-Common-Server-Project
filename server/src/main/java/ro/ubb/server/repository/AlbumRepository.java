package ro.ubb.server.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubb.common.model.Album;

import java.util.List;

public interface AlbumRepository extends Repository<Album, Long> {
    @Modifying
    @Query(value = "update songs set album_id=null where album_id=:albumId", nativeQuery = true)
    void prepareForDelete(@Param("albumId") Long albumId);

    List<Album> findAllByGenre(String genre);

    List<Album> findAllByOrderByTitleAsc();

    List<Album> findAllByOrderByTitleDesc();
}
