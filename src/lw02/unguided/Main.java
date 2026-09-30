package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        LinkedList<String[]> daftarOrder = new LinkedList<>();
        LinkedList<String[]> stokMakanan = new LinkedList<>();
        LinkedList<String[]> stokMinuman = new LinkedList<>();
        LinkedList<String[]> orderBerhasil = new LinkedList<>();
        Queue<String[]> antrianOrder = new LinkedList<>();
        Stack<String[]> orderGagal = new Stack<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] data = line.split(" ");
            daftarOrder.add(data);
        }

        input.close();

        stokMakanan.add(new String[]{"Bakso", "2"});
        stokMakanan.add(new String[]{"Sate", "1"});
        stokMakanan.add(new String[]{"Soto", "2"});

        stokMinuman.add(new String[]{"EsTeh", "4"});
        stokMinuman.add(new String[]{"EsJeruk", "2"});

        antrianOrder.addAll(daftarOrder);

        while (!antrianOrder.isEmpty()) {
            String[] order = antrianOrder.poll();
            String makanan = order[1];
            String minuman = order[2];
            
            int idxMakanan = -1;
            if (!makanan.equals("-")) {
                for (int i = 0; i < stokMakanan.size(); i++) {
                    if (stokMakanan.get(i)[0].equals(makanan)) {
                        idxMakanan = i;
                    }
                }
            }

            int idxMinuman = -1;
            if (!minuman.equals("-")) {
                for (int i = 0; i < stokMinuman.size(); i++) {
                    if (stokMinuman.get(i)[0].equals(minuman)) {
                        idxMinuman = i;
                    }
                }
            }

            boolean makananAda = true;
            if (!makanan.equals("-")) {
                int jumlah = Integer.parseInt(stokMakanan.get(idxMakanan)[1]);
                if (jumlah <= 0) {
                    makananAda = false;
                }
            }

            boolean minumanAda = true;
            if (!minuman.equals("-")) {
                int jumlah = Integer.parseInt(stokMinuman.get(idxMinuman)[1]);
                if (jumlah <= 0) {
                    minumanAda = false;
                }
            }

            if (makananAda && minumanAda) {
                if (!makanan.equals("-")) {
                    int jumlah = Integer.parseInt(stokMakanan.get(idxMakanan)[1]);
                    stokMakanan.get(idxMakanan)[1] = String.valueOf(jumlah - 1);
                }
                if (!minuman.equals("-")) {
                    int jumlah = Integer.parseInt(stokMinuman.get(idxMinuman)[1]);
                    stokMinuman.get(idxMinuman)[1] = String.valueOf(jumlah - 1);
                }
                orderBerhasil.add(order);
            } else {
                orderGagal.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : orderBerhasil) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] item : stokMakanan) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] item : stokMinuman) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!orderGagal.isEmpty()) {
            String[] order = orderGagal.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}