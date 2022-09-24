package HomeWork12;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter path to file...");
        String pathToFile = scan.nextLine();
        divideDocumentNumbers(pathToFile);
        scan.close();
    }

    public static void divideDocumentNumbers(String pathToFile) {
        try (Scanner fileScanner = new Scanner(new FileReader(pathToFile));
             FileWriter validWriter =
                     new FileWriter("validNumbers.txt", true);
             FileWriter invalidWriter =
                     new FileWriter("invalidNumbers.txt", true)) {

            while (fileScanner.hasNext()) {
                String docNum = fileScanner.nextLine();
                if ((docNum.startsWith("docnum")
                        || docNum.startsWith("contract"))
                        && docNum.length() == 15) {
                    validWriter.write("\n" + docNum);
                    validWriter.flush();
                } else if (!(docNum.startsWith("docnum") || docNum.startsWith("contract"))) {
                    invalidWriter.write("\n" + docNum);
                    invalidWriter.append(" - The number doesn't start with \"docnum\" or \"contract\"");
                    invalidWriter.flush();
                } else {
                    invalidWriter.write("\n" + docNum);
                    invalidWriter.append(" - The number has less or more than 15 symbols");
                    invalidWriter.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
