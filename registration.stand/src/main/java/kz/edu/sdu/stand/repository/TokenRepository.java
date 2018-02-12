package kz.edu.sdu.stand.repository;

import kz.edu.sdu.stand.impl.db.Db;
import org.springframework.stereotype.Repository;

/**
 * Created by daniyar on 12/02/18.
 */
@Repository
public class TokenRepository {
    private Db db;

    public TokenRepository(Db db) {
        this.db = db;
    }

    public Long getIdByToken(String token) {
        return db.mapUserIdToToken.entrySet().stream().filter(map -> token.equals(map.getValue()))
                .findFirst().get().getKey();
    }
}
