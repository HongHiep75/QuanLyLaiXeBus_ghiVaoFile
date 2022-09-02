package danh.sach;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import chuc.nang.chung.FileIO;
import chuc.nang.chung.HienThongTin;

public abstract class DanhSach<T> implements HienThongTin, FileIO {

	private Set<T> danhSach = new LinkedHashSet<>();

	public Set<T> getDanhSach() {
		return danhSach;
	}

	public void setDanhSach(Set<T> danhSach) {
		this.danhSach = danhSach;
	}

	public void add(T t) {
		this.danhSach.add(t);

	}

	public boolean isEmpty() {
		return this.danhSach.isEmpty();

	}
	public int size() {
		return this.danhSach.size();

	}
	public boolean contains(T t) {
		return this.danhSach.contains(t);

	}

	public T traRaDoiTuongTheoMa(T t) {
		
		if(this.isEmpty()) return null;
		// loc qua cac phan tu trong set neu co them vao list
	    List<T> list =
	    this.danhSach
	        .stream()
	        .filter(p -> p.equals(t))
	        .collect(Collectors.toList());

	    if(list.isEmpty()) {
	    	return null;
	    }else {
	    	return list.get(0);
	    }
	    
	}

	
	
	
	
	
	
}
