package kz.edu.sdu.stand.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by daniyar on 10/02/18.
 */
@Component
public class TimeProvider {

    public Date now() {
        return new Date();
    }
}
