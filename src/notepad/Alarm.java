package notepad;

import java.time.LocalDate;
import java.time.LocalTime;

public class Alarm extends Note {
    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public void askQuestions() {
        super.askQuestions();
        System.out.println("Enter reminder time (example, 01:01)");
        time = Main.askTime();
    }

    @Override
    public boolean hasSubstring(String str) {
        return super.hasSubstring(str)
                || time.format(Main.TIME_FORMATTER).contains(str);


    }
    @Override
    public String toString(){
        return "Reminder{" +
                "id=" + getId() + ", " +
                "text='" + getText() + '\'' +
                ", time='" + time.format(Main.TIME_FORMATTER) + '\'' +
                '}';
    }
}
