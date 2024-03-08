import utils.DataInput;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> indexes = new ArrayList<>();
        String pidStr = DataInput.getString("Введіть шукану підстрічку: ").toLowerCase();
        while (true) {
            int choice = DataInput.getInt("1 - Ввести стрічку \n2 - Вийти і вивести кількість входжень підстрічки в кожну введену стрічку");
            if (choice == 2) {
               /* int[] arr = new int[indexes.size()];
                for (int i = 0; i < indexes.size(); i++) {
                    arr[i] = indexes.get(i);
                }
                mergeSort(arr, arr.length);*/
                for (int i = 0; i < indexes.size(); i++) {
                    System.out.println("Кількість входжень підстрічки в " + (i + 1) + " стрічці: " + indexes.get(i));
                }
                break;
            }
            String str = DataInput.getString("Введіть стрічку: ").toLowerCase();
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == pidStr.charAt(0) && i + pidStr.length() <= str.length()) {
                    boolean isPid = true;
                    for (int j = 0; j < pidStr.length(); j++) {
                        if (str.charAt(i + j) != pidStr.charAt(j)) {
                            isPid = false;
                            break;
                        }
                    }
                    if (isPid) {
                        count++;
                        i += pidStr.length() - 1;
                    }
                }
            }
            System.out.println("Кількість входжень: " + count);
            indexes.add(count);


        }
    }
        public static void mergeSort(int[] a, int n) {
            if (n < 2) {
                return;
            }
            int mid = n / 2;
            int[] l = new int[mid];
            int[] r = new int[n - mid];

            for (int i = 0; i < mid; i++) {
                l[i] = a[i];
            }
            for (int i = mid; i < n; i++) {
                r[i - mid] = a[i];
            }
            mergeSort(l, mid);
            mergeSort(r, n - mid);

            merge(a, l, r, mid, n - mid);
        }
        public static void merge(int[] a, int[] l, int[] r, int left, int right) {
            int i = 0, j = 0, k = 0;
            while (i < left && j < right) {
                if (l[i] <= r[j]) {
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
            while (i < left) {
                a[k++] = l[i++];
            }
            while (j < right) {
                a[k++] = r[j++];
            }
        }
    }
