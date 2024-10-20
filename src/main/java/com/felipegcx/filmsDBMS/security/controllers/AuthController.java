package com.felipegcx.filmsDBMS.security.controllers;

import com.felipegcx.filmsDBMS.security.models.User;
import com.felipegcx.filmsDBMS.security.payload.request.AddRoleRequest;
import com.felipegcx.filmsDBMS.security.payload.request.LoginRequest;
import com.felipegcx.filmsDBMS.security.payload.request.SignupRequest;
import com.felipegcx.filmsDBMS.security.payload.request.VerifyTokenRequest;
import com.felipegcx.filmsDBMS.security.payload.response.JwtResponse;
import com.felipegcx.filmsDBMS.security.services.AuthService;
import com.felipegcx.filmsDBMS.services.FormatingService;
import java.util.Map;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//*||||||||||||||||*\\
//* AuthController *\\
//*||||||||||||||||*\\

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  AuthService authService;

  @Autowired
  FormatingService formatService;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(
      @Valid @RequestBody LoginRequest loginRequest) {
    JwtResponse result = authService.authenticateUser(loginRequest);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(
      @Valid @RequestBody SignupRequest signUpRequest)
      throws Exception {
    JwtResponse result = authService.registerUser(signUpRequest);
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/addroletouser")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> addRoleToUser(
      @Valid @RequestBody AddRoleRequest addRoleRequest)
      throws Exception {
    User result = authService.addRoleToUser(
        addRoleRequest.getUsername(),
        addRoleRequest.getRoles());
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/verifytoken")
  public ResponseEntity<?> verifyToken(@Valid @RequestBody VerifyTokenRequest token) throws Exception {
    JwtResponse result = authService.verifyToken(token.getToken());
    Map<String, Object> response = formatService.formatData(result);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
