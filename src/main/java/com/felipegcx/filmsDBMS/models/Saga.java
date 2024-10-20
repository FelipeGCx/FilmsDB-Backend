package com.felipegcx.filmsDBMS.models;

import org.springframework.data.annotation.Id;

//*|||||||||||*\\
//* SagaModel *\\
//*|||||||||||*\\

public class Saga {

  @Id
  private Integer id;

  private String saga;
  private String svg;

  public Saga() {}

  public Saga(Integer id, String saga) {
    this.id = id;
    this.saga = saga;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSaga() {
    return saga;
  }

  public void setSaga(String saga) {
    this.saga = saga;
  }

  public String getSvg() {
    return svg;
  }

  public void setSvg(String svg) {
    this.svg = svg;
  }
}
