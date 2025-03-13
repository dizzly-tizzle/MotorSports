package com.motorsports.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired private CarRepository repo;

    public List<Car> listALL() {
        return (List<Car>) repo.findAll();
    }

    public void save(Car car) {
        repo.save(car);
    }

    public Car get(Integer id) throws CarFoundErrorException {
        Optional<Car> result =  repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw  new CarFoundErrorException("С таким ID авто не найдено" + id);
    }

    public void delete(Integer id) throws CarFoundErrorException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw  new CarFoundErrorException("С таким ID авто не найдено" + id);
        }
        repo.deleteById(id);
    }

}
