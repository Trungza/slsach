package vn.edu.poly.duanquanlysach.Model;

public class Hoadonclass {
    public String mahoadon,ngaymuahang;

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getNgaymuahang() {
        return ngaymuahang;
    }

    public void setNgaymuahang(String ngaymuahang) {
        this.ngaymuahang = ngaymuahang;
    }

    public Hoadonclass() {
    }

    public Hoadonclass(String mahoadon, String ngaymuahang) {
        this.mahoadon = mahoadon;
        this.ngaymuahang = ngaymuahang;
    }
}
