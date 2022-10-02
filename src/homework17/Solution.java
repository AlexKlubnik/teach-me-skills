package homework17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the path to files");
        File files = new File(scan.nextLine());

        try {
            fileToMapWriter(files).forEach((str, docs) -> System.out.println(str + " - " + docs));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        scan.close();
    }

    static Map<String, Document> fileToMapWriter(File fileFolder) throws FileNotFoundException {

        Map<String, Document> documents = new HashMap<>();

        if (!fileFolder.isDirectory()) {
            throw new FileNotFoundException(fileFolder.getName() + " is not a directory.");
        }

        for (File file : fileFolder.listFiles()) {

            if (!file.getName().matches(".txt$")) {
                throw new FileNotFoundException("Invalid format of file");
            }

            List<String> docNumbers = new ArrayList<>();
            String phoneNumber = null;
            String email = null;
            String fileName = file.getName().substring(0, file.getName().indexOf("."));

            try (Scanner fileScan = new Scanner(new FileReader(file))) {
                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    if (isDocNumber(line))
                        docNumbers.add(line);
                    else if (isPhoneNumber(line))
                        phoneNumber = line;
                    else if (isEmail(line))
                        email = line;
                }

                documents.put(fileName, new Document(docNumbers, phoneNumber, email));

            } catch (FileNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return documents;
    }

    static boolean isDocNumber(String str) {
        Pattern p = Pattern.compile("^\\d{4}-\\w{3}-\\d{4}-\\w{3}-\\d\\w\\d\\w$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    static boolean isPhoneNumber(String str) {
        Pattern p = Pattern.compile("^\\+\\(\\d{2}\\)\\d{7}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    static boolean isEmail(String str) {
        Pattern p = Pattern.compile("^[\\w.%+\\-]+@[a-z0-9.\\-]+\\.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
