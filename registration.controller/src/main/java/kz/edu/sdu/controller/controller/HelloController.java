package kz.edu.sdu.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by daniyar on 05/02/18.
 */
@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Hello, world!";
    }
}
