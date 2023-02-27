package ro.ubb.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "bands")
public class Band extends BaseEntity<Long> {

    private String name;
    @Column(name = "activity_start_date")
    private Date activityStartData;
    @Column(name = "activity_end_date")
    private Date activityEndData;


    public Band(Long id, String name, Date activityStartData, Date activityEndData) {
        super(id);
        this.name = name;
        this.activityStartData = activityStartData;
        this.activityEndData = activityEndData;
    }


    @Override
    public String toString() {
        return "Band{" +
                "name='" + name + '\'' +
                ", activityStartData=" + activityStartData +
                ", activityEndData=" + activityEndData +
                ", id=" + id +
                '}';
    }
}
