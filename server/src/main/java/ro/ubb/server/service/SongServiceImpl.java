package ro.ubb.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.common.exceptions.ServiceException;
import ro.ubb.common.model.Song;
import ro.ubb.common.service.SongService;
import ro.ubb.common.utils.ExceptionMessages;
import ro.ubb.server.repository.SongRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public void create(Song entity) {
        log.debug("### Entering create song method.");
        songRepository.save(entity);
        log.debug("### Exiting create song method.");
    }

    @Override
    public Song readOne(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);

        log.debug("### Entering readOne song method.");
        if (songOptional.isEmpty()) {
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message);
        }
        Song song = songOptional.get();
        log.debug("### Exiting readOne song method.");

        return song;
    }

    @Override
    public List<Song> readAll() {
        log.debug("### Entering read songs method.");
        List<Song> songs = songRepository.findAll();
        log.debug("### Exiting read songs method.");

        return songs;
    }

    @Transactional
    @Override
    public void update(Song song) {
        log.debug("### Entering update song method.");
        Optional<Song> optional = songRepository.findById(song.getId());

        Song songUpdate = optional.orElseThrow(() -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        songUpdate.setTitle(song.getTitle());
        songUpdate.setAlbumId(songUpdate.getAlbumId());
        songUpdate.setTime(song.getTime());
        log.debug("### Exiting update song method.");
    }

    @Override
    public void delete(Long id) {
        log.debug("### Entering delete song method.");
        songRepository.deleteById(id);
        log.debug("### Exiting delete song method.");
    }

    @Override
    public List<Song> sortByLength() {
        log.debug("### Entering sort by length");
        List<Song> songs = songRepository.findByOrderByTimeDesc();
         log.debug("### Exiting sort by length");

        return songs;
    }

    @Override
    public Song findByTitle(String title) {
        return songRepository.findAllByTitle(title);
    }

    @Override
    public List<Song> groupSongsByAlbumId(Integer id) {
        return songRepository.findAllByAlbumId(id);
    }



}
