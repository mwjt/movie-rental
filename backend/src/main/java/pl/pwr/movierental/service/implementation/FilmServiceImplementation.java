package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.FilmMapper;
import pl.pwr.movierental.model.dto.FilmDTO;
import pl.pwr.movierental.model.entity.Film;
import pl.pwr.movierental.repository.FilmRepository;
import pl.pwr.movierental.service.FilmService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImplementation implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper = FilmMapper.INSTANCE;

    public ResponseEntity<List<FilmDTO>> getAll() {
        List<Film> filmList = filmRepository.findAll();
        return ResponseEntity.ok(filmMapper.filmListToFilmDTOList(filmList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(filmMapper.filmToFilmDTO(film.get()));
    }

    @Transactional
    public ResponseEntity<?> add(FilmDTO film) {
        if (film == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Film newFilm = filmRepository.saveAndFlush(filmMapper.filmDTOToFilm(film));
        return ResponseEntity.status(HttpStatus.CREATED).body(filmMapper.filmToFilmDTO(newFilm));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, FilmDTO newFilm) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newFilm.setId(id);
        filmRepository.saveAndFlush(filmMapper.filmDTOToFilm(newFilm));
        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        film.ifPresent(filmRepository::delete);
        return ResponseEntity.ok("");
    }
}
