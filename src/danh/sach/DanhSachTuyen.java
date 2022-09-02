package danh.sach;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import model.Tuyen;
import run.QuanLyPhanCong;

public class DanhSachTuyen extends DanhSach<Tuyen>{

	
	public void themDanhSachTuyen() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Số lượng tuyến muốn thêm: ");
		int soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		while (soLuong <= 0) {
			System.out.println("Nhập lại số lượng (số lượng > 0): ");
			soLuong = QuanLyPhanCong.kiemTraDauVaoInt(sc);
		}
		Tuyen tuyen;
		for (int i = 0; i < soLuong; i++) {
			tuyen = themTuyen(sc);
			this.add(tuyen);
		}

	}

	private Tuyen themTuyen(Scanner sc) {
		System.out.println("----------------------------\n");
		System.out.println("Nhập khoảng cách: ");
		double khoangCach = QuanLyPhanCong.kiemTraDauVaoDouble(sc);
		System.out.println("Nhập số điểm dừng: ");
	    int soDiemDung =  QuanLyPhanCong.kiemTraDauVaoInt(sc);
		Tuyen tuyen = new Tuyen(khoangCach, soDiemDung);
		return tuyen;
	}

	
	
	
	
	
	
	@Override
	public void hienThongTin() {
		this.getDanhSach().stream().forEach(Tuyen::hienThongTin);
		
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
				String khoangCach = data[1];
				String diemDung = data[2];
				
//				// ep kieu du lieu ve int 
				int maId = Integer.parseInt(id);
				double soKhoangCach = Double.parseDouble(khoangCach);
				int soDiemDung = Integer.parseInt(diemDung);
				Tuyen tuyen = new Tuyen(maId, soKhoangCach, soDiemDung);
				this.add(tuyen);
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
	 public boolean chucNangThemTuyen() {
		 Boolean check = this.docThongTinTuFile("DanhSachTuyen");
			if (!check) {
				System.out.println("Không đọc được file thử lại");
				return false;
			}
			this.themDanhSachTuyen();
			check = this.ghiThongTinVaoFile("DanhSachTuyen");
			if (!check) {
				System.out.println("Không ghi được file thử lại");
				return false;
			}
			this.hienThongTin();
          return true;
		}
}
