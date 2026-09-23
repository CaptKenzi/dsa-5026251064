package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File( "C:\\Users\\kenzi\\OneDrive\\Desktop\\Semester 3\\Algoritma dan Struktur Data\\Praktikum\\dsa-5026251064\\src\\lw01\\unguided\\washes.txt"));

            int total = scanner.nextInt();
            WashService[] washes = new WashService[total];

            for (int i = 0; i < total; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equals("MOTORCYCLE")) {
                    washes[i] = new MotorcycleWash(id, days);
                } else if (type.equals("CAR")) {
                    washes[i] = new CarWash(id, days);
                } else {
                    throw new IllegalArgumentException("Unknown vehicle type: " + type);
                }
            }

            scanner.close();

            for (WashService wash : washes) {
                System.out.println(wash.summary());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: washes.txt not found.");
        }
    }
}