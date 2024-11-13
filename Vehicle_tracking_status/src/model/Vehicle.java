package model;

public class Vehicle {
    private int vehicleId;
    private String status;

    public Vehicle(int vehicleId, String status) {
        this.vehicleId = vehicleId;
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

