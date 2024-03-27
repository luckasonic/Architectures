import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введіть систему числення: ");
            int base = scanner.nextInt();

            System.out.print("Введіть число: ");
            String number = scanner.next();


            try {
                int decimalNumber = convertToDecimal(number, base);
                System.out.println("Десяткове представлення числа: " + decimalNumber);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: неправильний формат числа або системи числення.");
            }
        }
    }

    private static int convertToDecimal(String number, int base) {
        int result = 0;
        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Непідтримувана система числення. Підтримуються лише системи від 2 до 16.");
        }

        int power = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digitValue;
            if (number.charAt(i) >= 'A' && number.charAt(i) <= 'F') {
                digitValue = number.charAt(i) - 'A' + 10;
            } else {
                digitValue = Character.getNumericValue(number.charAt(i));
            }
            if (digitValue >= base) {
                throw new NumberFormatException("Неправильний формат числа або системи числення.");
            }

            result += digitValue * Math.pow(base, power);
            power++;
        }
if(number.charAt(0) == '-'){
            result = -result;
}
        return result;
    }
}
