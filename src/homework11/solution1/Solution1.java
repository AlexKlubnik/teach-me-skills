package homework11.solution1;

public class Solution1 {
    public static void main(String[] args) {
        String documentNumber = "5553-Abc-4567-def-1a2b";

        try {
            StringMethods.containsAbc(documentNumber);
        } catch (MySubstringException e) {
            System.out.println(e.getMessage());
        }

        try {
            StringMethods.startsWith555(documentNumber);
        } catch (MySubstringException e) {
            System.out.println(e.getMessage());
        }

        try {
            StringMethods.endsWith1a2b(documentNumber);
        } catch (MySubstringException e) {
            System.out.println(e.getMessage());
        }
    }
}
