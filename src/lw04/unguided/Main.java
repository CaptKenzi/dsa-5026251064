package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dataInput = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int count = dataInput.nextInt();
        int[] weights = new int[count];
        for (int i = 0; i < count; i++) {
            weights[i] = dataInput.nextInt();
        }

        int targetCount = dataInput.nextInt();
        int[] targets = new int[targetCount];
        for (int i = 0; i < targetCount; i++) {
            targets[i] = dataInput.nextInt();
        }
        dataInput.close();

        int[] snapshotPass2 = new int[count];
        int outer = 0;
        while (outer < count - 1) {
            int inner = 0;
            while (inner < count - 1 - outer) {
                if (weights[inner] < weights[inner + 1]) {
                    int tmp = weights[inner];
                    weights[inner] = weights[inner + 1];
                    weights[inner + 1] = tmp;
                }
                inner++;
            }
            if (outer == 1) {
                int copyIdx = 0;
                while (copyIdx < count) {
                    snapshotPass2[copyIdx] = weights[copyIdx];
                    copyIdx++;
                }
            }
            outer++;
        }

        System.out.println("==== After 2 Passes ====");
        int p = 0;
        while (p < count) {
            System.out.print(snapshotPass2[p]);
            if (p < count - 1) {
                System.out.print(" ");
            }
            p++;
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        int s = 0;
        while (s < count) {
            System.out.print(weights[s]);
            if (s < count - 1) {
                System.out.print(" ");
            }
            s++;
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Search Results ====");
        for (int t = 0; t < targetCount; t++) {
            int low = 0;
            int high = count - 1;
            int countSteps = 0;
            int foundIndex = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                countSteps++;
                if (weights[mid] == targets[t]) {
                    foundIndex = mid;
                    break;
                } else if (weights[mid] < targets[t]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (foundIndex != -1) {
                System.out.println(targets[t] + ": FOUND at index " + foundIndex + " (steps: " + countSteps + ")");
            } else {
                System.out.println(targets[t] + ": NOT FOUND (steps: " + countSteps + ")");
            }
        }
    }
}