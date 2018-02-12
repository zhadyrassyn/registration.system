package kz.edu.sdu.stand.utils;

import java.util.UUID;

/**
 * Created by daniyar on 12/02/18.
 */
public class Utils {
    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
