import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String kotaX = "";
        ArrayData kota = new ArrayData();

        // sisipkan 3 kota, pastikan kota dalam keadaan urut
        kota.tambahData("Medan");
        kota.tambahData("Pontianak");
        kota.tambahData("Surabaya");
        kota.tampilkanData();

        // proses interaksi dengan pemakai
        Scanner kbd = new Scanner(System.in);
        char pilihan;

        do {
            pilihan = kota.pilihanMenu();
            switch (pilihan) {
                case '1':
                    System.out.println("Penambahan data. \nKota : ");
                    kotaX = kbd.nextLine();
                    if (!kota.tambahData(kotaX)) {
                        System.out.println("Gagal tambah data, wadah penuh");
                    }
                    break;

                case '2':
                    System.out.println("Penghapusan data. \nKota : ");
                    kotaX = kbd.nextLine();
                    if (!kota.hapusData(kotaX)) {
                        System.out.println("Data yang akan dihapus tidak ditemukan");
                        break;
                    }
                case '3':
                    kota.tampilkanData();
                    break;
                case '4':
                    System.out.println("*** Selesai");
            }
        } while (pilihan != '4');
        kbd.close();
    }

    public static String[] menu() {
        String[] menu = new String[2];
        menu[0] = "1. Tambah kota";
        menu[1] = "2. Hapus kota";
        return menu;
    }

    public static String[] tambahKota(String[] kota) {
        return kota;
    }
}

class ArrayData {
    final int MAKSDATA = 10;
    private String[] kota = new String[10];
    private Integer jumKota;

    public ArrayData() {
        jumKota = 0;
    }

    // MENU
    public char pilihanMenu() {
        char pilihan;
        Scanner kbd = new Scanner(System.in);

        do {
            System.out.println("Menu : ");
            System.out.println("1. Tambah data");
            System.out.println("2. Hapus data");
            System.out.println("3. Tampilkan data");
            System.out.println("4. Selesai");
            System.out.println("Pilihan (1, 2, 3, atau 4 dan tekan Enter) :");

            String baris = kbd.nextLine();

            if (baris.isEmpty()) {
                pilihan = '0';
            } else{
                pilihan = baris.charAt(0);
            }
        } while (pilihan < '1' || pilihan > '4');

        return pilihan;
    }

    // SHOW MENU
    public void tampilkanData() {
        System.out.println("Isi array : ");

        for (int j = 0; j < jumKota; j++) {
            System.out.print(kota[j]);

            if (j != (jumKota - 1)) {
                System.out.print(" - ");
            }
        }
        // by abdul
        System.out.println();
        for (int i = 0; i < kota.length; i++) {
            System.out.println(i+". "+kota[i]);
        }
        System.out.println();
    }

    // TAMBAH DATA
    public boolean tambahData(String kotaBaru) {
        if (jumKota == MAKSDATA) {
            System.out.println("Array sudah penuh");
            return false;
        }

        // cari posisi penyisipan
        Integer posisi = -1;
        for (int j = 0; j < jumKota; j++) {
            if (kotaBaru.compareTo(kota[j]) < 0) {
                posisi = j;
                break;
            }
        }

        if (posisi == -1) {
            // sisipkan di belakang
            kota[jumKota] = kotaBaru;
            jumKota = jumKota + 1;
        } else{
            // kalau poisi tidak sama dengan -1
            // sisipkan pada posisi
            // a. geser dulu
            for (int j = jumKota - 1; j >= posisi; j--) {
                kota[j + 1] = kota[j];
            }

            // b. sisipkan
            kota[posisi] = kotaBaru;
            jumKota += 1;
        }
        return true;
    }

    // HAPUS DATA
    public boolean hapusData(String kotaX) {
        // cari posisi penghapusan
        Integer posisi = -1;
        for (int j = 0; j < jumKota; j++) {
            if (kotaX.compareTo(kota[j]) == 0) {
                // kalau sama
                posisi = j;
                break;
            }
        }

        // kalau tidak ditemukan
        if (posisi == -1) {
            return false;
            // gagal hapus karena data tidak ada
        }

        // penghapusan kota
        if (posisi == (jumKota - 1)) {
            // hapus di bagian akhir
            jumKota -= 1;
        } else{
            //kalau posisi tidak sama dengan yang terakhir

            // gese ke atas untuk penghapusan
            for (int j = posisi + 1; j < jumKota; j++) {
                kota[j - 1] = kota[j];
                jumKota -= 1;
            }
        }
        return true;
    }

}