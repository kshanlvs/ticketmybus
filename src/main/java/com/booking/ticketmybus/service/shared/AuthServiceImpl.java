package com.booking.ticketmybus.service.shared;

import com.booking.ticketmybus.dto.shared.LoginRequestDTO;
import com.booking.ticketmybus.dto.shared.LoginResponseDTO;
import com.booking.ticketmybus.dto.shared.UserRegisterDTO;
import com.booking.ticketmybus.entity.User;
import com.booking.ticketmybus.entity.UserRole;
import com.booking.ticketmybus.repository.UserRepository;
import com.booking.ticketmybus.service.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

 // Add this dependency

    @Override
    public User register(UserRegisterDTO userRegisterDTO) {

        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();


        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword())); // Encode password!
        user.setMobileNumber(userRegisterDTO.getMobileNumber());
        user.setCompanyName(userRegisterDTO.getCompanyName());

        switch (userRegisterDTO.getUserRole().toLowerCase()) {
            case "admin":
                user.setRole(UserRole.ADMIN);
                break;
            case "user":
                user.setRole(UserRole.USER);
                break;
            case "vendor":
                user.setRole(UserRole.VENDOR);
                break; // Added missing break
            default:
                throw new RuntimeException("Invalid role: " + userRegisterDTO.getUserRole());
        }
        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not registered"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setName(user.getName());
        loginResponseDTO.setEmail(user.getEmail());
        loginResponseDTO.setAccess_token("lksjdlfkj");
        return loginResponseDTO;
    }


}