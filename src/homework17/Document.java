package homework17;

import java.util.List;
import java.util.Optional;

public class Document {
    private List<String> docNumbers;
    private String phoneNumber;
    private String email;

    public Document(List<String> documents, String phoneNumber, String email) {
        this.docNumbers = documents;
        this.phoneNumber = Optional.ofNullable(phoneNumber).orElse("No phone number");
        this.email = Optional.ofNullable(email).orElse("No email");
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNumbers=" + docNumbers +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                '}';
    }
}
