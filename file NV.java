import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class NV {
    private String maNV; 
    private String hoTen; 
    private LocalDate ngayVaoLam; 
    private double luongKhoiDiem; 
    private String cachTra; 

   
    public NV(String maNV, String hoTen, LocalDate ngayVaoLam, double luongKhoiDiem, String cachTra) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngayVaoLam = ngayVaoLam;
        this.luongKhoiDiem = luongKhoiDiem;
        this.cachTra = cachTra;
    }

    
    public String getMaNV() {
        return maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public double getLuongKhoiDiem() {
        return luongKhoiDiem;
    }

    public String getCachTra() {
        return cachTra;
    }

    @Override
    public String toString() {
        return "NV{" +
                "maNV='" + maNV + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngayVaoLam=" + ngayVaoLam +
                ", luongKhoiDiem=" + luongKhoiDiem +
                ", cachTra='" + cachTra + '\'' +
                '}';
    }

    // Static method to input data
    public static HashMap<String, NV> nhapDanhSachNV() {
        HashMap<String, NV> dsNV = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Nhập mã NV (hoặc # để kết thúc): ");
                String maNV = scanner.nextLine();
                if (maNV.equals("#")) break;

                if (dsNV.containsKey(maNV)) {
                    System.out.println("Mã NV đã tồn tại. Vui lòng nhập mã khác.");
                    continue;
                }

                System.out.print("Nhập họ tên NV: ");
                String hoTen = scanner.nextLine();

                System.out.print("Nhập ngày vào làm (yyyy-MM-dd): ");
                LocalDate ngayVaoLam = LocalDate.parse(scanner.nextLine());

                System.out.print("Nhập lương khởi điểm: ");
                double luongKhoiDiem = Double.parseDouble(scanner.nextLine());

                System.out.print("Nhập cách trả lương (duan/ngay): ");
                String cachTra = scanner.nextLine();
                if (!cachTra.equals("duan") && !cachTra.equals("ngay")) {
                    throw new IllegalArgumentException("Cách trả lương phải là 'duan' hoặc 'ngay'.");
                }

                NV nv = new NV(maNV, hoTen, ngayVaoLam, luongKhoiDiem, cachTra);
                dsNV.put(maNV, nv);
                System.out.println("Thêm nhân viên thành công!");
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập lại.");
            } catch (NumberFormatException e) {
                System.out.println("Lương khởi điểm không hợp lệ. Vui lòng nhập lại.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return dsNV;
    }
}
