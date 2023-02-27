package ro.ubb.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "albums")
public class Album extends BaseEntity<Long> {

    private String title;
    private String genre;
    private Long artist_id;
    private Long band_id;
    private Date release_date;

    public Album(Long aLong, String title, String genre, Long artist_id, Long band_id, Date release_date) {
        super(aLong);
        this.title = title;
        this.genre = genre;
        this.artist_id = artist_id;
        this.band_id = band_id;
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Albums{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", artist_id=" + artist_id +
                ", band_id=" + band_id +
                ", release_date=" + release_date +
                '}';
    }
}
