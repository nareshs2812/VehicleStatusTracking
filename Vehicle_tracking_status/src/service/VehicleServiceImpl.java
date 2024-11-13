package service;

import model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleServiceImpl implements VehicleService {

    @Override
    public String getVehicleStatus(String vehicleNumber) throws SQLException {
        Connection conn = Database.db_connect();
        try {
            String statusQuery = "SELECT vehicle_status FROM vehicle_details WHERE vehicle_number = ?";
            PreparedStatement statusStmt = conn.prepareStatement(statusQuery);
            statusStmt.setString(1, vehicleNumber);
            ResultSet rs = statusStmt.executeQuery();
            if (rs.next()) {
                return rs.getString("vehicle_status");
            } else {
                return "Vehicle number does not exist.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching vehicle status: " + e.getMessage());
        } finally {
            conn.close();
        }
    }
    @Override
    public void updateVehicleStatus(String vehicleNumber, String newStatus) throws SQLException {
        Connection conn = Database.db_connect();

        try {
            String checkQuery = "SELECT COUNT(*) FROM vehicle_details WHERE vehicle_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, vehicleNumber);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Vehicle number does not exist.");
                return; // No need to proceed if the vehicle doesn't exist
            }

            // Update vehicle status
            String updateQuery = "UPDATE vehicle_details SET vehicle_status = ? WHERE vehicle_number = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, newStatus.toUpperCase());  // Ensure the new status is uppercase
            updateStmt.setString(2, vehicleNumber);
            updateStmt.executeUpdate();

            System.out.println("Vehicle status updated.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating vehicle status: " + e.getMessage());
        } finally {
            conn.close();
        }
    }
    

    @Override
    public String getVehicleDetails(String vehicleNumber) throws SQLException {
        Connection conn = Database.db_connect();
        try {
            String query = "SELECT * FROM vehicle_details WHERE vehicle_number = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, vehicleNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Vehicle Number: " + rs.getString("vehicle_number") + "\n" +
                        "Driver Name: " + rs.getString("driver_name") + "\n" +
                        "Driver Age: " + rs.getInt("driver_age") + "\n" +
                        "Vehicle Type: " + rs.getString("vehicle_type") + "\n" +
                        "Vehicle Status: " + rs.getString("vehicle_status");
            } else {
                return "Vehicle number does not exist.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching vehicle details: " + e.getMessage());
        } finally {
            conn.close();
        }
    }
    
    
    @Override
    public void createVehicleDetails(String driverName, int driverAge, String vehicleType,
                                     String vehicleNumber, String vehicleStatus) throws SQLException {
        Connection conn = Database.db_connect();

        try {
            String insertQuery = "INSERT INTO vehicle_details (driver_name, driver_age, vehicle_type, vehicle_number, vehicle_status) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, driverName);
            stmt.setInt(2, driverAge);
            stmt.setString(3, vehicleType);
            stmt.setString(4, vehicleNumber);
            stmt.setString(5, vehicleStatus.toUpperCase()); // Ensure status is uppercase
            stmt.executeUpdate();

            System.out.println("Vehicle details created.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error creating vehicle details: " + e.getMessage());
        } finally {
            conn.close();
        }
    }

    @Override
    public void deleteVehicleDetails(String vehicleNumber) throws SQLException {
        Connection conn = Database.db_connect();

        try {
            String checkQuery = "SELECT COUNT(*) FROM vehicle_details WHERE vehicle_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, vehicleNumber);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                System.out.println("Vehicle with number " + vehicleNumber + " does not exist.");
                return;
            }

            String deleteLogQuery = "DELETE FROM status_change_log WHERE vehicle_id IN (SELECT vehicle_id FROM vehicle_details WHERE vehicle_number = ?)";
            PreparedStatement deleteLogStmt = conn.prepareStatement(deleteLogQuery);
            deleteLogStmt.setString(1, vehicleNumber);
            deleteLogStmt.executeUpdate();

            
            String deleteQuery = "DELETE FROM vehicle_details WHERE vehicle_number = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setString(1, vehicleNumber);
            deleteStmt.executeUpdate();

            System.out.println("Vehicle details deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting vehicle details: " + e.getMessage());
        } finally {
            conn.close();
        }
    }

}
