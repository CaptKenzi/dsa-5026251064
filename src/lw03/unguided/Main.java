package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> daftarMahasiswa = new HashSet<>();
        Scanner pembacaDaftar = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (pembacaDaftar.hasNextLine()) {
            String nrp = pembacaDaftar.nextLine();
            daftarMahasiswa.add(nrp);
        }
        pembacaDaftar.close();

        Set<String> sudahMasuk = new HashSet<>();
        List<String> catatan = new ArrayList<>();
        int jumlahGagal = 0;

        Scanner pembacaMasuk = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (pembacaMasuk.hasNextLine()) {
            String nrp = pembacaMasuk.nextLine();

            if (daftarMahasiswa.contains(nrp) && !sudahMasuk.contains(nrp)) {
                sudahMasuk.add(nrp);
                catatan.add(nrp + ": Checked in");
            } else if (sudahMasuk.contains(nrp)) {
                catatan.add(nrp + ": Rejected (already checked in)");
                jumlahGagal++;
            } else {
                catatan.add(nrp + ": Rejected (not registered)");
                jumlahGagal++;
            }
        }
        pembacaMasuk.close();

        System.out.println("===== Event Check-In Results =====");
        for (String entri : catatan) {
            System.out.println(entri);
        }

        int totalDaftar = daftarMahasiswa.size();
        int totalHadir = sudahMasuk.size();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + totalDaftar);
        System.out.println("Successful check-ins: " + totalHadir);
        System.out.println("Absent students: " + (totalDaftar - totalHadir));
        System.out.println("Rejected attempts: " + jumlahGagal);
    }
}
