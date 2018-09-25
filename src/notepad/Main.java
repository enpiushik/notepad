package notepad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List <Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command:");
            String cmd = scanner.next();
            switch (cmd) {
                case "create":
                    create();
                    break;
                case "list":
                    printList();
                    break;
                case "delete":
                    delete();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It is not a command!");
            }
        }
    }

    private static void delete() {
        
    }

    private static void printList() {
        for (Person p : personList){
            System.out.println(p);
        }
    }

    private static void create() {
            System.out.println("Enter your name:");
            String name = scanner.next();

            System.out.println("Enter your surname:");
            String surname = scanner.next();

            System.out.println("Enter your phone:");
            String phone = scanner.next();

            Person p = new Person();
            p.setName(name);
            p.setSurname(surname);
            p.setPhone(phone);

            personList.add(p);

        System.out.println(p);
    }
}