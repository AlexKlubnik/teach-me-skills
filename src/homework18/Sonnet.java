package homework18;

import java.util.List;

public class Sonnet {
    private String firstName;
    private String lastName;
    private String title;
    private List<String> lines;

    public Sonnet(String firstName, String lastName, String title, List<String> lines) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.lines = lines;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Sonnet{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", lines=" + lines +
                '}';
    }
}
