import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        CuaHangSach cuaHang = new CuaHangSach();
        cuaHang.khoiTao();
        cuaHang.xuLy();
    }

    public static String docChuoi(String s) {
        System.out.print(s);
        return sc.nextLine().trim();
    }

    public static String docChuoiGiuCu(String s, String cu) {
        System.out.print(s + " [" + cu + "]: ");
        String nhap = sc.nextLine().trim();
        return nhap.isEmpty() ? cu : nhap;
    }

    public static int docSoNguyen(String s) {
        while (true) {
            System.out.print(s);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le! Vui long nhap so nguyen.");
            }
        }
    }

    public static double docSoThuc(String s) {
        while (true) {
            System.out.print(s);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Gia tri khong hop le! Vui long nhap so thuc.");
            }
        }
    }

    public static LocalDate docNgay(String s) {
        while (true) {
            System.out.print(s + " (dd/MM/yyyy): ");
            String nhap = sc.nextLine().trim();
            if (nhap.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(nhap, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Dinh dang ngay khong hop le! Vui long nhap dung dinh dang dd/MM/yyyy.");
            }
        }
    }

    public static String dinhDangNgay(LocalDate d) {
        if (d == null) return "";
        return d.format(DATE_FORMATTER);
    }

    public static String dinhDangTien(double x) {
        return String.format("%,.0f VND", x);
    }
}
