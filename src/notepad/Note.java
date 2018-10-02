package notepad;

public class Note extends Record {
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String toString;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + getId() +
                "note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean hasSubstring(String str) {
        return note.contains(str);
    }
}
