package com.felipegcx.filmsDBMS.security.services.impl;

import com.felipegcx.filmsDBMS.security.jwt.JwtUtils;
import com.felipegcx.filmsDBMS.security.models.ERole;
import com.felipegcx.filmsDBMS.security.models.Role;
import com.felipegcx.filmsDBMS.security.models.User;
import com.felipegcx.filmsDBMS.security.payload.request.LoginRequest;
import com.felipegcx.filmsDBMS.security.payload.request.SignupRequest;
import com.felipegcx.filmsDBMS.security.payload.response.JwtResponse;
import com.felipegcx.filmsDBMS.security.repositories.RoleRepository;
import com.felipegcx.filmsDBMS.security.repositories.UserRepository;
import com.felipegcx.filmsDBMS.security.services.AuthService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//*|||||||||||||||||*\\
//* AuthServiceImpl *\\
//*|||||||||||||||||*\\

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public JwtResponse authenticateUser(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(),
        loginRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails
      .getAuthorities()
      .stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return new JwtResponse(
      jwt,
      userDetails.getId(),
      userDetails.getUsername(),
      userDetails.getEmail(),
      roles
    );
  }

  @Override
  public JwtResponse registerUser(SignupRequest signUpRequest)
    throws Exception {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new Exception("Username is already taken!");
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new Exception("Email is already taken!");
    }
    // if (userRepository.existsByUsername(signUpRequest.getUsername())) {
    //   return ResponseEntity
    //     .badRequest()
    //     .body(new MessageResponse("Error: Username is already taken!"));
    // }

    // if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    //   return ResponseEntity
    //     .badRequest()
    //     .body(new MessageResponse("Error: Email is already in use!"));
    // }

    // Create new user's account
    User user = new User(
      signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword())
    );
    // Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository
      .findByName(ERole.ROLE_USER)
      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    LoginRequest userLogin = new LoginRequest(
      signUpRequest.getUsername(),
      signUpRequest.getPassword()
    );
    JwtResponse response = authenticateUser(userLogin);
    return response;
  }

  @Override
  public User addRoleToUser(String username, Set<String> roles)
    throws Exception {
    if (!userRepository.existsByUsername(username)) {
      throw new Exception("Not Found User with username: " + username);
    }
    User user = userRepository.getByUsername(username);
    Set<Role> userRoles = user.getRoles();
    // Set<Role> userRoles = new HashSet<>();
    roles.forEach(
      role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository
              .findByName(ERole.ROLE_ADMIN)
              .orElseThrow(
                () -> new RuntimeException("Error: Role is not found.")
              );
            userRoles.add(adminRole);
            break;
          case "mod":
            Role modRole = roleRepository
              .findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(
                () -> new RuntimeException("Error: Role is not found.")
              );
            userRoles.add(modRole);
            break;
          default:
            Role userRole = roleRepository
              .findByName(ERole.ROLE_USER)
              .orElseThrow(
                () -> new RuntimeException("Error: Role is not found.")
              );
            userRoles.add(userRole);
        }
      }
    );
    user.setRoles(userRoles);
    User response = userRepository.save(user);
    return response;
  }
  // @Override
  // public User registerUser(SignupRequest signUpRequest) throws Exception {
  //   if (userRepository.existsByUsername(signUpRequest.getUsername())) {
  //     throw new Exception("Username is already taken!");
  //   }
  //   if (userRepository.existsByEmail(signUpRequest.getEmail())) {
  //     throw new Exception("Email is already taken!");
  //   }
  //   // if (userRepository.existsByUsername(signUpRequest.getUsername())) {
  //   //   return ResponseEntity
  //   //     .badRequest()
  //   //     .body(new MessageResponse("Error: Username is already taken!"));
  //   // }

  //   // if (userRepository.existsByEmail(signUpRequest.getEmail())) {
  //   //   return ResponseEntity
  //   //     .badRequest()
  //   //     .body(new MessageResponse("Error: Email is already in use!"));
  //   // }

  //   // Create new user's account
  //   User user = new User(
  //     signUpRequest.getUsername(),
  //     signUpRequest.getEmail(),
  //     encoder.encode(signUpRequest.getPassword())
  //   );

  //   Set<String> strRoles = signUpRequest.getRoles();
  //   Set<Role> roles = new HashSet<>();

  //   if (strRoles == null) {
  //     Role userRole = roleRepository
  //       .findByName(ERole.ROLE_USER)
  //       .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
  //     roles.add(userRole);
  //   } else {
  //     strRoles.forEach(
  //       role -> {
  //         switch (role) {
  //           case "admin":
  //             Role adminRole = roleRepository
  //               .findByName(ERole.ROLE_ADMIN)
  //               .orElseThrow(
  //                 () -> new RuntimeException("Error: Role is not found.")
  //               );
  //             roles.add(adminRole);

  //             break;
  //           case "mod":
  //             Role modRole = roleRepository
  //               .findByName(ERole.ROLE_MODERATOR)
  //               .orElseThrow(
  //                 () -> new RuntimeException("Error: Role is not found.")
  //               );
  //             roles.add(modRole);

  //             break;
  //           default:
  //             Role userRole = roleRepository
  //               .findByName(ERole.ROLE_USER)
  //               .orElseThrow(
  //                 () -> new RuntimeException("Error: Role is not found.")
  //               );
  //             roles.add(userRole);
  //         }
  //       }
  //     );
  //   }

  //   user.setRoles(roles);
  //   User response = userRepository.save(user);

  //   return response;
  // }
    @Override
    public JwtResponse verifyToken(String token)throws Exception {
      if (jwtUtils.validateJwtToken(token)){
        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.getByUsername(username);
        Set<Role> roles = user.getRoles();
        List<String> theRoles = new ArrayList<>();
        roles.forEach(
          role -> {
            theRoles.add(role.getName().toString());
          });
        JwtResponse response = new JwtResponse(
          token,
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          theRoles
        );
        return response;
      }
      else throw new Exception("Invalid Token");
    }
}
