package com.booking.ticketmybus.service;
import com.booking.ticketmybus.dto.shared.LoginRequestDTO;
import com.booking.ticketmybus.dto.shared.LoginResponseDTO;
import com.booking.ticketmybus.dto.shared.UserRegisterDTO;
import com.booking.ticketmybus.entity.User;

public interface AuthService {
   User register(UserRegisterDTO registerDTO);
   LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
