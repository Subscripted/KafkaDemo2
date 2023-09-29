package com.example.kafkademo.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class GlobalVehicleCounter {

    @Id
    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING) // damit werden Enums in der DB nicht als Zahl sondern als String gespeichert
    private VehicleType vehicleType;

    @Column(name = "counter", nullable = false)
    private Long counter;

    public GlobalVehicleCounter(VehicleType vehicleType, Long counter) {
        this.vehicleType = vehicleType;
        this.counter = counter;
    }

    public GlobalVehicleCounter() {

    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "GlobalVehicleCounter{" +
                "vehicleType=" + vehicleType +
                ", counter=" + counter +
                '}';
    }
}


