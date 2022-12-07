package com.example.oryeqrzh.repository;

import com.example.oryeqrzh.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "select * from cars where model = ?1",nativeQuery = true)
    Car findByModel(String model);
}
