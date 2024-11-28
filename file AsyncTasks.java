import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AsyncTasks {

    // Sắp xếp danh sách nhân viên theo lương khởi điểm
    public static CompletableFuture<List<NV>> sapXepTheoLuongKhoiDiem(HashMap<String, NV> dsNV) {
        return CompletableFuture.supplyAsync(() -> {
            List<NV> sortedList = dsNV.values().stream()
                    .sorted(Comparator.comparingDouble(NV::getLuongKhoiDiem))
                    .collect(Collectors.toList());
            try {
                Thread.sleep(1000); // Delay 1 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sắp xếp hoàn tất.");
            return sortedList;
        });
    }

    // Hiển thị danh sách nhân viên
    public static CompletableFuture<Void> hienThiDanhSachNhanVien(List<NV> danhSach) {
        return CompletableFuture.runAsync(() -> {
            danhSach.forEach(System.out::println);
            try {
                Thread.sleep(2000); // Delay 2 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hiển thị hoàn tất.");
        });
    }
}
