package danh.sach;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.LaiXe;
import run.QuanLyPhanCong;

public class DanhSachLaiXe extends DanhSach<LaiXe> {

	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(LaiXe::hienThongTin);

	}

	public void themNhanVien() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Số lượng lái xe muốn thêm: ");
		int soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Nhập lại số lượng (số lượng > 0): ");
			soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}
		LaiXe laiXe;
		for (int i = 0; i < soLuong; i++) {
			laiXe = themLaiXe(sc);
			this.add(laiXe);
		}

	}

	private LaiXe themLaiXe(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập tên Lái xe: ");
		String tenNhanVien = QuanLyPhanCong.kiemTraDauVaoString(sc);
		System.out.println("Nhập địa chỉ: ");
		String diaChi = sc.nextLine();
		System.out.println("Nhập số điện thoại: ");
		String soDienThoai = QuanLyPhanCong.kiemTraSoDienThoai(sc);
		System.out.println("Trình độ: ");
		String trinhDo = kiemTraTrinhDoInput(sc);
		LaiXe laiXe = new LaiXe(tenNhanVien, diaChi, soDienThoai, trinhDo);
		return laiXe;
	}

	private String kiemTraTrinhDoInput(Scanner sc) {
		Pattern p = Pattern.compile("[^a-fA-F]{1}$");
		String input = sc.nextLine();
		while (p.matcher(input).find()) {
			System.out.println("Thông tin không chính xác trình độ từ A tời F");
			input = sc.nextLine();
		}
		return input;
	}
	@Override
	public boolean ghiThongTinVaoFile(String tenFile) {

		if (this.isEmpty())return false;
			
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/file/" + tenFile))) {

			this.getDanhSach().forEach(t -> {
				try {
					bufferedWriter.write(t.toString());
					bufferedWriter.newLine();
				} catch (IOException e) {
					System.out.println("lỗi trong phần ghi vào file");
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
				// cat du lieu tai vi tri "-" thành một mảng
				// lọc giá trị tương ứng rồi tạo đối tượng lai xe
				String data[] = line.split("-");
				String id = data[0];
				String name = data[1];
				String diaChi = data[2];
				String soDienThoai = data[3];
				String trinhDo = data[4];
//				// ep kieu du lieu ve int 
				int maId = Integer.parseInt(id);
				LaiXe laiXe = new LaiXe(maId, name, diaChi, soDienThoai, trinhDo);
				this.add(laiXe);
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

	 public boolean chucNangThemNhanVien() {
		 Boolean check = this.docThongTinTuFile("DanhSachLaiXe");
			if (!check) {
				System.out.println("Không đọc được file thử lại");
				return false;
			}
			this.themNhanVien();
			check = this.ghiThongTinVaoFile("DanhSachLaiXe");
			if (!check) {
				System.out.println("Không ghi được file thử lại");
				return false;
			}
			this.hienThongTin();
          return true;
		}
	 
}
