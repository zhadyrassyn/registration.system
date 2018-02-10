package kz.edu.sdu.stand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by daniyar on 05/02/18.
 * Song G-Eazy & Halsey - Him & I
 */
@SpringBootApplication
@ComponentScan(basePackages = {"kz.edu.sdu"})
public class StandLauncher {
    public static void main(String[] args) {
        SpringApplication.run(StandLauncher.class, args);
    }
}
