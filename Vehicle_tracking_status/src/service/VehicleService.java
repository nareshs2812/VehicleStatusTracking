package service;

import java.sql.SQLException;

public interface VehicleService {
    String getVehicleDetails(String vehicleNumber) throws SQLException;
    void deleteVehicleDetails(String vehicleNumber) throws SQLException;
    void createVehicleDetails(String driverName, int driverAge, String vehicleType,
            String vehicleNumber, String vehicleStatus) throws SQLException;
    String getVehicleStatus(String vehicleNumber) throws SQLException;
    void updateVehicleStatus(String vehicleNumber, String newStatus) throws SQLException;
}
