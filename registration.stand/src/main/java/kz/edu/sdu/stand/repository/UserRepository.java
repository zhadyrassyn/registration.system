package kz.edu.sdu.stand.repository;

import kz.edu.sdu.stand.impl.db.Db;
import kz.edu.sdu.stand.impl.model.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by daniyar on 06/02/18.
 * Kygo feat. Parson James - Stole the Show
 */
@Service
public class UserRepository {
    private Db db;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserRepository(Db db) {
        this.db = db;
    }

    public UserDto findByEmail(String email) {
        for(long key: db.users.keySet()) {
            if(db.users.get(key).email.equals(email)) {
                return db.users.get(key);
            }
        }

        return null;
    }

    public void save(String email, String password) {
        UserDto newUser = new UserDto();
        newUser.id = db.counter.incrementAndGet();
        newUser.password = password;
        newUser.email = email;
        newUser.password = passwordEncoder().encode(newUser.password);
        db.users.put(newUser.id, newUser);

    }

    public void mapUserToToken(String email, String token) {
        UserDto userDto = findByEmail(email);
        if(userDto != null) {
            userDto.enabled = true;
            db.mapUserIdToToken.put(userDto.id, token);
        }
    }

    public void setStatus(Long id, String status) {
        if(status.equals("ACTIVATED")) {
            db.users.get(id).enabled = true;
        }

    }
}
