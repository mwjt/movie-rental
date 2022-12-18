package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    ResponseEntity<List<FilmDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(FilmDTO film);
    ResponseEntity<?> change(Integer id, FilmDTO newFilm);
    ResponseEntity<String> delete(Integer id);
}
