package com.felipegcx.filmsDBMS.models;

import org.springframework.data.annotation.Id;

//*|||||||||||||||*\\
//* CategoryModel *\\
//*|||||||||||||||*\\

public class Categories {

  @Id
  private Integer id;

  private String category;
  private String svg;

  public Categories() {}

  public Categories(Integer id, String category) {
    this.id = id;
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSvg() {
    return svg;
  }

  public void setSvg(String svg) {
    this.svg = svg;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
