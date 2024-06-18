import java.sql.*;
import java.util.Scanner;

public class BukuDAO {

    public static void insertBuku(Scanner scanner) {
        System.out.print("Masukan Judul Buku: ");
        String judul_buku = scanner.nextLine();
        System.out.print("Masukan Tahun Terbit: ");
        int tahun_terbit = scanner.nextInt();
        System.out.print("Masukan Stok: ");
        int stok = scanner.nextInt();
        System.out.print("Masukan ID Penulis: ");
        int penulis = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO buku (judul_buku, tahun_terbit, stok, penulis) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, judul_buku);
            ps.setInt(2, tahun_terbit);
            ps.setInt(3, stok);
            ps.setInt(4, penulis);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBuku(Scanner scanner) {
        System.out.print("Masukan ID Buku: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Masukan Judul Buku Baru: ");
        String judul_buku = scanner.nextLine();
        System.out.print("Masukan Tahun Terbit Baru: ");
        int tahun_terbit = scanner.nextInt();
        System.out.print("Masukan Stok Baru: ");
        int stok = scanner.nextInt();
        System.out.print("Masukan ID Penulis Baru: ");
        int penulis = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE buku SET judul_buku=?, tahun_terbit=?, stok=?, penulis=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, judul_buku);
            ps.setInt(2, tahun_terbit);
            ps.setInt(3, stok);
            ps.setInt(4, penulis);
            ps.setInt(5, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBuku(Scanner scanner) {
        System.out.print("Masukan ID Buku: ");
        int id = scanner.nextInt();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM buku WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBuku() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM buku");

            // Define column widths
            int idWidth = 5;
            int titleWidth = 40;
            int yearWidth = 5;
            int stockWidth = 5;
            int authorWidth = 30;

            // Print table header
            System.out.printf("%-" + idWidth + "s | %-" + titleWidth + "s | %-" + yearWidth + "s | %-" + stockWidth + "s | %-" + authorWidth + "s\n",
                    "ID", "Judul Buku", "Tahun", "Stok", "Penulis");
            System.out.println("-".repeat(idWidth + titleWidth + yearWidth + stockWidth + authorWidth + 11));

            // Print table rows
            while (rs.next()) {
                String id = wrapText(String.valueOf(rs.getInt("id")), idWidth);
                String judul_buku = wrapText(rs.getString("judul_buku"), titleWidth);
                String tahun_terbit = wrapText(String.valueOf(rs.getInt("tahun_terbit")), yearWidth);
                String stok = wrapText(String.valueOf(rs.getInt("stok")), stockWidth);
                String penulis = wrapText(String.valueOf(rs.getInt("penulis")), authorWidth);

                int maxLines = Math.max(Math.max(id.split("\n").length, judul_buku.split("\n").length),
                        Math.max(tahun_terbit.split("\n").length, Math.max(stok.split("\n").length, penulis.split("\n").length)));
                for (int i = 0; i < maxLines; i++) {
                    System.out.printf("%-" + idWidth + "s | %-" + titleWidth + "s | %-" + yearWidth + "s | %-" + stockWidth + "s | %-" + authorWidth + "s\n",
                            getLine(id, i), getLine(judul_buku, i), getLine(tahun_terbit, i), getLine(stok, i), getLine(penulis, i));
                }
                System.out.println("-".repeat(idWidth + titleWidth + yearWidth + stockWidth + authorWidth + 11));
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBukuWithPenulis() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT buku.id, buku.judul_buku, buku.tahun_terbit, buku.stok, penulis.nama_penulis " +
                    "FROM buku INNER JOIN penulis ON buku.penulis = penulis.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Define column widths
            int idWidth = 5;
            int titleWidth = 40;
            int yearWidth = 5;
            int stockWidth = 5;
            int authorWidth = 30;

            // Print table header
            System.out.printf("%-" + idWidth + "s | %-" + titleWidth + "s | %-" + yearWidth + "s | %-" + stockWidth + "s | %-" + authorWidth + "s\n",
                    "ID", "Judul Buku", "Tahun", "Stok", "Nama Penulis");
            System.out.println("-".repeat(idWidth + titleWidth + yearWidth + stockWidth + authorWidth + 11));

            // Print table rows
            while (rs.next()) {
                String id = wrapText(String.valueOf(rs.getInt("id")), idWidth);
                String judul_buku = wrapText(rs.getString("judul_buku"), titleWidth);
                String tahun_terbit = wrapText(String.valueOf(rs.getInt("tahun_terbit")), yearWidth);
                String stok = wrapText(String.valueOf(rs.getInt("stok")), stockWidth);
                String nama_penulis = wrapText(rs.getString("nama_penulis"), authorWidth);

                int maxLines = Math.max(Math.max(id.split("\n").length, judul_buku.split("\n").length),
                        Math.max(tahun_terbit.split("\n").length, Math.max(stok.split("\n").length, nama_penulis.split("\n").length)));
                for (int i = 0; i < maxLines; i++) {
                    System.out.printf("%-" + idWidth + "s | %-" + titleWidth + "s | %-" + yearWidth + "s | %-" + stockWidth + "s | %-" + authorWidth + "s\n",
                            getLine(id, i), getLine(judul_buku, i), getLine(tahun_terbit, i), getLine(stok, i), getLine(nama_penulis, i));
                }
                System.out.println("-".repeat(idWidth + titleWidth + yearWidth + stockWidth + authorWidth + 11));
            }

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String wrapText(String text, int width) {
        StringBuilder wrappedText = new StringBuilder();
        int start = 0;
        while (start < text.length()) {
            int end = Math.min(start + width, text.length());
            wrappedText.append(text, start, end).append("\n");
            start = end;
        }
        return wrappedText.toString().trim();
    }

    private static String getLine(String text, int line) {
        String[] lines = text.split("\n");
        if (line < lines.length) {
            return lines[line];
        } else {
            return "";
        }
    }
}
