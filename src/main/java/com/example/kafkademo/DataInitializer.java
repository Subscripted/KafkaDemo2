package com.example.kafkademo;

import com.example.kafkademo.messaging.MeasurementProducer;
import com.example.kafkademo.repository.*;
import com.example.kafkademo.repository.entity.GlobalVehicleCounter;
import com.example.kafkademo.repository.entity.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class DataInitializer {

    private final Logger logger = Logger.getLogger(String.valueOf(DataInitializer.class));

    private static final Random RANDOM = new Random();

    private static final List<VehicleType> VALUES = List.of(VehicleType.values());

    private static final int SIZE = VALUES.size();

    private final MeasurementRepository measurementRepository;
    private final GlobalVehicleCounterRepository globalVehicleCounterRepository;
    private final MeasurementProducer measurementProducer;

    DataInitializer(
            MeasurementRepository measurementRepository,
            GlobalVehicleCounterRepository globalVehicleCounterRepository,
            MeasurementProducer measurementProducer
    ) {
        this.measurementRepository = measurementRepository;
        this.globalVehicleCounterRepository = globalVehicleCounterRepository;
        this.measurementProducer = measurementProducer;
    }

    public void init() {
        // den auskommentierten Code habe ich nur mal zum Testen der H2 und von JPA geschrieben
//        var now = LocalDateTime.now();
//
//        List<Measurement> measurements = LongStream.range(0, 100)
//                .mapToObj(i -> new Measurement(1L, VALUES.get(RANDOM.nextInt(SIZE)), now.plusHours(i)))
//                .collect(Collectors.toList());
//
//        measurementRepository.saveAllAndFlush(measurements);
//
//        measurementRepository.findAll().forEach(System.out::println);


        // hier legen wir schon einmal die Counter mit Wert 0 an, dann müssen wir sie
        // im Service nicht erst erzeugen
        var globalVehicleCounter = List.of(
                new GlobalVehicleCounter(VehicleType.PKW, 0L),
                new GlobalVehicleCounter(VehicleType.LKW_ZWEIACHSER, 0L),
                new GlobalVehicleCounter(VehicleType.LKW_DREIACHSER, 0L)
        );

        globalVehicleCounterRepository.saveAllAndFlush(globalVehicleCounter);

        logger.info("calling producer");
        measurementProducer.produce();
        logger.info("producer called");

    }
}
