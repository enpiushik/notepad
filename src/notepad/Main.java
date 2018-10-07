package notepad;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List <Record> recordlist = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command:");
            String cmd = scanner.next();
            switch (cmd) {
                case "createPerson":
                case "cp":
                    createPerson();
                    break;
                case "createNote":
                case "cn":
                    createNote();
                    break;
                case "list":
                    printList();
                    break;
                case "delete":
                    removeByID();
                    break;
                case "find":
                    find();
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

    private static void find() {
        System.out.println("Find what?");
        String str = askString();
        for (Record r: recordlist) {
            if (r.hasSubstring(str)) {
                System.out.println(r);
            }
        }
    }

    private static void createNote() {
        System.out.println("Enter note:");
        String txt = askString();
        Note note = new Note();
        note.setText(txt);
        recordlist.add(note);
        System.out.println(note);
    }

    private static void help() {
        System.out.println("createPerson; cp - create new person");
        System.out.println("createNote; cn - create new a note");
        System.out.println("list - show all persons");
        System.out.println("delete - delete person by ID");
        System.out.println("find - person or note by ID");
        System.out.println("exit - close program");
    }

    private static void removeByID() {
        System.out.println("Enter person ID to remove:");
        int id = askInt();
        for (int i = 0; i < recordlist.size(); i++) {
            Record p = recordlist.get(i);
            if (id == p.getId()) {
                recordlist.remove(i);
                break;
            }
        }
    }

//    private static void delete() {
//        System.out.println("Enter person ID to remove:");
//        int ID = scanner.nextInt();
//        int list_index = 0;
//
//        for (Record p : recordlist) {
//           if (p.getId() == ID) {
//               list_index = recordlist.indexOf(p);
//               break;
//           }
//        }
//        if (list_index == 0)
//            System.out.println("ID not found!");
//        else
//            recordlist.remove(list_index);
//    }

    private static int askInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next(); // skip wrong input
                System.out.println("It isn't a number");
            }
        }
    }

    private static void printList() {
        for (Record p : recordlist){
            System.out.println(p);
        }
    }

    private static void createPerson() {
            System.out.println("Enter your name:");
            String name = askString();

            System.out.println("Enter your surname:");
            String surname = askString();

            System.out.println("Enter your phone:");
            String phone = askPhone();
//            String phone = "";
//            while (phone.length() == 0) {
//                phone = scanner.next();
//                if (phone.matches("[0-9]+") == false || phone.length() < 5) {
//                    System.out.println("Wrong phone number!");
//                    phone = "";
//                }
//            }

            System.out.println("Enter your e-mail:");
            String email = askString();

            Person p = new Person();
            p.setName(name);
            p.setSurname(surname);
            p.setPhone(phone);
            p.setEmail(email);

            recordlist.add(p);

        System.out.println(p);
    }

    private static String askString() {
        var result = new ArrayList<String>();
        var word = scanner.next();
        if (word.startsWith("\"")) {

            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join(" ", result);
                    return str.substring(1, str.length()-1);
                }
                word = scanner.next();
            } while (true);

        } else {
            return word;
        }
    }
        private static String askPhone() {
            while (true) {
                String phone = askString();
                // checking if there any characters expect digits, spaces, pluses and dashes
                if (phone.chars().anyMatch(c -> !Character.isDigit(c) && c != ' ' && c != '+' && c != '-')) {
                    System.out.println("Only digits, spaces, plus and dash are allowed!");
                    continue;
                }

                // checking how many digits in the entered number (excluding spaces and other non-digits)
                if (phone.chars().filter(Character::isDigit).count() < 5) {
                    System.out.println("At least 5 digits in phone number");
                    continue;
                }

                // validation passed
                return phone;
            }
        }
}
