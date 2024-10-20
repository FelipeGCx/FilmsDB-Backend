package com.felipegcx.filmsDBMS.controllers;

import com.felipegcx.filmsDBMS.models.Films;
import com.felipegcx.filmsDBMS.services.FilmService;
import com.felipegcx.filmsDBMS.services.FormatingService;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//*||||||||||||||||*\\
//* FilmController *\\
//*||||||||||||||||*\\

@RestController
@RequestMapping("/api/v1")
public class FilmController {

  private final FilmService filmService;
  private final FormatingService formatService;
  private final String nPage = "0";
  private final String nSize = "30";
  private final String nYear = "2022";
  private final String nNote = "none";

  public FilmController(
    FilmService filmService,
    FormatingService formatService
  ) {
    this.filmService = filmService;
    this.formatService = formatService;
  }

  // ~Create Film
  @PostMapping("/create/film")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> newFilm(@RequestBody Films film) {
    Films result = filmService.saveFilm(film);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  // ~Create Multiple Film
  @PostMapping("/create/films")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> newFilms(@RequestBody List<Films> films) {
    String result = filmService.saveFilms(films);
    Map<String, Object> response = formatService.formatMessageResponse(
      result,
      false
    );
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  // ~Update Film
  @PutMapping("/update/film/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> updateFilm(
    @RequestBody Films modFilm,
    @PathVariable Integer id
  ) {
    Films result = filmService.updateFilm(modFilm, id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // ~Delete Film
  @DeleteMapping("/delete/film/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
    String result = filmService.deleteFilm(id);
    Map<String, Object> response = formatService.formatMessageResponse(
      result,
      false
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  //^\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
  // ~Get Film by Id
  @GetMapping("/film/id/{id}")
  ResponseEntity<?> getFilmById(@PathVariable Integer id) {
    Films result = filmService.getFilmById(id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // ~Get Film start with title
  @GetMapping("/film/title/{title}")
  ResponseEntity<?> getFilmsByTitle(@PathVariable String title) {
    List<Films> result = filmService.getFilmsByTitle(title);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // ~Get All Film
  @GetMapping("/films")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> getFilms(
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilms(page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // ~Get Charts Film
  @GetMapping("/charts")
  ResponseEntity<?> getCharts() {
    List<Object> result = filmService.getCharts();
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/year={year}/order={order}")
  ResponseEntity<?> getFilmYear(
    @PathVariable Integer year,
    @PathVariable String order,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByYear(year, order, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/note={note}/order={order}")
  ResponseEntity<?> getFilmNote(
    @PathVariable Double note,
    @PathVariable String order,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByNote(note, order, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/category={category}")
  ResponseEntity<?> getFilmCategory(
    @PathVariable String category,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByCategory(category, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/language={language}")
  ResponseEntity<?> getFilmLanguage(
    @PathVariable Boolean language,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByLanguage(language, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/favorite={favorite}")
  ResponseEntity<?> getFilmFavorite(
    @PathVariable Boolean favorite,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByFavorites(favorite, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/saga={saga}")
  ResponseEntity<?> getFilmBySaga(
    @PathVariable String saga,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsBySaga(saga, page, size);
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}/year={year}/order={order}")
  ResponseEntity<?> getByTypeAndYear(
    @PathVariable String type,
    @PathVariable Integer year,
    @PathVariable String order,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByTypeAndYear(
      type,
      year,
      order,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}/note={note}/order={order}")
  ResponseEntity<?> getByTypeAndNote(
    @PathVariable String type,
    @PathVariable Double note,
    @PathVariable String order,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByTypeAndNote(
      type,
      note,
      order,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}/category={category}")
  ResponseEntity<?> getByTypeAndCategory(
    @PathVariable String category,
    @PathVariable String type,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByTypeAndCategory(
      type,
      category,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}/language={language}")
  ResponseEntity<?> getByTypeAndLanguage(
    @PathVariable Boolean language,
    @PathVariable String type,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByTypeAndLanguage(
      type,
      language,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}/favorite={favorite}")
  ResponseEntity<?> getByTypeAndFavorite(
    @PathVariable Boolean favorite,
    @PathVariable String type,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size
  ) {
    Page<Films> result = filmService.getFilmsByTypeAndFavorites(
      type,
      favorite,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/films/type={type}")
  ResponseEntity<?> getFilmByType(
    @PathVariable String type,
    @RequestParam(defaultValue = nPage) Integer page,
    @RequestParam(defaultValue = nSize) Integer size,
    @RequestParam(defaultValue = nYear) Integer year,
    @RequestParam(defaultValue = nNote) String note
  ) {
    Page<Films> result = filmService.getFilmsByType(
      type,
      year,
      note,
      page,
      size
    );
    Map<String, Object> response = formatService.formatPageableResponse(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
