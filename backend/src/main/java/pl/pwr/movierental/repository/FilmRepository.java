package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
