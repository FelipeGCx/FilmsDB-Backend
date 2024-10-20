package com.felipegcx.filmsDBMS.security.services;

import com.felipegcx.filmsDBMS.security.models.User;
import com.felipegcx.filmsDBMS.security.payload.request.LoginRequest;
import com.felipegcx.filmsDBMS.security.payload.request.SignupRequest;
import com.felipegcx.filmsDBMS.security.payload.response.JwtResponse;
import java.util.Set;

//*|||||||||||||*\\
//* AuthService *\\
//*|||||||||||||*\\

public interface AuthService {
  JwtResponse authenticateUser(LoginRequest loginRequest);

  JwtResponse registerUser(SignupRequest signUpRequest) throws Exception;

  User addRoleToUser(String username, Set<String> role) throws Exception;

  JwtResponse verifyToken(String token) throws Exception;
}
