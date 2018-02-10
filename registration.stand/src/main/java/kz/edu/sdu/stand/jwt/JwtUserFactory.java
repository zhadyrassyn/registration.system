package kz.edu.sdu.stand.jwt;

import kz.edu.sdu.stand.impl.model.RoleDto;
import kz.edu.sdu.stand.impl.model.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daniyar on 11/02/18.
 *
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(UserDto user) {
        return new JwtUser(
                user.id,
                user.email,
                user.password,
                mapToGrantedAuthorities(user.authorities),
                user.enabled
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleDto> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.roleName))
                .collect(Collectors.toList());
    }
}
