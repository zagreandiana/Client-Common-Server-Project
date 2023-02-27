package ro.ubb.common.service;

import ro.ubb.common.model.Band;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BandService {
    void create(Band band);

    Band readOne(Long id);

    List<Band> readAll();

    void update(Band band);

    void delete(Long id);

    List<Band> sortareAlfabetica();

    List<Band> activitateInceputaIntre(Date data1, Date data2);
}
