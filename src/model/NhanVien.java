package model;

import java.util.Objects;

import chuc.nang.chung.HienThongTin;

public abstract class NhanVien implements HienThongTin {

	private static int AUTO = 10000;
	private int maNhaVien;
	private String hoVaTen;
	private String diaChi;
	private String soDienThoai;

	public NhanVien() {
		super();
		this.maNhaVien = AUTO++;
	}

	public NhanVien(String hoVaTen, String diaChi, String soDienThoai) {
		this.maNhaVien = AUTO++;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public NhanVien(int maNhaVien, String hoVaTen, String diaChi, String soDienThoai) {
		super();
		++AUTO;
		this.maNhaVien = maNhaVien;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getMaNhaVien() {
		return maNhaVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhaVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return maNhaVien == other.maNhaVien;
	}

}
