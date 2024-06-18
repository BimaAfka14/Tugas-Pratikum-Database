import java.sql.*;
import java.util.Scanner;

public class PenulisDAO {

    public static void insertPenulis(Scanner scanner) {
        System.out.print("Masukan Nama Penulis: ");
        String nama_penulis = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO penulis (nama_penulis) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama_penulis);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePenulis(Scanner scanner) {
        System.out.print("Masukan ID Penulis: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Masukan Nama Penulis Baru: ");
        String nama_penulis = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE penulis SET nama_penulis=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama_penulis);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePenulis(Scanner scanner) {
        System.out.print("Masukan ID Penulis: ");
        int id = scanner.nextInt();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM penulis WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPenulis() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM penulis");

            // Define column widths
            int idWidth = 5;
            int nameWidth = 50;

            // Print table header
            System.out.printf("%-" + idWidth + "s | %-" + nameWidth + "s\n", "ID", "Nama Penulis");
            System.out.println("-".repeat(idWidth + nameWidth + 3));

            // Print table rows
            while (rs.next()) {
                String id = wrapText(String.valueOf(rs.getInt("id")), idWidth);
                String nama_penulis = wrapText(rs.getString("nama_penulis"), nameWidth);

                int maxLines = Math.max(id.split("\n").length, nama_penulis.split("\n").length);
                for (int i = 0; i < maxLines; i++) {
                    System.out.printf("%-" + idWidth + "s | %-" + nameWidth + "s\n",
                            getLine(id, i), getLine(nama_penulis, i));
                }
                System.out.println("-".repeat(idWidth + nameWidth + 3));
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
