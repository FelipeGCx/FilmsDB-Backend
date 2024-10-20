package com.felipegcx.filmsDBMS.services;

import java.util.List;
import com.felipegcx.filmsDBMS.models.Saga;

//*|||||||||||||*\\
//* SagaService *\\
//*|||||||||||||*\\

public interface SagaService {
  public Saga saveSaga(Saga category);

  public Saga updateSaga(Saga modSaga, Integer id);

  public String deleteSaga(Integer id);

  public Saga getSagaById(Integer id);

  public Saga getSagaByName(String name);

  public List<Saga> getSagas();
}
