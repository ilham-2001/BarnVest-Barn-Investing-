
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class UserDataClass {
  private SimpleStringProperty nama, kota, alamat, tanggal, jenisHwn, Status;
  private SimpleIntegerProperty jumlahHwn, jumlahInvest, target;
  
  private int initial = 0;
  
  public UserDataClass(){
    this("", "", 0);
  }
  
  // Overload
  public UserDataClass(String jenisHwn, String kota, int jumlahHwn){
      this.jenisHwn = new SimpleStringProperty(jenisHwn);
      this.kota = new SimpleStringProperty(kota);
      this.jumlahHwn = new SimpleIntegerProperty(jumlahHwn);
  }
  
  // Overload
    public UserDataClass(String nama, int jumlahInvestasi, int target, String alamat, 
            String tanggal, String jenisHwn, int jumlahHwn, String status){
      this.nama = new SimpleStringProperty(nama);
      this.jumlahInvest = new SimpleIntegerProperty(jumlahInvestasi);
      this.target = new SimpleIntegerProperty(target);
      this.alamat = new SimpleStringProperty(alamat);
      this.tanggal = new SimpleStringProperty(tanggal);
      this.jenisHwn = new SimpleStringProperty(jenisHwn);
      this.jumlahHwn = new SimpleIntegerProperty(jumlahHwn);
      this.Status = new SimpleStringProperty(status);
  }
  
    // Overload
  public UserDataClass(String kota,String alamat, String jenisHwn,int jumlahHwn){
     this.kota = (new SimpleStringProperty(kota));
     this.alamat = (new SimpleStringProperty(alamat));
     this.jenisHwn = (new SimpleStringProperty(jenisHwn));
     this.jumlahHwn = (new SimpleIntegerProperty(jumlahHwn));

  }

    public String getStatus() {
        return Status.get();
    }

    public void setStatus(String Status) {
        this.Status.set(Status);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }
    
    public void setJumlahInvest(int jumlahInvest){
        initial += jumlahInvest;
        this.jumlahInvest.set(initial);
    }
    
    public String getKota() {
        return kota.get();
    }

    public void setKota(String kota) {
        this.kota.set(kota);
    }
    
    public void setTarget(int target){
        this.target.set(target);
    }
    
    public String getTanggal() {
        return tanggal.get();
    }

    public void setTanggal(String tanggal) {
        this.tanggal.set(tanggal);
    }
    
    public int getTarget(){
        return target.get();
    }
    
    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

   
    public String getJenisHwn() {
        return jenisHwn.get();
    }

   
    public void setJenisHwn(String jenisHwn) {
        this.jenisHwn.set(jenisHwn);
    }
   
    public int getJumlahHwn() {
        return jumlahHwn.get();
    }

    
    public void setJumlahHwn(int jumlahHwn) {
        this.jumlahHwn.set(jumlahHwn);
    }
    
    public int getJumlahInvest(){
        return jumlahInvest.get();
    }

    @Override
    public String toString() {
        return  "Nama = " + nama +
                "Kota = " + kota + 
                "Alamat = " + alamat +
                "Jenis Hewan = " + jenisHwn +
                "Jumlah Hewan = " + jumlahHwn + 
                "Status =  " + Status; 
    }
    
    public SimpleStringProperty tcKotaProperty() {
        return  kota;
    }
    public SimpleStringProperty tcJenisHwnProperty() {
        return  jenisHwn;
    }
     public SimpleIntegerProperty tcJumlahHwnProperty() {
        return  jumlahHwn;
    }

    
}

