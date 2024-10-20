package com.felipegcx.filmsDBMS.services.impl;

import com.felipegcx.filmsDBMS.exceptions.NotFoundException;
import com.felipegcx.filmsDBMS.models.Saga;
import com.felipegcx.filmsDBMS.repositories.SagaRepository;
import com.felipegcx.filmsDBMS.services.SagaService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//*|||||||||||||||||*\\
//* SagaServiceImpl *\\
//*|||||||||||||||||*\\

@Service
@Transactional
public class SagaServiceImpl implements SagaService {

  private final SagaRepository sagaRepo;

  public SagaServiceImpl(SagaRepository sagaRepo) {
    this.sagaRepo = sagaRepo;
  }

  @Override
  public Saga saveSaga(Saga saga) {
    // parse the saga name to capitalize case
    saga.setSaga(StringUtils.capitalize(saga.getSaga()));
    // find the last saga to set the new id
    Saga lastSaga = sagaRepo.findLast().get(0);
    Integer id = lastSaga.getId() + 1;
    saga.setId(id);
    // save the new saga
    Saga response = sagaRepo.save(saga);
    return response;
  }

  @Override
  public Saga updateSaga(Saga modSaga, Integer id) {
    // find the saga
    Saga saga = sagaRepo.findById(id).orElse(null);
    if (saga == null) {
      throw new NotFoundException("Saga not found");
    }
    // set the values modificated
    saga.setSaga(
        (modSaga.getSaga() == null) ? saga.getSaga() : modSaga.getSaga());
    saga.setSvg((modSaga.getSvg() == null) ? saga.getSvg() : modSaga.getSvg());
    // update saga
    Saga response = sagaRepo.save(saga);
    return response;
  }

  @Override
  public String deleteSaga(Integer id) {
    // find the saga
    Saga saga = sagaRepo.findById(id).orElse(null);
    if (saga == null) {
      throw new NotFoundException("Saga not found");
    }
    // delete saga
    sagaRepo.deleteById(id);
    return "Saga deleted";
  }

  @Override
  public Saga getSagaById(Integer id) {
    // find the saga
    Saga saga = sagaRepo.findById(id).orElse(null);
    if (saga == null) {
      throw new NotFoundException("Saga not found");
    }
    return saga;
  }

  @Override
  public Saga getSagaByName(String name) {
    Saga saga = sagaRepo.findByName(name);
    return saga;
  }

  @Override
  public List<Saga> getSagas() {
    // get all sagas
    List<Saga> response = sagaRepo.findAll();
    return response;
  }
}
