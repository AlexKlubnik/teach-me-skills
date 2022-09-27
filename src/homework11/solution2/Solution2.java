package homework11.solution2;

public class Solution2 {
    public static void main(String[] args) {
        String login = "sadsafldw";
        String password = "124kjl";
        String confirmPassword = "124kjl";

        try {
            if (Registration.isRegister(login, password, confirmPassword)) {
                System.out.println("Registration successful");
            }
        } catch (WrongLoginException | WrongPasswordException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
