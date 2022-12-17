package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Film;
import pl.pwr.movierental.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        return filmService.getAll();
    }

    @PostMapping("/films")
    public ResponseEntity<?> createFilm(@RequestBody Film film) {
        return filmService.add(film);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Integer id) {
        return filmService.getById(id);
    }


    @PutMapping("/film/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Integer id, @RequestBody Film newFilm) {
        return filmService.change(id, newFilm);
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
        return filmService.delete(id);
    }
}
