package cuahangsach;

public final class GiaoDienConsole {
    private GiaoDienConsole() {
    }

    public static void inTieuDe(String tieuDe) {
        System.out.println();
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |%n", tieuDe);
        System.out.println("+--------------------------------------------------------------+");
    }

    public static void inMuc(String nhan, String giaTri) {
        System.out.printf("  %-18s : %s%n", nhan, giaTri == null ? "" : giaTri);
    }

    public static void inPhanCach() {
        System.out.println("  ------------------------------------------------------------");
    }
}
