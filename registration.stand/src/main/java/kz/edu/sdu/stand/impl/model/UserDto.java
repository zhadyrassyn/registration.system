package kz.edu.sdu.stand.impl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by daniyar on 06/02/18.
 */
public class UserDto {
    public Long id;
    public String email;
    public String password;
    public boolean enabled = false;

    public List<RoleDto> authorities = new ArrayList<>();
}
