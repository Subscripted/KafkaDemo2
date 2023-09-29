package com.example.kafkademo.repository;

import com.example.kafkademo.repository.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long>, CrudRepository<Measurement, Long> {
    List<Measurement> findByStationId(Long stationId);
}
