package lw04.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        int passes = 3;
        int i = 1;
        while (i <= passes && i < n) {
            int key = branches[i];
            int j = i - 1;
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
            i++;
        }

        int idxPrint = 0;
        while (idxPrint < n) {
            System.out.print(branches[idxPrint]);
            if (idxPrint < n - 1) {
                System.out.print(" ");
            }
            idxPrint++;
        }
        System.out.println();

        for (int t = 0; t < m; t++) {
            int currentTarget = targets[t];
            boolean isFound = false;
            int foundIndex = -1;

            int k = 0;
            while (k < n) {
                if (branches[k] == currentTarget) {
                    foundIndex = k;
                    isFound = true;
                    break;
                }
                k++;
            }

            if (isFound) {
                System.out.println(currentTarget + ": FOUND at index " + foundIndex);
            } else {
                System.out.println(currentTarget + ": NOT FOUND");
            }
        }
    }
}