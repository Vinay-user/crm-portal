package com.crm.portal.service;

import com.crm.portal.entity.User;
import com.crm.portal.exception.UnauthorizedException;
import com.crm.portal.repository.UserRepository;
import com.crm.portal.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Resolves the currently authenticated User entity from the Spring Security
 * context. Several other services use this to default an "owner" field or
 * to record who performed an action.
 */
@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails principal)) {
            throw new UnauthorizedException("No authenticated user in the current request");
        }

        return userRepository.findById(principal.getId())
                .orElseThrow(() -> new UnauthorizedException("Authenticated user no longer exists"));
    }

    public Long getCurrentUserIdOrNull() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails principal) {
            return principal.getId();
        }

        return null;
    }
}
