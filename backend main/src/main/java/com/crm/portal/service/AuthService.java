package com.crm.portal.service;

import com.crm.portal.dto.AuthResponse;
import com.crm.portal.dto.LoginRequest;
import com.crm.portal.dto.RegisterRequest;
import com.crm.portal.dto.UserDto;
import com.crm.portal.entity.User;
import com.crm.portal.enums.UserRole;
import com.crm.portal.exception.DuplicateResourceException;
import com.crm.portal.exception.UnauthorizedException;
import com.crm.portal.mapper.UserMapper;
import com.crm.portal.repository.UserPermissionRepository;
import com.crm.portal.repository.UserRepository;
import com.crm.portal.security.CustomUserDetails;
import com.crm.portal.security.CustomUserDetailsService;
import com.crm.portal.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final AuditLogService auditLogService;

    @Transactional
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid email or password");
        }

        User user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new UnauthorizedException("This account has been disabled. Contact an administrator.");
        }

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        CustomUserDetails principal = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(principal);

        auditLogService.log(user, "LOGIN", "User", user.getId());

        return new AuthResponse(token, "Bearer", toDto(user));
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateResourceException("An account with this email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(UserRole.USER)
                .isActive(true)
                .build();

        user = userRepository.save(user);

        auditLogService.log(user, "REGISTER", "User", user.getId());

        CustomUserDetails principal = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(principal);

        return new AuthResponse(token, "Bearer", toDto(user));
    }

    @Transactional(readOnly = true)
    public UserDto me(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException("Authenticated user no longer exists"));

        return toDto(user);
    }

    private UserDto toDto(User user) {
        List<String> permissions = userPermissionRepository.findByUserId(user.getId())
                .stream()
                .map(p -> p.getPermission())
                .collect(Collectors.toList());

        return UserMapper.toDto(user, permissions);
    }
}
