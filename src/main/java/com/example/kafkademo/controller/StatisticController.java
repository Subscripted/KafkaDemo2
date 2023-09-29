package com.example.kafkademo.controller;

import com.example.kafkademo.repository.GlobalVehicleCounterRepository;
import com.example.kafkademo.repository.MeasurementRepository;
import com.example.kafkademo.repository.entity.GlobalVehicleCounter;
import com.example.kafkademo.repository.entity.Measurement;
import com.example.kafkademo.repository.entity.VehicleType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/statistic")
public class StatisticController {

    private final GlobalVehicleCounterRepository globalVehicleCounterRepository;
    private final MeasurementRepository measurementRepository;

    public StatisticController(GlobalVehicleCounterRepository globalVehicleCounterRepository, MeasurementRepository measurementRepository) {
        this.globalVehicleCounterRepository = globalVehicleCounterRepository;
        this.measurementRepository = measurementRepository;
    }

    @GetMapping("station/{stationId}")
    public Map<VehicleType, Long> getCountsByStationId(@PathVariable Long stationId) {
        System.out.println("stationId=" + stationId);
        var counter = new HashMap<VehicleType, Long>();

        // Hier die Map mit den richtigen Werten aus der Datenbank befüllen
        List<Measurement> measurements = measurementRepository.findByStationId(stationId);

        for (Measurement measurement : measurements) {
            VehicleType vehicleType = measurement.getVehicleType();
            counter.put(vehicleType, counter.getOrDefault(vehicleType, 0L) + 1);
        }

        return counter;
    }



    @GetMapping("global-counts")
    public List<GlobalVehicleCounter> getGlobalVehicleCounter() {
        // Hier alle globalen Zähler aus der Datenbank zurückgeben
        return globalVehicleCounterRepository.findAll();
    }
}
