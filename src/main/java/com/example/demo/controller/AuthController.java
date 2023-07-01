package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.ConstantMessage;
import com.example.demo.dto.AnggotaReq;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.Anggota;
import com.example.demo.model.Role;
import com.example.demo.model.Enum.ERole;
import com.example.demo.repository.AnggotaRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.impl.UserDetailsImpl;
import com.example.demo.util.JwtUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  AnggotaRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtil jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody AnggotaReq signUpRequest) {
    if (userRepository.findBynamaAnggotaContaining(signUpRequest.getUsername()).size() > 0) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse(ConstantMessage.USER_NAME_TAKEN));
    }

    if (userRepository.findByEmail(signUpRequest.getEmail()).size() > 0) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse(ConstantMessage.EMAIL_ALREADY_USE));
    }

    // Create new user's account
    signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
    Anggota user = new Anggota(signUpRequest);

    Set<String> strRoles = signUpRequest.getRole();
    Role role = new Role();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException(ConstantMessage.ROLE_NOT_FOUND));
      role.setRoleId(userRole.getRoleId());
    } else {
      strRoles.forEach(roleUser -> {
        switch (roleUser) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException(ConstantMessage.ROLE_NOT_FOUND));
          user.setRole(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException(ConstantMessage.ROLE_NOT_FOUND));
          user.setRole(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException(ConstantMessage.ROLE_NOT_FOUND));
          user.setRole(userRole);
        }
      });
    }    
    
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse(ConstantMessage.SUCCESS_REGISTER));
  }
}
