package kz.edu.sdu.stand.impl;

import kz.edu.sdu.controller.model.CityResponseInfo;
import kz.edu.sdu.controller.register.CityRegister;
import kz.edu.sdu.stand.impl.db.Db;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daniyar on 10/02/18.
 * Marshmello & Anne-Marie - FRIENDS
 */
@Service
public class CityRegisterStandImpl implements CityRegister{
    private Db db;

    public CityRegisterStandImpl(Db db) {
        this.db = db;
    }

    @Override
    public List<CityResponseInfo> getAllCities() {
        return db.cities.values().stream().map(it -> {
           CityResponseInfo cityResponseInfo = new CityResponseInfo();
           cityResponseInfo.id = it.id;
           cityResponseInfo.name = it.name;

           return cityResponseInfo;
        }).collect(Collectors.toList());
    }
}
