package notepad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public final static String DATE_FORMAT = "dd.MM.yyyy";
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public final static String TIME_FORMAT = "HH:mm";
    public final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    static Scanner scanner = new Scanner(System.in);
    static Map<Integer, Record> recordlist = new LinkedHashMap<>();

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
                case "createReminder":
                case "cr":
                    createReminder();
                    break;
                case "createAlarm":
                case "ca":
                    createAlarm();
                    break;
                case "list":
                    printList();
                    break;
                case "show":
                    showByID();
                    break;
                case "expired":
                    showExpired();
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

    private static void showExpired() {
        for (Record r : recordlist.values()) {
            if (r instanceof Expirable) {
                Expirable e = (Expirable) r;
                if (e.isExpired()) {
                    System.out.println(r);
                }
            }
        }
    }

    private static void createAlarm() {
        var alarm = new Alarm();
        addRecord(alarm);
    }

    private static void showByID() {
        System.out.println("Enter person ID to show:");
        int id = askInt();
        Record record = recordlist.get(id);
        System.out.println(record);
//        System.out.println(recordlist.get (id));
//        мой вариант
    }

    private static void createReminder() {
        var reminder = new Reminder();
        addRecord(reminder);
    }

    private static void find() {
        System.out.println("Find what?");
        String str = askString();
        for (Record r : recordlist.values()) {
            if (r.hasSubstring(str)) {
                System.out.println(r);
            }
        }
    }

    private static void createNote() {
        Note note = new Note();
        addRecord(note);
    }

    private static void help() {
        System.out.println("createPerson; cp - create new person");
        System.out.println("createNote; cn - create new a note");
        System.out.println("createReminder; cr - create new reminder");
        System.out.println("list - show all persons");
        System.out.println("show - show person by ID");
        System.out.println("delete - delete person by ID");
        System.out.println("find - find person or note by smt");
        System.out.println("exit - close program");
    }

    private static void removeByID() {
        System.out.println("Enter person ID to remove:");
        int id = askInt();
        recordlist.remove(id);
    }

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
        for (Record p : recordlist.values()) {
            System.out.println(p);
        }
    }

    private static void createPerson() {
        Person p = new Person();
        addRecord(p);
    }

    private static void addRecord(Record p) {
        p.askQuestions();
        recordlist.put(p.getId(), p);
        System.out.println("You have created this record!");
        System.out.println(p);
    }

    public static String askString() {
        var result = new ArrayList<String>();
        var word = scanner.next();
        if (word.startsWith("\"")) {

            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join(" ", result);
                    return str.substring(1, str.length() - 1);
                }
                word = scanner.next();
            } while (true);

        } else {
            return word;
        }
    }

    public static String askPhone() {
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

    public static LocalDate askDate () {
        String d;
        LocalDate date;

        while (true) {
            d = askString();
            try {
                date = LocalDate.parse(d, DATE_FORMATTER);
                return date;
            } catch (Exception e) {
                System.out.println("It isn't a date. Try again!");
            }
        }
    }
    public static LocalTime askTime () {
        String t;
        LocalTime time;

        while (true) {
            t = askString();
            try {
                time = LocalTime.parse(t, TIME_FORMATTER);
                return time;
            } catch (Exception e) {
                System.out.println("It isn't a time. Try again!");
            }
        }
    }
}
