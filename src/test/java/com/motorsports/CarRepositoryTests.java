package com.motorsports;

import com.motorsports.car.Car;
import com.motorsports.car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CarRepositoryTests {
    @Autowired
    private CarRepository repo;
    @Test
    public void testAddNew() {
        Car car = new Car();
        car.setName("DFSDFSFSFD");
        car.setYear(Integer.valueOf("2567"));
        car.setCountry("USA");
        car.setDescription("desc");
        car.setPrice(new BigDecimal("350000.00"));

        Car savedCar = repo.save(car);

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getId()).isGreaterThan(0);
    }


}