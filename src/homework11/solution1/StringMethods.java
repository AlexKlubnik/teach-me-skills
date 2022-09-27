package homework11.solution1;

import homework11.solution1.MySubstringException;

public class StringMethods {

    static void containsAbc(String documentNumber) throws MySubstringException {
        if (!documentNumber.toLowerCase().contains("abc")){
            throw new MySubstringException("This number doesn't contain \"abc\"");
        } else
        System.out.println("This number contains \"abc\"");
    }

    static void startsWith555(String documentNumber) throws MySubstringException{
        if (!documentNumber.startsWith("555")){
            throw new MySubstringException("This number doesn't start from \"555\"");
        }
        else
            System.out.println("This number starts from \"555\"");
    }

    static void endsWith1a2b(String documentNumber) throws MySubstringException{
        if (!documentNumber.endsWith("1a2b")){
            throw new MySubstringException("This number doesn't end with \"1a2b\"");
        }
        else
            System.out.println("This number ends with \"1a2b\"");
    }



}
