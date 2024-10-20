package com.felipegcx.filmsDBMS.services;

import java.util.Map;
import org.springframework.data.domain.Page;

//*||||||||||||||||||*\\
//* FormatingService *\\
//*||||||||||||||||||*\\

public interface FormatingService {
  public Map<String, Object> formatData(Object results);

  public Map<String, Object> formatPageable(Page<?> results);

  public Map<String, Object> formatPageableResponse(Page<?> results);

  public Map<String, Object> formatMessageResponse(
    String message,
    Boolean error
  );

  public Integer formatPage(Integer page);
}
