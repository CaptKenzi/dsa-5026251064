package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    static void problem1() {
        Scanner inputPlaylist = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (inputPlaylist.hasNextLine()) {
            String[] parts = inputPlaylist.nextLine().split(" ", 2);
            String perintah = parts[0];

            if (perintah.equals("ADD")) {
                playlist.add(parts[1]);
            } else if (perintah.equals("INSERT")) {
                String[] subParts = parts[1].split(" ", 2);
                int posisi = Integer.parseInt(subParts[0]);
                playlist.add(posisi, subParts[1]);
            } else if (perintah.equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }

        inputPlaylist.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Scanner inputPeserta = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> pesertaUnik = new LinkedHashSet<>();
        int totalDuplikat = 0;

        while (inputPeserta.hasNextLine()) {
            String nama = inputPeserta.nextLine();
            boolean berhasil = pesertaUnik.add(nama);
            if (!berhasil) {
                totalDuplikat++;
            }
        }

        inputPeserta.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + pesertaUnik.size());
        int nomor = 1;
        for (String nama : pesertaUnik) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
        System.out.println("Duplicate registrations: " + totalDuplikat);
    }

    static void problem3() {
        Scanner inputInventory = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stok = new LinkedHashMap<>();
        int gagalJual = 0;

        while (inputInventory.hasNext()) {
            String tipe = inputInventory.next();
            String produk = inputInventory.next();
            int qty = inputInventory.nextInt();

            if (tipe.equals("ADD")) {
                int stokSekarang = stok.getOrDefault(produk, 0);
                stok.put(produk, stokSekarang + qty);
            } else if (tipe.equals("SELL")) {
                if (stok.containsKey(produk) && stok.get(produk) >= qty) {
                    stok.put(produk, stok.get(produk) - qty);
                } else {
                    gagalJual++;
                }
            }
        }

        inputInventory.close();

        System.out.println("===== Problem 3 =====");
        for (String produk : stok.keySet()) {
            System.out.println(produk + ": " + stok.get(produk));
        }
        System.out.println("Failed sales: " + gagalJual);
    }
}
