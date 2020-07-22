
package Controller;

import Model.UserDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterPageController implements Initializable {
    
    @FXML private TextField tfNama;
    @FXML private TextField tfAlamat;
    @FXML private TextField tfJumlah;
    @FXML private TextField tfTarget;
    @FXML private ChoiceBox cbJenisHwn;
    @FXML private DatePicker dpTanggal;
    
    private ObservableList<UserDataClass> listUser = FXCollections.observableArrayList();
    private UserDataClass[] user;
    private int idx = 0;
    
    
    @FXML
    private void RegisteredHandleButton(ActionEvent event) throws IOException{ 
        loadMethod();
        
        while(user[idx] != null){
            idx++;
        }
        
        user[idx] = new UserDataClass(tfNama.getText(), 0, 
                Integer.parseInt(tfTarget.getText()), tfAlamat.getText(), dpTanggal.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), 
        (String) cbJenisHwn.getValue(), Integer.parseInt(tfJumlah.getText()), "Open for invest");
        
        saveMethod();
        
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePagePeternak.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(sewaScene);
        stage.show();
        System.out.println("Registered button is clicked!");
    }
    
    private void saveMethod(){ 
        XStream save = new XStream(new StaxDriver());
        String xml = save.toXML(user);
        
        try{
            FileOutputStream sd = new FileOutputStream("savedFileTableData.xml");
            byte[] bit = xml.getBytes("UTF-8");
            sd.write(bit);
            sd.close();
        }catch(IOException e){
            System.err.println("Terjadi kesalahan"+e.getMessage());
        }
    }
    
    @FXML
    private void BackHandleButton(ActionEvent event) throws IOException{  
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePagePeternak.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(sewaScene);
        stage.show();
    }
    
    public void loadMethod(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream fis = new FileInputStream("SavedFileTableData.xml");
            int isi, i=0;char c; String s="";
            
            while((isi = fis.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            user = (UserDataClass[])xstream.fromXML(s);
            fis.close();
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbJenisHwn.getItems().addAll("-Pilih Jenis-","Sapi","Kambing");
        cbJenisHwn.setValue("-Pilih Jenis-");
        user = new UserDataClass[999];
        
        user[0] = new UserDataClass("Ternak Budi", 0, 9000000, "Kebumen", "18-02-2020", "Sapi", 50, "Open for invest");
        user[1] = new UserDataClass("Ternak Kesbor", 0, 10000000, "Kulon Progo", "20-05-2020", "Sapi", 100, "Open for invest");
        user[2] = new UserDataClass("Ternak Kiwil", 0, 6000000, "Sleman", "18-04-2020", "Sapi", 150, "Open for invest");
        user[3] = new UserDataClass("Ternak Bambang", 0, 1500000, "Kebumen", "19-03-2020", "Sapi", 70, "Open for invest");

    }    
    
}
