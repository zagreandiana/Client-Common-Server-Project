package ro.ubb.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "songs")
public class Song extends BaseEntity<Long> {

    private String title;
    @Column(name="album_id")
    private Integer albumId;
    @Column(name="duration")
    private Time time;

    public Song(Long id, String title, Integer albumId, Time time) {
        super(id);
        this.title = title;
        this.albumId = albumId;
        this.time = time;
    }

    public Song(String title, Integer albumId, Time time) {
        this.title = title;
        this.albumId = albumId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", album_id=" + albumId +
                ", time=" + time +
                ", id=" + id +
                '}';
    }
}
