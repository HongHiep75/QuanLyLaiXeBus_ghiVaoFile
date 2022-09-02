package danh.sach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Comparator;
import java.util.LinkedHashSet;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import model.LaiXe;
import model.LichTrinhLamViecLaiXe;
import model.Tuyen;
import run.QuanLyPhanCong;

public class DanhSachPhanCong extends DanhSach<LichTrinhLamViecLaiXe> {

	// them thong tin phan cong
	public void themmDanhSachPhanCong() {
		DanhSachLaiXe danhSachLaiXe = new DanhSachLaiXe();
		DanhSachTuyen danhSachTuyen = new DanhSachTuyen();
		danhSachLaiXe.docThongTinTuFile("DanhSachLaiXe");
		danhSachTuyen.docThongTinTuFile("DanhSachTuyen");
		// kiem tra xem danh sach co phan tu hay khong
		// neu khong dung lai
		if (danhSachLaiXe.isEmpty() || danhSachTuyen.isEmpty()) {
			System.out.println("Danh sách lái xe hoặc danh sách tuyến trống");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Chọn sinh Vien bằng cách nhập mã sinh viên");
		int maNhanVien = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		LaiXe laiXe = danhSachLaiXe.traRaDoiTuongTheoMa(new LaiXe(maNhanVien));
		LichTrinhLamViecLaiXe lichTrinhLamViecLaiXe = new LichTrinhLamViecLaiXe(laiXe);
		// kiem tra trong danh sach phan cong da ton tai lai xe chua
		while (laiXe == null || this.contains(lichTrinhLamViecLaiXe)) {
			System.out.println("Nhân viên không tồn tại hoặc nhân viên đã được thêm vui lòng nhập lại");
			maNhanVien = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			laiXe = danhSachLaiXe.traRaDoiTuongTheoMa(new LaiXe(maNhanVien));
			lichTrinhLamViecLaiXe = new LichTrinhLamViecLaiXe(laiXe);
		}

		System.out.println("Thêm công việc cho nhân viên " + laiXe.getHoVaTen());
		System.out.println("Muốn thêm bao nhiêu tuyến ? ");
		int soLuongTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		for (int i = 0; i < soLuongTuyen; i++) {
			System.out.println("Nhập mã tuyến");
			int maTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			Tuyen tuyen = danhSachTuyen.traRaDoiTuongTheoMa(new Tuyen(maTuyen));
			while (tuyen == null) {
				System.out.println("Nhập lại mã tuyến, mã không tồn tại");
				maTuyen = QuanLyPhanCong.kiemTraDauVaoInt(sc);
				tuyen = danhSachTuyen.traRaDoiTuongTheoMa(new Tuyen(maTuyen));
			}
			System.out.println("Nhập số lần đi");
			int soLuot = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			while (soLuot > 15) {
				System.out.println("Nhập số lần không quá 15");
				soLuot = QuanLyPhanCong.kiemTraDauVaoInt(sc);
			}
			lichTrinhLamViecLaiXe.setPhanCong(tuyen, soLuot);
			this.add(lichTrinhLamViecLaiXe);
		}

	}

	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(LichTrinhLamViecLaiXe::hienThongTin);

	}

