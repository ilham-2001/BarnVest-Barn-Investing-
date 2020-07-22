package Controller;

import Model.AccountDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProfileAccountClassController implements Initializable {
    @FXML private TextField tfNamaPanjang;
    @FXML private TextField tfUserName;
    @FXML private TextField tfJenisKelamin;
    @FXML private TextField tfStatus;
    @FXML private TextField tfNoKTPorKK;
    @FXML private TextField tfEmail;
    @FXML private TextField tfNoTelp;
    @FXML private TextField tfJumlahInvestasi;
    
    private static int index;
    private AccountDataClass[] AccData;
    
    // BACK BUTTON
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException{
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
        Scene backScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pengisian Data Sewa");
        stage.setScene(backScene);
        stage.show();
    }
    
    public static void setIndex(int id){
        index = id;
    }
    
    private void loadMethod(){
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
        AccData = new AccountDataClass[999];
        loadMethod();
        
        tfNamaPanjang.setText(AccData[index].getNamaLengkap());
        tfUserName.setText(AccData[index].getUserName());
        tfJenisKelamin.setText(AccData[index].getJenisKelamin());
        tfStatus.setText(AccData[index].getStatusUser());
        tfNoKTPorKK.setText(AccData[index].getNoKTPorKK());
        tfEmail.setText(AccData[index].getEmail());
        tfNoTelp.setText(AccData[index].getNoTelp());
        tfJumlahInvestasi.setText(String.valueOf(AccData[index].getJumlahInvestasi()));
    }    
    
}
