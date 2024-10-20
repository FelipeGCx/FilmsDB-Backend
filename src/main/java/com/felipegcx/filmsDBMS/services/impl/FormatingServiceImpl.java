package com.felipegcx.filmsDBMS.services.impl;

import com.felipegcx.filmsDBMS.services.FormatingService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//*||||||||||||||||||||||*\\
//* FormatingServiceImpl *\\
//*||||||||||||||||||||||*\\

@Service
@RequiredArgsConstructor
@Transactional
public class FormatingServiceImpl implements FormatingService {

  @Override
  public Map<String, Object> formatData(Object results) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("error", false);
    response.put("data", results);
    return response;
  }

  @Override
  public Map<String, Object> formatPageable(Page<?> results) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("totalItems", results.getTotalElements());
    response.put("totalPages", results.getTotalPages());
    response.put("size", results.getSize());
    response.put("currentPage", results.getNumber() + 1);
    return response;
  }

  @Override
  public Map<String, Object> formatPageableResponse(Page<?> results) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("error", false);
    response.put("data", results.getContent());
    response.put("page", formatPageable(results));
    return response;
  }

  @Override
  public Map<String, Object> formatMessageResponse(
      String message,
      Boolean error) {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("error", error);
    response.put("message", message);
    return response;
  }

  @Override
  public Integer formatPage(Integer page) {
    return page == 0 ? 0 : page - 1;
  }
}
