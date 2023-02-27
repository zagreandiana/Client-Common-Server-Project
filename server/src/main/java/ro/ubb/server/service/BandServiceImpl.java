package ro.ubb.server.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.common.exceptions.ServiceException;
import ro.ubb.common.model.Band;

import ro.ubb.common.model.User;
import ro.ubb.common.service.BandService;
import ro.ubb.common.utils.ExceptionMessages;
import ro.ubb.server.repository.BandRepository;


import java.beans.Transient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public void create(Band band) {
        log.debug("### Entering create band method.");
        bandRepository.save(band);
        log.debug("### Exiting create band method.");
    }

    @Override
    public Band readOne(Long id) {
        log.debug("### Entering read band method.");
        Optional<Band> optional = bandRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }

        Band band = optional.get();
        log.debug("### Exiting read band method.");

        return band;
    }

    @Override
    public List<Band> readAll() {
        log.debug("### Entering read bands method.");
        List<Band> bands = bandRepository.findAll();
        log.debug("### Exiting read bands method.");

        return bands;
    }

    @Override
    @Transactional
    public void update(Band band) {
        log.debug("### Entering update band method.");
        Optional<Band> optional = bandRepository.findById(band.getId());

        Band bandToBeUpdated = optional.orElseThrow(() -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        bandToBeUpdated.setName(band.getName());
        bandToBeUpdated.setActivityStartData(band.getActivityStartData());
        bandToBeUpdated.setActivityEndData(band.getActivityEndData());

        log.debug("### Exiting update band method.");
    }

    @Override
    public void delete(Long id) {
        log.debug("### Entering delete band method.");
        bandRepository.deleteById(id);
        log.debug("### Exiting delete band method.");
    }

    @Override
    public List<Band> sortareAlfabetica() {
        return bandRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Band> activitateInceputaIntre(Date data1, Date data2) {
        return bandRepository.findByActivityStartDataBetween(data1, data2);
    }


}