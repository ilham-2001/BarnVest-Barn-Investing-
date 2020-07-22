package Model;




public class AccountDataClass {
    private String userName, Password, email, namaLengkap;
    private String jenisKelamin, noKTPorKK, statusUser, noTelp;
    private int jumlahInvestasi = 0;
    
    public AccountDataClass(){
        
    }
    
    public AccountDataClass(String userName, String Password, String email) {
        this.userName = userName;
        this.Password = Password;
        this.email = email;
    }
    
    public AccountDataClass(String userName, String password, String email, String namaLengkap
    , String jenisKelamin, String noKTPorKK, String statusUser, String noTelp){
        this.userName = userName;
        this.Password = password;
        this.email = email;
        this.namaLengkap = namaLengkap;
        this.jenisKelamin = jenisKelamin;
        this.noTelp = noTelp;
        this.statusUser = statusUser;
        this.noKTPorKK = noKTPorKK;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getJumlahInvestasi() {
        return jumlahInvestasi;
    }

    public void setJumlahInvestasi(int JumlahInvestasi) {
        this.jumlahInvestasi += JumlahInvestasi;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKTPorKK() {
        return noKTPorKK;
    }

    public void setNoKTPorKK(String noKTPorKK) {
        this.noKTPorKK = noKTPorKK;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    
    @Override
    public String toString() {
        return "Username: " + userName + 
               "\nPassword: " + Password +
               "\nEmail: " + email +
               "\nJenis Kelamin: " + jenisKelamin +
               "\nStatus: " + statusUser +
               "\nNo.KTP/KK: " + noKTPorKK +
               "\nNo.Telp: " + noTelp +
               "\nJumlah Investasi: " + jumlahInvestasi;
    }
}
