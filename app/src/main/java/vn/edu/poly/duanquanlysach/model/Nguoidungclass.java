package vn.edu.poly.duanquanlysach.model;

public class Nguoidungclass {

    public String name;
    public String tentk;
    public  String matkhau;
    public String sdt;


    public Nguoidungclass() {
    }

    public Nguoidungclass(String name, String tentk, String matkhau, String sdt) {
        this.name = name;
        this.tentk = tentk;
        this.matkhau = matkhau;
        this.sdt = sdt;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


}
