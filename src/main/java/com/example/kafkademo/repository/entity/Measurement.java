package com.example.kafkademo.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "station_id", nullable = false)
    private Long stationId;

    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING) // damit werden Enums in der DB nicht als Zahl sondern als String gespeichert
    private VehicleType vehicleType;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public Measurement(Long stationId, VehicleType vehicleType, LocalDateTime timestamp) {
        this.stationId = stationId;
        this.vehicleType = vehicleType;
        this.timestamp = timestamp;
    }

    public Measurement() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @PostPersist
    public void postPersist(){
        System.out.println("Entity with id = " + this.id + " stored.");
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", vehicleType=" + vehicleType +
                ", timestamp=" + timestamp +
                '}';
    }
}


