package Day2;

import java.util.*;

interface StudentCRUD {
    void create();
    void read();
    void update();
    void delete();
}

class Student {
    int id;
    String name;
    int age;

    public String toString()
    {
        return ("ID: " + id + ", Name: " + name + ", Age: " + age);
    }

    public boolean equals(Student s1)
    {
        return this.id==s1.id;
    }
}

class Manage implements StudentCRUD{
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    public void create() {
        Student s = new Student();
        System.out.print("Enter Student ID: ");
        s.id = sc.nextInt();
        for(Student st : students)
        {
            if(st.id == s.id)
            {
                System.out.println("Student ID already exists");
                return;
            }
        }
        sc.nextLine();


        System.out.print("Enter Student Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        s.age = sc.nextInt();

        students.add(s);

        System.out.println("Student Added Successfully!");
    }


    public void read() {

        if (students.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }

        System.out.println("\nStudent Details:");

        for (Student i : students) {//for(int i=0;i<students.size();i++)
            System.out.println(i);
        }
    }


    public void update() {

        System.out.print("Enter Student ID to Update: ");
        int upid = sc.nextInt();

        for (Student s : students) {

            if (s.id == upid) {

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


    public void delete() {

        System.out.print("Enter Student ID to Delete: ");
        int dlid = sc.nextInt();

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).id == dlid) {

                students.remove(i);

                System.out.println("Student Deleted Successfully!");
                return;
            }
        }


        System.out.println("Student Not Found!");
    }
}
public class Crud {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        StudentCRUD m = new Manage();
        int choice;

        do {

            System.out.println("\nSTUDENT CRUD MENU\n1. Create Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Exit\nEnter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    m.create();
                    break;

                case 2:
                    m.read();
                    break;

                case 3:
                    m.update();
                    break;

                case 4:
                    m.delete();
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