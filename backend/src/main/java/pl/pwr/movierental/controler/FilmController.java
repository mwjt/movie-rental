package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Film;
import pl.pwr.movierental.repository.FilmRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Integer id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalFilm.get());
    }


    @PutMapping("/film/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Integer id, @RequestBody Film newFilm) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) return ResponseEntity.notFound().build();

        Film film = optionalFilm.get();
        film.setAmount(newFilm.getAmount());
        film.setName(newFilm.getName());
        film.setDescription(newFilm.getDescription());
        film.setPrice(newFilm.getPrice());
        film.setPricePerMonth(newFilm.getPricePerMonth());

        Film updatedFilm = filmRepository.save(film);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFilm(@PathVariable Integer id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) return ResponseEntity.notFound().build();

        filmRepository.delete(optionalFilm.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
