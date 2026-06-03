package Day2;

import java.util.*;

class Student {
    int id;
    String name;
    int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("ID: " + id +
                ", Name: " + name +
                ", Age: " + age);
    }
}

public class Crud {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    public static void create() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();

        students.add(new Student(id, name, age));

        System.out.println("Student Added Successfully!");
    }


    public static void read() {

        if (students.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }

        System.out.println("\nStudent Details:");

        for (Student s : students) {
            s.display();
        }
    }


    public static void update() {

        System.out.print("Enter Student ID to Update: ");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.id == id) {

                sc.nextLine();

                System.out.print("Enter New Name: ");
                s.name = sc.nextLine();

                System.out.print("Enter New Age: ");
                s.age = sc.nextInt();

                System.out.println("Student Updated Successfully!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }


    public static void delete() {

        System.out.print("Enter Student ID to Delete: ");
        int id = sc.nextInt();

        Iterator<Student> it = students.iterator();

        while (it.hasNext()) {

            Student s = it.next();

            if (s.id == id) {
                it.remove();
                System.out.println("Student Deleted Successfully!");
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Create Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    create();
                    break;

                case 2:
                    read();
                    break;

                case 3:
                    update();
                    break;

                case 4:
                    delete();
                    break;

                case 5:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}