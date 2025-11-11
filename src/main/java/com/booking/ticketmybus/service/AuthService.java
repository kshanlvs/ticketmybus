package com.booking.ticketmybus.repository.shared.interfaces;
import com.booking.ticketmybus.dto.shared.UserRegisterDTO;
import com.booking.ticketmybus.entity.User;

public interface AuthService {
   User register(UserRegisterDTO registerDTO);
}
