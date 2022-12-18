package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.movierental.model.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
