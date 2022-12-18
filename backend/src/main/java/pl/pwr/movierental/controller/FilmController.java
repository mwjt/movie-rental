package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.FilmDTO;
import pl.pwr.movierental.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/films/")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Operation(summary = "Get all films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of films", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/all")
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return filmService.getAll();
    }

    @Operation(summary = "Get film by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable Integer id) {
        return filmService.getById(id);
    }

    @Operation(summary = "Add a new film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Film", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Film not given", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> createFilm(@RequestBody FilmDTO film) {
        return filmService.add(film);
    }

    @Operation(summary = "Update film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Film", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Film not given", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable Integer id, @RequestBody FilmDTO newFilm) {
        return filmService.change(id, newFilm);
    }

    @Operation(summary = "Delete address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Integer id) {
        return filmService.delete(id);
    }
}
