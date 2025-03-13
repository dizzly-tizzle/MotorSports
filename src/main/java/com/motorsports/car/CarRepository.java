package com.motorsports.car;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
    public Long countById(Integer id);
}
