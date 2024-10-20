package com.felipegcx.filmsDBMS.security.payload.request;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;

//*||||||||||||||||*\\
//* AddRoleRequest *\\
//*||||||||||||||||*\\

public class AddRoleRequest {

  @NotBlank
  private String username;

  private Set<String> roles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<String> getRoles() {
    return this.roles;
  }

  public void setRole(Set<String> roles) {
    this.roles = roles;
  }
}
