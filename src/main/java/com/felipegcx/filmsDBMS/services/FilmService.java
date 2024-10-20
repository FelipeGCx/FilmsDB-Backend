package com.felipegcx.filmsDBMS.services;

import java.util.List;
import com.felipegcx.filmsDBMS.models.Films;
import org.springframework.data.domain.Page;

//*|||||||||||||*\\
//* FilmService *\\
//*|||||||||||||*\\

public interface FilmService {
  public Films saveFilm(Films film);

  public String saveFilms(List<Films> films);

  public Films updateFilm(Films modFilm, Integer id);

  public String deleteFilm(Integer id);

  public Films getFilmById(Integer id);

  public List<Films> getFilmsByTitle(String title);

  public Page<Films> getFilms(Integer page, Integer size);

  public Page<Films> getFilmsByType(
      String type,
      Integer year,
      String note,
      Integer page,
      Integer size);

  public List<Object> getCharts();

  public Page<Films> getFilmsByYear(
      Integer year,
      String order,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByNote(
      Double note,
      String order,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByCategory(
      String category,
      Integer page,
      Integer size);

  public Page<Films> getFilmsBySaga(String saga, Integer page, Integer size);

  public Page<Films> getFilmsByLanguage(
      Boolean language,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByFavorites(
      Boolean favorite,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByTypeAndYear(
      String type,
      Integer year,
      String order,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByTypeAndNote(
      String type,
      Double note,
      String order,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByTypeAndCategory(
      String type,
      String category,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByTypeAndLanguage(
      String type,
      Boolean language,
      Integer page,
      Integer size);

  public Page<Films> getFilmsByTypeAndFavorites(
      String type,
      Boolean favorite,
      Integer page,
      Integer size);
}
