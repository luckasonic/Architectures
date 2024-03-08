package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

    public static Long getLong() throws IOException{
        String s = getString("");
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar(String prompt) throws IOException{
        System.out.print(prompt);
        String s = getString("");
        return s.charAt(0);
    }

    public static Integer getInt(String prompt){
        System.out.print(prompt);
        String s = "";
        s = getString("");
        Integer value = Integer.valueOf(s);
        return value;

    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    public static boolean getBoolean(String s) {
        System.out.println(s);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (input.equals("1")) {
            return true;
        } else if (input.equals("2")) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}