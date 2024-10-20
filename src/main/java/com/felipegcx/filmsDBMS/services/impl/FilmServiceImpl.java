package com.felipegcx.filmsDBMS.services.impl;

import com.felipegcx.filmsDBMS.exceptions.NotFoundException;
import com.felipegcx.filmsDBMS.models.Categories;
import com.felipegcx.filmsDBMS.models.Films;
import com.felipegcx.filmsDBMS.models.Saga;
import com.felipegcx.filmsDBMS.repositories.CategoryRepository;
import com.felipegcx.filmsDBMS.repositories.FilmRepository;
import com.felipegcx.filmsDBMS.repositories.SagaRepository;
import com.felipegcx.filmsDBMS.services.FilmService;
import com.felipegcx.filmsDBMS.services.FormatingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//*|||||||||||||||||*\\
//* FilmServiceImpl *\\
//*|||||||||||||||||*\\

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

  private final FilmRepository filmRepo;
  private final CategoryRepository categoryRepo;
  private final SagaRepository sagaRepo;
  private final FormatingService formatService;

  public FilmServiceImpl(
      FilmRepository filmRepo,
      CategoryRepository categoryRepo,
      SagaRepository sagaRepo,
      FormatingService formatService) {
    this.filmRepo = filmRepo;
    this.categoryRepo = categoryRepo;
    this.sagaRepo = sagaRepo;
    this.formatService = formatService;
  }

  @Override
  public Films saveFilm(Films film) {
    // find the last film to set new id
    Films lastFilm = filmRepo.findLast().get(0);
    Integer id = lastFilm.getId() + 1;
    film.setId(id);
    // save the film
    Films response = filmRepo.save(film);
    return response;
  }

  @Override
  public String saveFilms(List<Films> films) {
    // for each item call the saveFilm method to save the films
    // this method is only to migrate data
    for (Films item : films) {
      saveFilm(item);
    }
    return "Film created";
  }

  @Override
  public Films updateFilm(Films modFilm, Integer id) {
    // find the film
    Films film = filmRepo.findById(id).orElse(null);
    if (film == null) {
      throw new NotFoundException("Film not found");
    }
    // set the values modificated
    film.setType(
        (modFilm.getType() == null) ? film.getType() : modFilm.getType());
    film.setTitle(
        (modFilm.getTitle() == null) ? film.getTitle() : modFilm.getTitle());
    film.setTitleOG(
        (modFilm.getTitleOG() == null) ? film.getTitleOG() : modFilm.getTitleOG());
    film.setYear(
        (modFilm.getYear() == null) ? film.getYear() : modFilm.getYear());
    film.setNote(
        (modFilm.getNote() == null) ? film.getNote() : modFilm.getNote());
    film.setLanguage(
        (modFilm.getLanguage() == null)
            ? film.getLanguage()
            : modFilm.getLanguage());
    film.setCategory(
        (modFilm.getCategory() == null)
            ? film.getCategory()
            : modFilm.getCategory());
    film.setFavorite(
        (modFilm.getFavorite() == null)
            ? film.getFavorite()
            : modFilm.getFavorite());
    film.setInfo(
        (modFilm.getInfo() == null) ? film.getInfo() : modFilm.getInfo());
    film.setPoster(
        (modFilm.getPoster() == null) ? film.getPoster() : modFilm.getPoster());
    film.setLink(
        (modFilm.getLink() == null) ? film.getLink() : modFilm.getLink());
    film.setSeason(
        (modFilm.getSeason() == null) ? film.getSeason() : modFilm.getSeason());
    film.setSaga(
        (modFilm.getSaga() == null) ? film.getSaga() : modFilm.getSaga());
    // update the film
    Films response = filmRepo.save(film);
    return response;
  }

  @Override
  public String deleteFilm(Integer id) {
    // find the film
    Films film = filmRepo.findById(id).orElse(null);
    if (film == null) {
      throw new NotFoundException("Film not found");
    }
    // delete film
    filmRepo.deleteById(id);
    return "Film deleted";
  }

  @Override
  public Films getFilmById(Integer id) {
    // find the film
    Films film = filmRepo.findById(id).orElse(null);
    if (film == null) {
      throw new NotFoundException("Film not found");
    }
    return film;
  }

  @Override
  public List<Films> getFilmsByTitle(String title) {
    // i have some problems with regex startwith and fix with this
    // create array to save the results
    List<Films> results = new ArrayList<Films>();
    // get the films that contain the title
    List<Films> films = filmRepo.findByTitle(title);
    for (Films item : films) {
      // compared if the film start with title
      if (item.getTitle().toLowerCase().startsWith(title.toLowerCase()) ||
          item.getTitleOG().toLowerCase().startsWith(title.toLowerCase())) {
        results.add(item);
      }
    }
    // compared if results hace anyone value inside to create a response
    if (results.size() > 0) {
      // create response map
      return results;
    } else {
      // if results array is void throw exception
      throw new NotFoundException("Film not found");
    }
  }

  @Override
  public Page<Films> getFilms(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results = filmRepo.findAllSortByYear(pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByYear(
      Integer year,
      String order,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    Page<Films> results;
    // get the results page you want to display
    if (order.equals("asc")) {
      results = filmRepo.findByYearASC(year + 1, pageable);
    } else {
      results = filmRepo.findByYearDESC(year + 1, pageable);
    }
    return results;
  }

  @Override
  public Page<Films> getFilmsByNote(
      Double note,
      String order,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results;
    if (order.equals("asc")) {
      results = filmRepo.findByNoteASC(note + .1, pageable);
    } else {
      results = filmRepo.findByNoteDESC(note + .1, pageable);
    }
    return results;
  }

  @Override
  public Page<Films> getFilmsByCategory(
      String category,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the category you want to get the id
    Categories categoryObj = categoryRepo.findByName(category);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByCategory(
        categoryObj.getId(),
        pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsBySaga(String saga, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the saga you want to get the id
    Saga sagaObj = sagaRepo.findByName(saga);
    // get the results page you want to display
    Page<Films> results = filmRepo.findBySaga(sagaObj.getId(), pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByLanguage(
      Boolean language,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByLanguage(language, pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByFavorites(
      Boolean favorite,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByFavorite(favorite, pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByType(
      String type,
      Integer year,
      String note,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    year++;
    type = StringUtils.capitalize(type);

    if (note.equalsIgnoreCase("desc")) {
      Page<Films> results = (type.equalsIgnoreCase("all"))
          ? filmRepo.findAllFullDESC(year, pageable)
          : filmRepo.findByTypeFullDESC(type, year, pageable);
      return results;
    } else if (note.equalsIgnoreCase("asc")) {
      Page<Films> results = (type.equalsIgnoreCase("all"))
          ? filmRepo.findAllFullASC(year, pageable)
          : filmRepo.findByTypeFullASC(type, year, pageable);
      return results;
    } else {
      Page<Films> results = (type.equalsIgnoreCase("all"))
          ? filmRepo.findAllFullNoNote(year, pageable)
          : filmRepo.findByTypeFullNoNote(type, year, pageable);
      return results;
    }
  }

  @Override
  public Page<Films> getFilmsByTypeAndYear(
      String type,
      Integer year,
      String order,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results;
    if (order.equals("asc")) {
      results = filmRepo.findByTypeAndYearASC(
          StringUtils.capitalize(type),
          year + 1,
          pageable);
    } else {
      results = filmRepo.findByTypeAndYearDESC(
          StringUtils.capitalize(type),
          year + 1,
          pageable);
    }
    return results;
  }

  @Override
  public Page<Films> getFilmsByTypeAndNote(
      String type,
      Double note,
      String order,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results;
    if (order.equals("asc")) {
      results = filmRepo.findByTypeAndNoteASC(
          StringUtils.capitalize(type),
          note + .1,
          pageable);
    } else {
      results = filmRepo.findByTypeAndNoteDESC(
          StringUtils.capitalize(type),
          note + .1,
          pageable);
    }
    return results;
  }

  @Override
  public Page<Films> getFilmsByTypeAndCategory(
      String type,
      String category,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the category you want to get the id
    Categories categoryObj = categoryRepo.findByName(category);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByTypeAndCategory(
        StringUtils.capitalize(type),
        categoryObj.getId(),
        pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByTypeAndLanguage(
      String type,
      Boolean language,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByTypeAndLanguage(
        StringUtils.capitalize(type),
        language,
        pageable);
    return results;
  }

  @Override
  public Page<Films> getFilmsByTypeAndFavorites(
      String type,
      Boolean favorite,
      Integer page,
      Integer size) {
    Pageable pageable = PageRequest.of(formatService.formatPage(page), size);
    // get the results page you want to display
    Page<Films> results = filmRepo.findByTypeAndFavorite(
        StringUtils.capitalize(type),
        favorite,
        pageable);
    return results;
  }

  @Override
  public List<Object> getCharts() {
    Set<String> types = filmRepo.findUniqueTypes();
    List<Object> response = new ArrayList<Object>();
    for (String type : types) {
      if (type != null) {
        long elements = filmRepo.countByType(type);
        System.out.println(elements);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("type", type);
        res.put("elements", elements);
        response.add(res);
      }
    }
    return response;
  }
}
