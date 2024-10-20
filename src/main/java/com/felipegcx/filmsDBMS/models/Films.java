package com.felipegcx.filmsDBMS.models;

import org.springframework.data.annotation.Id;

//*|||||||||||*\\
//* FilmModel *\\
//*|||||||||||*\\

public class Films {

  @Id
  private Integer id;

  private String type;
  private String titleOG;
  private String title;
  private Integer year;
  private Double note;
  private Boolean language;
  private Boolean favorite;
  private Integer category;
  private String info;
  private String poster;
  private Integer season;
  private String link;
  private Integer saga;

  public Films() {}

  public Films(
    Integer id,
    String type,
    String titleOG,
    String title,
    Integer year,
    Double note,
    Boolean language,
    Boolean favorite,
    Integer category,
    String info,
    String poster,
    Integer season,
    String link,
    Integer saga
  ) {
    this.id = id;
    this.type = type;
    this.titleOG = titleOG;
    this.title = title;
    this.year = year;
    this.note = note;
    this.language = language;
    this.favorite = favorite;
    this.category = category;
    this.info = info;
    this.poster = poster;
    this.season = season;
    this.link = link;
    this.saga = saga;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitleOG() {
    return titleOG;
  }

  public void setTitleOG(String titleOG) {
    this.titleOG = titleOG;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Double getNote() {
    return note;
  }

  public void setNote(Double note) {
    this.note = note;
  }

  public Boolean getLanguage() {
    return language;
  }

  public void setLanguage(Boolean language) {
    this.language = language;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(Boolean favorite) {
    this.favorite = favorite;
  }

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public Integer getSeason() {
    return season;
  }

  public void setSeason(Integer season) {
    this.season = season;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Integer getSaga() {
    return saga;
  }

  public void setSaga(Integer saga) {
    this.saga = saga;
  }
}
