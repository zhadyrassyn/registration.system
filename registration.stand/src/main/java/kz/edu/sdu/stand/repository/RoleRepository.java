package kz.edu.sdu.stand.repository;

import kz.edu.sdu.stand.impl.db.Db;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daniyar on 06/02/18.
 * Song Lost Frequencies feat. Janieck Devy - Reality
 */
@Service
public class RoleRepository {
    private Db db;

    public RoleRepository(Db db) {
        this.db = db;
    }

    public List<String> getRoleNames() {
        return db.roles.values().stream().map(it -> it.roleName).collect(Collectors.toList());
    }
}
