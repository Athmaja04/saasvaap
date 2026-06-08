package Day3;
import java.util.*;

interface HospitalOperations {
    void addPatient();
    void viewPatients();
    void updatePatient();
    void deletePatient();
    void addDoctor();
    void viewDoctors();
    void updateDoctor();
    void deleteDoctor();
    void bookAppointment();
    void viewAppointment();
    void generateBill();
}

class Doctor{
    int doctorId;
    String drName;
    String specialisation;

    public String toString() {
        return ("Doctor ID: " + doctorId + ", Doctor Name: " + drName + ", Specialisation: " + specialisation);
    }
}
class Patient{
    int patientId;
    String ptName;
    int ptAge;
    String disease;

    public String toString() {
        return ("Patient ID: " + patientId + ", Patient Name: " + ptName + ", Patient Age: " + ptAge + ", Disease: "+ disease);
    }

}
class Appointment{
    int appointmentId;
    int doctorId;
    int patientId;
    String date;

    public String toString() {
        return ("Appointment ID: " + appointmentId + ", Doctor ID: " + doctorId + ", Patient ID: " + patientId + "Date: " + date);
    }

}

class HospitalManager implements HospitalOperations {
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public void addPatient() {
        Patient p = new Patient();
        System.out.print("Enter Patient ID: ");
        p.patientId = sc.nextInt();
        for (Patient i : patients) {
            if (i.patientId == p.patientId) {
                System.out.println("Patient ID already exists!!");
                return;
            }
        }
        sc.nextLine();

        System.out.print("Enter Patient Name: ");
        p.ptName = sc.nextLine();
        System.out.print("Enter Patient Age: ");
        p.ptAge = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Disease: ");
        p.disease = sc.nextLine();

        patients.add(p);
        System.out.println("Patient registered successfully");

    }

    public void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No Patients found!! ");
            return;
        }
        System.out.println("PATIENT DETAILS: ");
        for (Patient i : patients) {
            System.out.println(i);
        }
    }

    public void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        int uppid = sc.nextInt();
        sc.nextLine();
        for (Patient i : patients) {
            if (uppid == i.patientId) {
                System.out.print("Enter New Patient Name: ");
                i.ptName = sc.nextLine();
                System.out.print("Enter New Patient Age: ");
                i.ptAge = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter New Disease: ");
                i.disease = sc.nextLine();
                System.out.println("Patient Updated Successfully!");
                return;
            }
        }

        System.out.println("Patient Not Found!");
    }

    public void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int dlpid = sc.nextInt();
        for(Patient i : patients){
            if(dlpid==i.patientId){
                patients.remove(i);

                System.out.println("Patient Deleted Successfully!");
                return;
            }
        }
        System.out.println("Patient do not exists!!");
    }

    public void addDoctor(){
        Doctor d = new Doctor();
        System.out.print("Enter Doctor ID: ");
        d.doctorId = sc.nextInt();
        for (Doctor i : doctors) {
            if (i.doctorId == d.doctorId) {
                System.out.println("Doctor ID already exists!!");
                return;
            }
        }
        sc.nextLine();

        System.out.print("Enter Doctor Name: ");
        d.drName = sc.nextLine();
        System.out.print("Enter Specialisation: ");
        d.specialisation = sc.nextLine();

        doctors.add(d);
        System.out.println("Doctor added successfully");
    }

    public void viewDoctors(){
        if (doctors.isEmpty()) {
            System.out.println("No Doctors found!! ");
            return;
        }
        System.out.println("DOCTORS LIST: ");
        for (Doctor i : doctors) {
            System.out.println(i);
        }
    }

    public void updateDoctor(){
        System.out.print("Enter Doctor ID to update: ");
        int updid = sc.nextInt();
        sc.nextLine();
        for (Doctor i : doctors) {
            if (updid == i.doctorId) {
                System.out.print("Enter New Doctor Name: ");
                i.drName = sc.nextLine();
                System.out.print("Enter New Specialisation: ");
                i.specialisation = sc.nextLine();
                System.out.println("Doctor Updated Successfully!");
                return;
            }
        }

        System.out.println("Doctor Not Found!");
    }

    public void deleteDoctor() {
        System.out.print("Enter Doctor ID to delete: ");
        int dldid = sc.nextInt();
        for(Doctor i : doctors){
            if(dldid==i.doctorId){
                doctors.remove(i);

                System.out.println("Doctor Deleted Successfully!");
                return;
            }
        }
        System.out.println("Doctor do not exists!!");
    }

    public void bookAppointment() {
        Appointment a = new Appointment();
        System.out.print("Enter Patient ID: ");
        a.patientId = sc.nextInt();

        System.out.print("Enter Doctor ID: ");
        a.doctorId = sc.nextInt();

        System.out.print("Enter Appointment ID: ");
        a.appointmentId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Appointment Date: ");
        a.date = sc.nextLine();

        appointments.add(a);

        System.out.println("Appointment Booked Successfully!");
    }

    public void viewAppointment() {

        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();

        boolean found = false;

        System.out.println("\nAppointments:");

        for (Appointment a : appointments) {

            if (a.doctorId == did) {

                String patientName = "";
                String disease = "";

                for (Patient p : patients) {
                    if (p.patientId == a.patientId) {
                        patientName = p.ptName;
                        disease = p.disease;
                        break;
                    }
                }

                System.out.println(
                        "Appointment ID: " + a.appointmentId + ", Patient ID: " + a.patientId + ", Patient Name: " + patientName + ", Disease: " + disease + ", Date: " + a.date);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Appointments Found!");
        }
    }

    public void generateBill() {

        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Medicine Charges: ");
        double medicine = sc.nextDouble();

        double consultationFee = 500;

        double total = consultationFee + medicine;

        System.out.println("\n===== BILL =====");
        System.out.println("Patient ID: " + id + "Consultation Fee: " + consultationFee + "Medicine Charges: " + medicine + "Total Amount: " + total);
    }
}
public class Hospital {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HospitalManager h = new HospitalManager();

        int choice;

        do {

            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.print("1. Add Patient\n2. View Patients\n3. Update Patient\n4. Delete Patient\n5. Add Doctor\n6. View Doctors\n7. Update Doctor\n8. Delete Doctor\n9. Book Appointment\n10. View Appointments\n11. Generate Bill\n12. Exit\nEnter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    h.addPatient();
                    break;

                case 2:
                    h.viewPatients();
                    break;

                case 3:
                    h.updatePatient();
                    break;

                case 4:
                    h.deletePatient();
                    break;

                case 5:
                    h.addDoctor();
                    break;

                case 6:
                    h.viewDoctors();
                    break;

                case 7:
                    h.updateDoctor();
                    break;

                case 8:
                    h.deleteDoctor();
                    break;

                case 9:
                    h.bookAppointment();
                    break;

                case 10:
                    h.viewAppointment();
                    break;

                case 11:
                    h.generateBill();
                    break;

                case 12:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 12);

        sc.close();
    }
}
