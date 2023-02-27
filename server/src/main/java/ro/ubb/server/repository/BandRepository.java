package ro.ubb.server.repository;

import ro.ubb.common.model.Band;

import java.sql.Date;
import java.util.List;

public interface BandRepository extends Repository<Band, Long> {
    List<Band> findAllByOrderByNameAsc();

    List<Band> findByActivityStartDataBetween(Date startDate, Date endDate);
}