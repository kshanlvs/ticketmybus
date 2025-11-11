package com.booking.ticketmybus.controller.auth;

import com.booking.ticketmybus.dto.shared.LoginRequestDTO;
import com.booking.ticketmybus.dto.shared.LoginResponseDTO;
import com.booking.ticketmybus.dto.shared.RegisterResponseDTO;
import com.booking.ticketmybus.dto.shared.UserRegisterDTO;
import com.booking.ticketmybus.service.AuthService;
import com.booking.ticketmybus.service.shared.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {


    private final AuthService authService;

    AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {

            var createdUser = authService.register(userRegisterDTO);


            RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(
                    createdUser.getName(),
                    createdUser.getEmail(),
                    "User registered successfully",
                    true
            );
            return ResponseEntity.ok(registerResponseDTO);

        } catch (RuntimeException e) {
            RegisterResponseDTO errorResponse = new RegisterResponseDTO(
                    null,
                    null,
                    e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            RegisterResponseDTO errorResponse = new RegisterResponseDTO(
                    null,
                    null,
                    "Registration failed due to server error",
                    false
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            return ResponseEntity.ok(authService.login(loginRequestDTO));
        } catch (RuntimeException e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO();
            errorResponse.setErrorMsg(e.getMessage());
            errorResponse.setSuccess(false);
            HttpStatus status = determineHttpStatus(e.getMessage());
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    private HttpStatus determineHttpStatus(String errorMessage) {
        if (errorMessage.contains("not registered") || errorMessage.contains("Invalid password")) {
            return HttpStatus.UNAUTHORIZED; // 401
        } else if (errorMessage.contains("not active") || errorMessage.contains("suspended")) {
            return HttpStatus.FORBIDDEN; // 403
        } else {
            return HttpStatus.BAD_REQUEST; // 400
        }
    }
}