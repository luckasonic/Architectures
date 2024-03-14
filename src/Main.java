import utils.DataInput;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        String substring = DataInput.getString("Введіть шукану підстрічку: ").toLowerCase();
        String[] strings = DataInput.getString("Введіть стрічки, розділені пробілами: ").toLowerCase().split("\\s+");

        for (String inputString : strings) {
            int count = countSubstringOccurrences(substring, inputString);
            System.out.println("Кількість входжень підстрічки в стрічці " + inputString +": " + count);
            occurrences.add(count);
        }
        sortOccurrences(occurrences);
        System.out.println("Відсортовані кількості входжень:");
        for (Integer occurrence : occurrences) {
            System.out.println(occurrence);
        }
    }

    public static int countSubstringOccurrences(String substring, String inputString) {
        int count = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == substring.charAt(0) && i + substring.length() <= inputString.length()) {
                boolean isSubstring = true;
                for (int j = 0; j < substring.length(); j++) {
                    if (inputString.charAt(i + j) != substring.charAt(j)) {
                        isSubstring = false;
                        break;
                    }
                }
                if (isSubstring) {
                    count++;
                    i += substring.length() - 1;
                }
            }
        }
        return count;
    }

    public static void sortOccurrences(ArrayList<Integer> occurrences) {
        for (int i = 0; i < occurrences.size() - 1; i++) {
            for (int j = 0; j < occurrences.size() - i - 1; j++) {
                if (occurrences.get(j) > occurrences.get(j + 1)) {
                    int temp = occurrences.get(j);
                    occurrences.set(j, occurrences.get(j + 1));
                    occurrences.set(j + 1, temp);
                }
            }
        }
    }
}
