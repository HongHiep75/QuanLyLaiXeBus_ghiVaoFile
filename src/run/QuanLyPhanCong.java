package run;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import danh.sach.DanhSachLaiXe;
import danh.sach.DanhSachPhanCong;
import danh.sach.DanhSachTuyen;

public class QuanLyPhanCong {
	public static void main(String[] args) {
		showMenu();
	}

	public static void menu() {

		System.out.println("Vui lòng chọn chức năng(nhập số):");
		System.out.println("1. Nhập lái xe mới ");
		System.out.println("2. Nhập tuyến mới ");
		System.out.println("3. Nhập phân công cho lái xe");
		System.out.println("4. Sắp xếp danh sách phân công");
		System.out.println("5. Tính tổng khoảng cách đi trong ngày ");
		System.out.println("0.Thoát");
	}

	public static void showMenu() {
		Scanner sc = new Scanner(System.in);
		DanhSachLaiXe danhSachLaiXe = new DanhSachLaiXe();
		DanhSachTuyen danhSachTuyen = new DanhSachTuyen();
		DanhSachPhanCong danhSachPhanCong = new DanhSachPhanCong();
		int luaChon;
		// vong lap chon chuc nang
		System.out.println("Chào mừng bạn tới ứng dụng quản lý lái xe \n");
		do {
			menu();
			luaChon = kiemTraDauVaoInt(sc);
			while (luaChon > 5) {
				System.out.println("Chọn lại chức năng:");
				luaChon = kiemTraDauVaoInt(sc);
			}
			switch (luaChon) {
			// them lai xe
			case 1: {
				boolean check = danhSachLaiXe.chucNangThemNhanVien();
				hienThiKetQua(check);
				break;

			}
			// them tuyen
			case 2: {
				boolean check = danhSachTuyen.chucNangThemTuyen();
				hienThiKetQua(check);
				break;

			}
			// lập bảng phân công
			case 3: {
				boolean check = danhSachPhanCong.chucNangThemPhanCong();
				hienThiKetQua(check);
				danhSachPhanCong.hienThongTin();
				break;

			}
			// sắp xếp bảng phân công
			case 4: {
				Boolean check = danhSachPhanCong.sapXepDanhSach();
				hienThiKetQua(check);
				break;
				// tính tổng khoảng cách đi trong ngày
			}
			case 5: {
				Boolean check = danhSachPhanCong.tongKetQuangDuongLaiXe();
				hienThiKetQua(check);
				break;

			}
			}
		} while (luaChon != 0);

	}

	public static void hienThiKetQua(boolean check) {
		if (check) {
			System.out.println("Thành công");
		} else {
			System.out.println("Kiểm tra danh sách phân công hoặc file");
			System.out.println("Thực hiện thất bại");
		}
	}

	public static double kiemTraDiem(Scanner sc) {
		double input = 0;
		// kiem tra thong tin nhap vao
		input = kiemTraDauVaoDouble(sc);
		while (input < 0 || input > 10) {
			System.out.println("Điểm không lớn hơn 10 và nhỏ hơn 0");
			input = kiemTraDauVaoDouble(sc);
		}
		return input;
	}

//   sử lý ngoại lệ nhập chữ vào ô số
	public static int kiemTraDauVaoInt(Scanner sc) {
		int input = -1;
		// kiem tra thong tin nhap vao
		do {
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Vui lòng nhập lại số");
			}
			sc.nextLine();
		} while (input < 0);
		return input;

	}

	// kiem tra dau vao String
	public static String kiemTraDauVaoString(Scanner sc) {
		Pattern p = Pattern.compile("[^a-zA-z]$");
		String input = sc.nextLine();
		while (p.matcher(input).find()) {
			System.out.println("Thông tin không chính xác vui lòng nhập lại chỉ được nhập chữ");
			input = sc.nextLine();
		}
		return input;

	}

	// kiem tra input double
	public static double kiemTraDauVaoDouble(Scanner sc) {
		double input = -1.0;
		// kiem tra thong tin nhap vao
		do {
			try {
				input = sc.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Vui lòng nhập lại số");
			}
			sc.nextLine();
		} while (input < 0);
		return input;

	}

	// kiem tra so dien thoai
	public static String kiemTraSoDienThoai(Scanner sc) {
		String soDienThoai = null;
		soDienThoai = sc.nextLine();
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Pattern p2 = Pattern.compile("^[0-9]{11}$");
		while (!(p.matcher(soDienThoai + "").find() || p2.matcher(soDienThoai + "").find())) {
			System.out.println("Số điện thoại không đúng (số trong khoảng 10 tới 11 số)");
			soDienThoai = sc.nextLine();
		}
		return soDienThoai;
	}

}
