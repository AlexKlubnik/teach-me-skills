package homework12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter path to file...");
        String pathToFile = scan.nextLine();
        validDocumentNumbers(pathToFile);
        scan.close();
    }

    public static void validDocumentNumbers(String pathToFile) {
        try (Scanner fileScanner = new Scanner(new FileReader(pathToFile))) {

            while (fileScanner.hasNext()) {
                String docNum = fileScanner.nextLine();
                if ((docNum.startsWith("docnum") || docNum.startsWith("contract"))
                        && docNum.length() == 15) {
                    System.out.println("Document number " + docNum + " is valid");
                } else System.out.println("Document number " + docNum + " is invalid");
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
