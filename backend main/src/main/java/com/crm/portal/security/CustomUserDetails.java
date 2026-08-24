package com.crm.portal.security;

import com.crm.portal.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Spring Security principal wrapping our User entity.
 * Authorities = ROLE_<role> plus each fine-grained permission string from
 * user_permissions, so both hasRole(...) and hasAuthority(...) work in
 * @PreAuthorize expressions.
 */
@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String email;
    private final String passwordHash;
    private final String role;
    private final boolean active;
    private final List<String> permissions;

    public CustomUserDetails(User user, List<String> permissions) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
        this.role = user.getRole().name();
        this.active = Boolean.TRUE.equals(user.getIsActive());
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<String> roleAuthority = Stream.of("ROLE_" + role);
        Stream<String> permissionAuthorities = permissions == null ? Stream.empty() : permissions.stream();
        return Stream.concat(roleAuthority, permissionAuthorities)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
