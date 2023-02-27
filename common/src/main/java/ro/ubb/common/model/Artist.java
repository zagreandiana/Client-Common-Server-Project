package ro.ubb.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "artists")
public class Artist extends BaseEntity<Long> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "stage_name")
    private String stageName;
    @Column(name = "activity_start_date")
    private Date activityStartDate;
    @Column(name = "activity_end_date")
    private Date activityEndDate;

    public Artist(Long id, String firstName, String lastName, String stageName, Date activityStartDate, Date activityEndDate) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ",first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", stage_name='" + stageName + '\'' +
                ", activity_start_date=" + activityStartDate +
                ", activity_end_date=" + activityEndDate +
                '}';
    }
}
