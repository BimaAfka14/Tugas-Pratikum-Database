import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert Data");
            System.out.println("2. Update Data");
            System.out.println("3. Delete Data");
            System.out.println("4. Show Data");
            System.out.println("0. Keluar");
            System.out.print("Masukan Pilihan Anda: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    handleInsert(scanner);
                    break;
                case 2:
                    handleUpdate(scanner);
                    break;
                case 3:
                    handleDelete(scanner);
                    break;
                case 4:
                    handleShow(scanner);
                    break;
                case 0:
                    System.out.println("Terima Kasih");
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void handleInsert(Scanner scanner) {
        System.out.println("\nInsert Menu:");
        System.out.println("1. Insert Penulis");
        System.out.println("2. Insert Buku");
        System.out.print("Masukan Pilihan Anda: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (subChoice) {
            case 1:
                PenulisDAO.insertPenulis(scanner);
                break;
            case 2:
                BukuDAO.insertBuku(scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
        }
    }

    private static void handleUpdate(Scanner scanner) {
        System.out.println("\nUpdate Menu:");
        System.out.println("1. Update Penulis");
        System.out.println("2. Update Buku");
        System.out.print("Masukan Pilihan Anda: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (subChoice) {
            case 1:
                PenulisDAO.updatePenulis(scanner);
                break;
            case 2:
                BukuDAO.updateBuku(scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
        }
    }

    private static void handleDelete(Scanner scanner) {
        System.out.println("\nDelete Menu:");
        System.out.println("1. Delete Penulis");
        System.out.println("2. Delete Buku");
        System.out.print("Masukan Pilihan Anda: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (subChoice) {
            case 1:
                PenulisDAO.deletePenulis(scanner);
                break;
            case 2:
                BukuDAO.deleteBuku(scanner);
                break;
            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
        }
    }

    private static void handleShow(Scanner scanner) {
        System.out.println("\nShow Menu:");
        System.out.println("1. Show Penulis");
        System.out.println("2. Show Buku");
        System.out.println("3. Show Semua Data");
        System.out.print("Masukan Pilihan Anda: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (subChoice) {
            case 1:
                PenulisDAO.showPenulis();
                break;
            case 2:
                BukuDAO.showBuku();
                break;
            case 3:
                BukuDAO.showBukuWithPenulis();
                break;
            default:
                System.out.println("Pilihan tidak valid! Silakan coba lagi.");
        }
    }
}