	@Override
	public boolean ghiThongTinVaoFile(String tenFile) {
		if (this.isEmpty())
			return false;

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/file/" + tenFile))) {

			this.getDanhSach().forEach(t -> {
				try {
					bufferedWriter.write(t.getLaiXe().toString());
					bufferedWriter.newLine();
					// số lượng phân công khi đọc file ta biết cần đọc bao nhiêu lần
					int n = t.getPhanCong().size();
					bufferedWriter.write(n + "");
					bufferedWriter.newLine();
					t.getPhanCong().forEach((k, v) -> {
						try {
							bufferedWriter.write(k.toString() + "-" + v + "");
							bufferedWriter.newLine();
						} catch (IOException e) {
							System.out.println("Không ghi được phân công công viêc");
							e.printStackTrace();
						}

					});
				} catch (IOException e) {
					System.out.println("lỗi trong phần vào file");
					e.printStackTrace();
				}

			});
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean docThongTinTuFile(String tenFile) {
		try (BufferedReader br = new BufferedReader(new FileReader("src/file/" + tenFile))) {
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				LichTrinhLamViecLaiXe lamViecLaiXe = new LichTrinhLamViecLaiXe();
				// cat du lieu tai vi tri "-" thành một mảng
				// cấu trúc lưu trong file: mã nv - tên - dia chi - soDienThoai - trinh do
				// lọc giá trị tương ứng rồi tạo đối tượng LichTrinhLamViecLaiXe
				String data[] = line.split("-");
				String id = data[0];
				String name = data[1];
				String diaChi = data[2];
				String soDienThoai = data[3];
				String trinhdo = data[4];
//				// ep kieu du lieu ve int va double
				int maSV = Integer.parseInt(id);
				LaiXe laiXe = new LaiXe(maSV, name, diaChi, soDienThoai, trinhdo);
				lamViecLaiXe.setLaiXe(laiXe);

				// dọc số lượng để biết có bao nhiêu dòng phân công cần cần đọc
				// vòng lặp duyệt lấy ra số phân công tương ứng
				String soLuongTuyen = line = br.readLine();
				int soLuong = Integer.parseInt(soLuongTuyen);
				for (int i = 0; i < soLuong; i++) {
					line = br.readLine();
					// cat du lieu tai vi tri "-"
					// cấu trúc lưu trong file: mã - khoang cach- diem dung - so lan di
					String dataTuyen[] = line.split("-");
					String idTuyen = dataTuyen[0];
					String khoangCach = dataTuyen[1];
					String diemDung = dataTuyen[2];
					String lanDi = dataTuyen[3];
//					// ep kieu du lieu ve int va double
					int maMon = Integer.parseInt(idTuyen);
					double sokhoangCach = Double.parseDouble(khoangCach);
					int soDiemDung = Integer.parseInt(diemDung);
					int soLanDi = Integer.parseInt(lanDi);
					Tuyen tuyen = new Tuyen(maMon, sokhoangCach, soDiemDung);
					lamViecLaiXe.setPhanCong(tuyen, soLanDi);
					this.add(lamViecLaiXe);
				}

			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			return false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Dữ liệu trong file không khớp");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean chucNangThemPhanCong() {
		Boolean check = this.docThongTinTuFile("DanhSachPhanCong");
		if (!check) {
			System.out.println("Không đọc được file thử lại");
			return false;
		}
		this.themmDanhSachPhanCong();
		check = this.ghiThongTinVaoFile("DanhSachPhanCong");
		if (!check) {
			System.out.println("Không ghi được file thử lại");
			return false;
		}
		this.hienThongTin();
		return true;
	}

	public void sapXepTheoTen() {
		if (this.isEmpty())
			return;
		Set<LichTrinhLamViecLaiXe> list = 
				(Set<LichTrinhLamViecLaiXe>) this.getDanhSach()
				.stream()
				.sorted(Comparator.comparing(LichTrinhLamViecLaiXe::getHoVaTen))
				.collect(Collectors.toCollection(LinkedHashSet::new));
		this.setDanhSach(list);

	}
	public void sapXepTheoSoLuongTuyen() {
		if (this.isEmpty())
			return;
		Set<LichTrinhLamViecLaiXe> list = 
				(Set<LichTrinhLamViecLaiXe>) this.getDanhSach()
				.stream()
				.sorted(Comparator.comparing(LichTrinhLamViecLaiXe::soLuongTuyen)
						.reversed())
				.collect(Collectors.toCollection(LinkedHashSet::new));
		this.setDanhSach(list);
		
	}
	public boolean sapXepDanhSach() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Chọn kiểu sắp xếp");
		System.out.println("1. Theo họ tên lái xe");
		System.out.println("2.Theo số lượng tuyến (giảm dần)");
		int chon = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (chon < 1 || chon > 2) {
			System.out.println("Vui lòng chọn lại");
			chon = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}
		boolean ckeck = this.docThongTinTuFile("DanhSachPhanCong");
		if(!ckeck || this.isEmpty()) {
			return false;
		}

		if (chon == 1) {
			this.sapXepTheoTen();
			this.hienThongTin();
		} else {
			this.sapXepTheoSoLuongTuyen();
			this.hienThongTin();

		}
		this.ghiThongTinVaoFile("DanhSachPhanCong");
		return true;
		
	}
	
	public boolean tongKetQuangDuongLaiXe() {
		boolean ckeck = this.docThongTinTuFile("DanhSachPhanCong");
		if(!ckeck || this.isEmpty()) {
			return false;
		}
		this.getDanhSach().forEach(t ->{
			t.getLaiXe().hienThongTin();
			System.out.println("Tổng khoảng cách chạy trong ngày: " + t.tongKhoangCachChay());
	
		});
		return true;
		
		}
	
}
