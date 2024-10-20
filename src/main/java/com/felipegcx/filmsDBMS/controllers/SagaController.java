package com.felipegcx.filmsDBMS.controllers;

import com.felipegcx.filmsDBMS.models.Saga;
import com.felipegcx.filmsDBMS.services.FormatingService;
import com.felipegcx.filmsDBMS.services.SagaService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//*||||||||||||||||*\\
//* SagaController *\\
//*||||||||||||||||*\\

@RestController
@RequestMapping("/api/v1")
public class SagaController {

  private final SagaService sagaService;
  private final FormatingService formatService;

  public SagaController(
    SagaService sagaService,
    FormatingService formatService
  ) {
    this.sagaService = sagaService;
    this.formatService = formatService;
  }

  @PostMapping("/create/saga")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> newSaga(@RequestBody Saga saga) {
    Saga result = sagaService.saveSaga(saga);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/saga/id={id}")
  ResponseEntity<?> getSaga(@PathVariable Integer id) {
    Saga result = sagaService.getSagaById(id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/saga/name={name}")
  ResponseEntity<?> getSagaName(@PathVariable String name) {
    Saga result = sagaService.getSagaByName(name);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/sagas")
  ResponseEntity<?> getSagas() {
    List<Saga> results = sagaService.getSagas();
    Map<String, Object> response = formatService.formatData(results);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/update/saga/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> updateSaga(
    @RequestBody Saga modSaga,
    @PathVariable Integer id
  ) {
    Saga result = sagaService.updateSaga(modSaga, id);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/delete/saga/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<?> deleteSaga(@PathVariable Integer id) {
    String result = sagaService.deleteSaga(id);
    Map<String, Object> response = formatService.formatMessageResponse(
      result,
      false
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
