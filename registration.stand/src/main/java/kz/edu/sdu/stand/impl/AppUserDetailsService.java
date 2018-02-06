package kz.edu.sdu.stand.impl;

import kz.edu.sdu.stand.impl.model.UserDto;
import kz.edu.sdu.stand.repository.RoleRepository;
import kz.edu.sdu.stand.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniyar on 06/02/18.
 * Song Feder, Lyse - Goodbye
 */
@Component
public class AppUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AppUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userRepository.findByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException(String.format("The username with email %s" +
                    " doesn't exist", email));

        List<GrantedAuthority> authorities = new ArrayList<>();
        roleRepository.getRoleNames().forEach(roleName -> authorities.add(new SimpleGrantedAuthority(roleName)));

        UserDetails userDetails = new User(user.email, user.password, authorities);
        return userDetails;
    }
}
