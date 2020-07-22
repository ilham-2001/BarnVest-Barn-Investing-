package Controller;

import Model.AccountDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    @FXML private TextField tfNamaPanjang;
    @FXML private TextField tfUsername;
    @FXML private ChoiceBox cbxJenisKelamin;
    @FXML private ChoiceBox cbxStatusUser;
    @FXML private TextField tfNoKTPorKK;
    @FXML private TextField tfEmail;
    @FXML private TextField tfNoTelp;
    @FXML private TextField tfPassword;
    
    private AccountDataClass AccData[];
    private int idx = 0;
    
    // Show sign up oage
    @FXML
    private void handleButtonSubmit(ActionEvent event) throws IOException{
        
        loadMethod();
        while(AccData[idx] != null){
            idx++;
        }
            AccData[idx] = new AccountDataClass(tfUsername.getText(), tfPassword.getText(), 
                    tfEmail.getText(), tfNamaPanjang.getText(), (String) cbxJenisKelamin.getValue(),
                    tfNoKTPorKK.getText(), (String) cbxStatusUser.getValue(), tfNoTelp.getText());
            
            saveMethod();
        
            Parent submitParent = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
            Scene submitScene = new Scene(submitParent);
        
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Home Page");
            stage.setScene(submitScene);
            stage.show();
    }
    
    // Show login page
    @FXML
    private void handleButtonBack(ActionEvent event)throws IOException{
        Parent backParent = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
        Scene backScene = new Scene(backParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Log In BarnVest");
        stage.setScene(backScene);
        stage.show();
    }       
    
    // Save registered data to XML
    public void saveMethod(){
        XStream save = new XStream(new StaxDriver());
        String xml = save.toXML(AccData);
        
        try{
            FileOutputStream sd = new FileOutputStream("savedData.xml");
            byte[] bit = xml.getBytes("UTF-8");
            sd.write(bit);
            sd.close();
        }catch(IOException e){
            System.err.println("Terjadi kesalahan"+e.getMessage());
        }
    }
    
    // Take XML data file
    public void loadMethod(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream ld = new FileInputStream("savedData.xml");
            int isi, i=0;char c; String s="";
            while((isi = ld.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            AccData = (AccountDataClass[])xstream.fromXML(s);
            ld.close();
            
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        cbxJenisKelamin.getItems().addAll("-Jenis Kelamin-", "Laki-Laki", "Perempuan");
        cbxJenisKelamin.setValue("-Jenis Kelamin-");
        
        cbxStatusUser.getItems().addAll("-Pilih-", "Masyarakat Umum", "Investor", "Peternak");
        cbxStatusUser.setValue("-Pilih-");
        
        AccData = new AccountDataClass[999];
        
        AccData[0] = new AccountDataClass("admin", "admin", "admin.go", "Admin",
                "Laki-Laki", "NIK0", "Masyarakat Umum", "000000000000");
        AccData[1] = new AccountDataClass( "khirana", "khirana", "khirana.go",
                "Khirana Dwi Cahyo", "Laki-Laki", "NIK-----", "Investor", "000000000000");
        AccData[2] = new AccountDataClass( "ilham", "ilham", "ilham.go", "Muh. Ilham R",
                "Laki-Laki", "NIK-----", "Investor", "000000000000");
        AccData[3] = new AccountDataClass( "akmal", "akmal", "akmal.go", "Akmal Perdana", 
                " Laki-Laki", "NIK-----", "Investor", "000000000000");
        AccData[4] = new AccountDataClass( "rio", "rio", "rio.go", "Rio Yuda Sakti", 
                " Laki-Laki", "NIK-----", "Investor", "000000000000"); 
    }    
}
