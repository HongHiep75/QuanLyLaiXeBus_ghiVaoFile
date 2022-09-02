package model;

public class LaiXe extends NhanVien {

	private String trinhDo;

	public LaiXe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LaiXe(String hoVaTen, String diaChi, String soDienThoai, String trinhDo) {
		super(hoVaTen, diaChi, soDienThoai);
		this.trinhDo = trinhDo;
	}

	public LaiXe(int maNhaVien, String hoVaTen, String diaChi, String soDienThoai, String trinhDo) {
		super(maNhaVien, hoVaTen, diaChi, soDienThoai);
		this.trinhDo = trinhDo;
	}

	public LaiXe(int maNhaVien) {
		super(maNhaVien, null, null, null);
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	@Override
	public void hienThongTin() {
		StringBuilder builder = new StringBuilder();
		builder.append("- maNV: ");
		builder.append(this.getMaNhaVien());
		builder.append(" - Họ và tên: ");
		builder.append(this.getHoVaTen());
		builder.append(" - Địa chỉ: ");
		builder.append(this.getDiaChi());

		builder.append(" - Số điện thoại: ");
		builder.append(this.getSoDienThoai());
		builder.append(" - Trình độ: ");
		builder.append(this.getTrinhDo());
		System.out.println(builder.toString());

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(this.getMaNhaVien());
		builder.append("-");
		builder.append(this.getHoVaTen());
		builder.append("-");
		builder.append(this.getDiaChi());

		builder.append("-");
		builder.append(this.getSoDienThoai());
		builder.append("-");
		builder.append(this.getTrinhDo());
		return builder.toString();
	}

}
