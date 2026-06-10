package Day4;

import java.util.*;
import java.sql.*;

class HospitalManager {

    // Centralized database credentials
    private static final String url = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String username = "root";
    private static final String password = "Athmaja@230560";

    static Scanner sc = new Scanner(System.in);

    // HELPER METHOD: Reusable database connection source
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Connection connection = getConnection();

            // Check if patient exists
            String checkQuery = "select * from patient where patient_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                System.out.println("Patient ID already exists!!");
                connection.close();
                return;
            }

            System.out.print("Enter Patient Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Patient Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Disease: ");
            String disease = sc.nextLine();

            // Insert new patient
            String insertQuery = "insert into patient(patient_id, pt_name, pt_age, disease) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, disease);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient registered successfully");
            } else {
                System.out.println("Patient not registered!");
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewPatients() {
        try {
            Connection connection = getConnection();
            String query = "select * from patient";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("PATIENT DETAILS: ");
            boolean hasData = false;
            while (resultSet.next()) {
                hasData = true;
                int id = resultSet.getInt("patient_id");
                String name = resultSet.getString("pt_name");
                int age = resultSet.getInt("pt_age");
                String disease = resultSet.getString("disease");
                System.out.println("Patient ID: " + id + ", Patient Name: " + name + ", Patient Age: " + age + ", Disease: " + disease);
            }
            if (!hasData) {
                System.out.println("No Patients found!! ");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Connection connection = getConnection();
            System.out.print("Enter New Patient Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Patient Age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Disease: ");
            String disease = sc.nextLine();

            String query = "update patient set pt_name = ?, pt_age = ?, disease = ? where patient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, disease);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient Updated Successfully!");
            } else {
                System.out.println("Patient Not Found!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int id = sc.nextInt();

        try {
            Connection connection = getConnection();
            String query = "delete from patient where patient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient Deleted Successfully!");
            } else {
                System.out.println("Patient do not exists!!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Connection connection = getConnection();

            // Check if doctor exists
            String checkQuery = "select * from doctor where doctor_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, id);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                System.out.println("Doctor ID already exists!!");
                connection.close();
                return;
            }

            System.out.print("Enter Doctor Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Specialisation: ");
            String spec = sc.nextLine();

            String insertQuery = "insert into doctor(doctor_id, dr_name, specialisation) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, spec);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor added successfully");
            } else {
                System.out.println("Doctor not added!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewDoctors() {
        try {
            Connection connection = getConnection();
            String query = "select * from doctor";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("DOCTORS LIST: ");
            boolean hasData = false;
            while (resultSet.next()) {
                hasData = true;
                int id = resultSet.getInt("doctor_id");
                String name = resultSet.getString("dr_name");
                String spec = resultSet.getString("specialisation");
                System.out.println("Doctor ID: " + id + ", Doctor Name: " + name + ", Specialisation: " + spec);
            }
            if (!hasData) {
                System.out.println("No Doctors found!! ");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDoctor() {
        System.out.print("Enter Doctor ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Connection connection = getConnection();
            System.out.print("Enter New Doctor Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Specialisation: ");
            String spec = sc.nextLine();

            String query = "update doctor set dr_name = ?, specialisation = ? where doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, spec);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor Updated Successfully!");
            } else {
                System.out.println("Doctor Not Found!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDoctor() {
        System.out.print("Enter Doctor ID to delete: ");
        int id = sc.nextInt();

        try {
            Connection connection = getConnection();
            String query = "delete from doctor where doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor Deleted Successfully!");
            } else {
                System.out.println("Doctor do not exists!!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void bookAppointment() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();
        System.out.print("Enter Appointment ID: ");
        int aid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Appointment Date: ");
        String date = sc.nextLine();

        try {
            Connection connection = getConnection();
            String query = "insert into appointment(appointment_id, doctor_id, patient_id, app_date) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, aid);
            preparedStatement.setInt(2, did);
            preparedStatement.setInt(3, pid);
            preparedStatement.setString(4, date);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment Booked Successfully!");
            } else {
                System.out.println("Appointment not booked!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error (Check if Doctor/Patient ID exists): " + e.getMessage());
        }
    }

    public void viewAppointment() {
        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();

        try {
            Connection connection = getConnection();

            String query = "select * from appointment where doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, did);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\nAppointments:");
            boolean found = false;

            while (resultSet.next()) {
                int aid = resultSet.getInt("appointment_id");
                int pid = resultSet.getInt("patient_id");
                String date = resultSet.getString("app_date");

                String patientQuery = "select pt_name, disease from patient where patient_id = ?";
                PreparedStatement patientStmt = connection.prepareStatement(patientQuery);
                patientStmt.setInt(1, pid);
                ResultSet patientResult = patientStmt.executeQuery();

                String patientName = "";
                String disease = "";
                if (patientResult.next()) {
                    patientName = patientResult.getString("pt_name");
                    disease = patientResult.getString("disease");
                }

                System.out.println("Appointment ID: " + aid + ", Patient ID: " + pid + ", Patient Name: " + patientName + ", Disease: " + disease + ", Date: " + date);
                found = true;
            }

            if (!found) {
                System.out.println("No Appointments Found!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateBill() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();

        try {
            Connection connection = getConnection();
            String query = "select pt_name from patient where patient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("pt_name");
                System.out.print("Enter Medicine Charges: ");
                double medicine = sc.nextDouble();
                double consultationFee = 500;
                double total = consultationFee + medicine;

                System.out.println("\n===== BILL =====");
                System.out.println("Patient ID: " + id + " | Name: " + name);
                System.out.println("Consultation Fee: " + consultationFee + " Medicine Charges: " + medicine + " Total Amount: " + total);
            } else {
                System.out.println("Patient Not Found!");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class HospitalDb {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        HospitalManager h = new HospitalManager();
        int choice;

        do {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.print("1. Add Patient\n2. View Patients\n3. Update Patient\n4. Delete Patient\n5. Add Doctor\n6. View Doctors\n7. Update Doctor\n8. Delete Doctor\n9. Book Appointment\n10. View Appointments\n11. Generate Bill\n12. Exit\nEnter Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: h.addPatient(); break;
                case 2: h.viewPatients(); break;
                case 3: h.updatePatient(); break;
                case 4: h.deletePatient(); break;
                case 5: h.addDoctor(); break;
                case 6: h.viewDoctors(); break;
                case 7: h.updateDoctor(); break;
                case 8: h.deleteDoctor(); break;
                case 9: h.bookAppointment(); break;
                case 10: h.viewAppointment(); break;
                case 11: h.generateBill(); break;
                case 12: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid Choice!");
            }
        } while (choice != 12);

        sc.close();
    }
}