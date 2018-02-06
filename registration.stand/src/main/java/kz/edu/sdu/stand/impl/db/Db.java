package kz.edu.sdu.stand.impl.db;

import kz.edu.sdu.stand.impl.model.RoleDto;
import kz.edu.sdu.stand.impl.model.UserDto;
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
    private AtomicLong counter = new AtomicLong();

    public Db() {
        init();
    }

    private void init() {
        RoleDto r1 = new RoleDto();
        r1.id = (int)counter.incrementAndGet();
        r1.roleName = "STANDARD_USER";
        r1.description = "Standard User - Has no admin rights";

        RoleDto r2 = new RoleDto();
        r2.id = (int)counter.incrementAndGet();
        r2.roleName = "ADMIN_USER";
        r2.description = "Admin user - Has permission to perform admin operations";

        roles.put(r1.id, r1);
        roles.put(r2.id, r2);

        for(int i = 1; i <= 5; i++) {
            UserDto u = new UserDto();
            u.id = counter.incrementAndGet();
            u.email = String.format("u%d@test.ru", i);
            u.password = String.format("u%dpassword", i);

            users.put(u.id, u);
            mapUserToRole.put(u.id, i == 0 ? r2.id : r1.id);
        }
    }
}
