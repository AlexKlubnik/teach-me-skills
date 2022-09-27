package homework11.solution2;

public class Registration {

    public static boolean isRegister(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {

        if (login.length() > 20 || login.contains(" ")) {
            throw new WrongLoginException("Login should be less than 20 symbols and doesn't contain spaces");
        }
        if (password.length() > 20 || password.contains(" ")) {
            throw new WrongPasswordException("Invalid password input. " +
                    "Password should be less than 20 symbols, doesn't contain spaces and have at least one number");
        } else if (password.chars()
                .mapToObj(x -> (char) x)
                .noneMatch(Character::isDigit)) {
            throw new WrongPasswordException("Invalid password input. " +
                    "Password should be less than 20 symbols, doesn't contain spaces and have at least one number");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Password confirm doesn't matches to password");
        } else return true;
    }
}
