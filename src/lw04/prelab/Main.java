package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dataScanner = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int totalBranches = dataScanner.nextInt();
        int[] stabilityValues = new int[totalBranches];
        for (int idx = 0; idx < totalBranches; idx++) {
            stabilityValues[idx] = dataScanner.nextInt();
        }

        int totalTargets = dataScanner.nextInt();
        int[] searchTargets = new int[totalTargets];
        for (int idx = 0; idx < totalTargets; idx++) {
            searchTargets[idx] = dataScanner.nextInt();
        }
        dataScanner.close();

        int maxPasses = 3;
        for (int i = 1; i <= maxPasses && i < totalBranches; i++) {
            int currentKey = stabilityValues[i];
            int j = i - 1;
            
            while (j >= 0 && stabilityValues[j] < currentKey) {
                stabilityValues[j + 1] = stabilityValues[j];
                j--;
            }
            stabilityValues[j + 1] = currentKey;
        }

        for (int idx = 0; idx < totalBranches; idx++) {
            if (idx > 0) {
                System.out.print(" ");
            }
            System.out.print(stabilityValues[idx]);
        }
        System.out.println();

        for (int t = 0; t < totalTargets; t++) {
            int matchIdx = -1;
            for (int idx = 0; idx < totalBranches; idx++) {
                if (stabilityValues[idx] == searchTargets[t]) {
                    matchIdx = idx;
                    break;
                }
            }

            if (matchIdx != -1) {
                System.out.println(searchTargets[t] + ": FOUND at index " + matchIdx);
            } else {
                System.out.println(searchTargets[t] + ": NOT FOUND");
            }
        }
    }
}