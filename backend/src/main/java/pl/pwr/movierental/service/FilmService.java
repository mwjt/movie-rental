package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.Film;
import pl.pwr.movierental.repository.FilmRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public ResponseEntity<List<Film>> getAll() {
        List<Film> filmList = filmRepository.findAll();
        return ResponseEntity.ok(filmList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(film.get());
    }

    @Transactional
    public ResponseEntity<?> add(Film film) {
        if (film == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Film newFilm = filmRepository.saveAndFlush(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, Film newFilm) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newFilm.setFilmId(id);
        filmRepository.saveAndFlush(newFilm);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<Film> film = filmRepository.findById(id);
        film.ifPresent(filmRepository::delete);
        return ResponseEntity.ok("");
    }
}
