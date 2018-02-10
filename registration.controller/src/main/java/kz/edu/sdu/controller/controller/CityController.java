package kz.edu.sdu.controller.controller;

import kz.edu.sdu.controller.model.CityResponseInfo;
import kz.edu.sdu.controller.register.CityRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by daniyar on 10/02/18.
 * Song Imagine Dragons - Whatever It Takes
 */
@RestController
public class CityController {

    private CityRegister cityRegister;

    public CityController(CityRegister cityRegister) {
        this.cityRegister = cityRegister;
    }

    @RequestMapping(value = "/cities")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<CityResponseInfo> getCities() {
        return cityRegister.getAllCities();
    }
}

