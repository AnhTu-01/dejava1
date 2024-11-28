import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Lấy instance của QLNV
        QLNV qlnv = QLNV.getInstance();

        // Nhập danh sách nhân viên
        HashMap<String, NV> dsNV = NV.nhapDanhSachNV();

        // Thêm observer
        ThongBaoHR hr = new ThongBaoHR();
        ThongBaoPM pm = new ThongBaoPM();
        qlnv.themObserver(hr);
        qlnv.themObserver(pm);

        // Thêm nhân viên vào hệ thống và gửi thông báo
        dsNV.values().forEach(qlnv::themNhanVien);

        // Sử dụng CompletableFuture để xử lý bất đồng bộ
        AsyncTasks.sapXepTheoLuongKhoiDiem(qlnv.getDsNV())
                .thenCompose(sortedList -> AsyncTasks.hienThiDanhSachNhanVien(sortedList))
                .join();
    }
}
