package kz.edu.sdu.stand.impl.db;

import kz.edu.sdu.stand.impl.model.CityDto;
import kz.edu.sdu.stand.impl.model.RoleDto;
import kz.edu.sdu.stand.impl.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by daniyar on 06/02/18.
 * Song Adam Lambert - Ghost Town
 */
@Component
public class Db {
    public Map<Long, UserDto> users = new HashMap<>();
    public Map<Integer, RoleDto> roles = new HashMap<>();
    public Map<Long, Integer> mapUserToRole = new HashMap<>();
    public Map<Integer, CityDto> cities = new HashMap<>();
    private AtomicLong counter = new AtomicLong();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public Db() {
        init();
    }

    private void init() {
        RoleDto r1 = new RoleDto();
        r1.id = (int)counter.incrementAndGet();
        r1.roleName = "ROLE_USER";
        r1.description = "Standard User - Has no admin rights";

        RoleDto r2 = new RoleDto();
        r2.id = (int)counter.incrementAndGet();
        r2.roleName = "ROLE_ADMIN";
        r2.description = "Admin user - Has permission to perform admin operations";

        roles.put(r1.id, r1);
        roles.put(r2.id, r2);



        for(int i = 1; i <= 5; i++) {
            UserDto u = new UserDto();
            u.id = counter.incrementAndGet();
            u.email = String.format("u%d@test.ru", i);
            u.password = passwordEncoder().encode(String.format("u%dpassword", i));
            users.put(u.id, u);
            if(i == 1) {
                u.authorities.add(r2);
            } else {
                u.authorities.add(r1);
            }
        }

        CityDto c1 = new CityDto();
        c1.id = (int)counter.incrementAndGet();
        c1.name = "Almaty";

        CityDto c2 = new CityDto();
        c2.id = (int)counter.incrementAndGet();
        c2.name = "Astana";

        cities.put(c1.id, c1);
        cities.put(c2.id, c2);
    }
}
