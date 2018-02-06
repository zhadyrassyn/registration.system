package kz.edu.sdu.stand.repository;

import kz.edu.sdu.stand.impl.db.Db;
import kz.edu.sdu.stand.impl.model.UserDto;
import org.springframework.stereotype.Service;

/**
 * Created by daniyar on 06/02/18.
 * Kygo feat. Parson James - Stole the Show
 */
@Service
public class UserRepository {
    private Db db;

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
}
