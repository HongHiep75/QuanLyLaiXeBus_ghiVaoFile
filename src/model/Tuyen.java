package model;

import java.util.Objects;

import chuc.nang.chung.HienThongTin;

public class Tuyen implements HienThongTin {

	private static int AUTO = 100;
	private int maTuyen;
	private double khoangCach;
	private int soDiemDung;

	public Tuyen(int maTuyen, double khoangCach, int soDiemDung) {
		super();
		++AUTO;
		this.maTuyen = maTuyen;
		this.khoangCach = khoangCach;
		this.soDiemDung = soDiemDung;
	}

	public Tuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}

	public Tuyen(double khoangCach, int soDiemDung) {
		super();
		this.maTuyen = AUTO++;
		this.khoangCach = khoangCach;
		this.soDiemDung = soDiemDung;
	}

	public static int getAUTO() {
		return AUTO;
	}

	public static void setAUTO(int aUTO) {
		AUTO = aUTO;
	}

	public int getMaTuyen() {
		return maTuyen;
	}

	public void setMaTuyen(int maTuyen) {
		this.maTuyen = maTuyen;
	}

	public double getKhoangCach() {
		return khoangCach;
	}

	public void setKhoangCach(double khoangCach) {
		this.khoangCach = khoangCach;
	}

	public int getSoDiemDung() {
		return soDiemDung;
	}

	public void setSoDiemDung(int soDiemDung) {
		this.soDiemDung = soDiemDung;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append(" + maTuyen: ");
		builder.append(this.maTuyen);
		builder.append(" - Khoảng Cách: ");
		builder.append(this.khoangCach);
		builder.append(" - Số điểm dừng: ");
		builder.append(this.soDiemDung);
		System.out.println(builder.toString());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.maTuyen);
		builder.append("-");
		builder.append(this.khoangCach);
		builder.append("-");
		builder.append(this.soDiemDung);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTuyen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuyen other = (Tuyen) obj;
		return maTuyen == other.maTuyen;
	}

}
