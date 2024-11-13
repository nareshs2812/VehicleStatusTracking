package controller;

import service.VehicleService;
import service.VehicleServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class VehicleController {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        VehicleService vehicleService = new VehicleServiceImpl();
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("-------------vehicle status tracking----------------");
            System.out.println("1. Create Vehicle Details");
            System.out.println("2. Update Vehicle Status");
            System.out.println("3. Get Vehicle Details");
            System.out.println("4. Delete Vehicle Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    createVehicleDetails(vehicleService);
                    break;

                case 2:
                    updateVehicleStatus(vehicleService);
                    break;

                case 3:
                    getVehicleDetails(vehicleService);
                    break;

                case 4:
                	deleteVehicleDetails(vehicleService);
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createVehicleDetails(VehicleService vehicleService) throws SQLException {
        System.out.print("Enter Driver Name: ");
        String driverName = sc.next();
        System.out.print("Enter Driver Age: ");
        int driverAge = sc.nextInt();
        System.out.print("Enter Vehicle Type: ");
        String vehicleType = sc.next();
        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.next();
        System.out.print("Enter Vehicle Status (ACTIVE, INACTIVE, IN_REPAIR, IN_TRANSIT): ");
        String vehicleStatus = sc.next();

        vehicleService.createVehicleDetails(driverName, driverAge, vehicleType, vehicleNumber, vehicleStatus);
        System.out.println("Vehicle details created successfully.");
    }

    private static void updateVehicleStatus(VehicleService vehicleService) throws SQLException {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.next();
        System.out.print("Enter New Status (ACTIVE, INACTIVE, IN_REPAIR, IN_TRANSIT): ");
        String newStatus = sc.next();
        vehicleService.updateVehicleStatus(vehicleNumber, newStatus);
        System.out.println("Vehicle status updated.");
    }

    private static void getVehicleDetails(VehicleService vehicleService) throws SQLException {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.next();
        String vehicleDetails = vehicleService.getVehicleDetails(vehicleNumber);
        System.out.println("Vehicle Details: \n" + vehicleDetails);
    }

    private static void deleteVehicleDetails(VehicleService vehicleService) throws SQLException {
        System.out.print("Enter Vehicle Number to delete: ");
        String vehicleNumber = sc.next();
        vehicleService.deleteVehicleDetails(vehicleNumber);
    }
}
