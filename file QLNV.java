import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Interface Observer
interface QuanSat {
    void capNhat(String message, NV nv);
}

// Thông báo cho phòng nhân sự
class ThongBaoHR implements QuanSat {
    @Override
    public void capNhat(String message, NV nv) {
        System.out.println("HR nhận được thông báo: " + message + " - " + nv);
    }
}

// Thông báo cho đội dự án
class ThongBaoPM implements QuanSat {
    @Override
    public void capNhat(String message, NV nv) {
        System.out.println("PM nhận được thông báo: " + message + " - " + nv);
    }
}

// Lớp quản lý nhân viên
public class QLNV {
    private static QLNV instance;
    private HashMap<String, NV> dsNV;
    private List<QuanSat> dsQS; // Danh sách observer

    private QLNV() {
        dsNV = new HashMap<>();
        dsQS = new ArrayList<>();
    }

    public static QLNV getInstance() {
        if (instance == null) {
            instance = new QLNV();
        }
        return instance;
    }

    public void themObserver(QuanSat observer) {
        dsQS.add(observer);
    }

    public void themNhanVien(NV nv) {
        dsNV.put(nv.getMaNV(), nv);

        // Thông báo cho observer
        for (QuanSat qs : dsQS) {
            if (nv.getCachTra().equals("duan") && qs instanceof ThongBaoPM) {
                qs.capNhat("Nhân viên mới cách trả 'duan'", nv);
            } else if (nv.getCachTra().equals("ngay") && qs instanceof ThongBaoHR) {
                qs.capNhat("Nhân viên mới cách trả 'ngay'", nv);
            }
        }
    }

    public HashMap<String, NV> getDsNV() {
        return dsNV;
    }
}
