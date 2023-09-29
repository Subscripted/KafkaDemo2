package com.example.kafkademo.repository;

import com.example.kafkademo.repository.entity.GlobalVehicleCounter;
import com.example.kafkademo.repository.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalVehicleCounterRepository extends
        JpaRepository<GlobalVehicleCounter, VehicleType>,
        CrudRepository<GlobalVehicleCounter, VehicleType> {
}
