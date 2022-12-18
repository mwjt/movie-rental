package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.FilmDTO;
import pl.pwr.movierental.model.entity.Film;

import java.util.List;

@Mapper
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDTO filmToFilmDTO(Film film);
    Film filmDTOToFilm(FilmDTO filmDTO);
    List<FilmDTO> filmListToFilmDTOList(List<Film> filmList);
    List<Film> filmDTOListToFilmList(List<FilmDTO> filmDTOList);
}
