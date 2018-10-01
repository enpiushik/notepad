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
                case "help":
                    help();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It is not a command!");
            }
        }
    }

    private static void help() {
        System.out.println("create - create new person");
        System.out.println("list - show all persons");
        System.out.println("delete - delete person");
        System.out.println("exit - close program");
    }

    private static void delete() {
        System.out.println("Enter person ID:");
        int ID = scanner.nextInt();
        int list_index = 0;

        for (Person p : personList) {
           if (p.getId() == ID) {
               list_index = personList.indexOf(p);
               break;
           }
        }
        if (list_index == 0)
            System.out.println("ID not found!");
        else
            personList.remove(list_index);
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
            String phone = "";
            while (phone.length() == 0) {
                phone = scanner.next();
                if (phone.matches("[0-9]+") == false || phone.length() < 5) {
                    System.out.println("Wrong phone number!");
                    phone = "";
                }
            }

            System.out.println("Enter your e-mail:");
            String email = scanner.next();

            Person p = new Person();
            p.setName(name);
            p.setSurname(surname);
            p.setPhone(phone);
            p.setEmail(email);

            personList.add(p);

        System.out.println(p);
    }
}