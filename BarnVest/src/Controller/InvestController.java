package Controller;

import Model.AccountDataClass;
import Model.UserDataClass;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InvestController implements Initializable{
    
    @FXML private TextField tfNama;
    @FXML private TextField tfJumlahInvest;
    @FXML private TableView <UserDataClass> tvInvestasi;
    @FXML private TableColumn<UserDataClass, String> tcnama;
    @FXML private TableColumn<UserDataClass, Integer> tcTarget;
    @FXML private TableColumn<UserDataClass, Integer> tcTerkumpul;
    @FXML private TableColumn<UserDataClass, String> tcAlamat;
    @FXML private TableColumn<UserDataClass, String> tcStatus;
    
    UserDataClass[] user;
    AccountDataClass[] acc;
    
    @FXML
    private void handleButtonInvest(ActionEvent event){    
    int idx = tvInvestasi.getSelectionModel().getSelectedIndex();
    int i = 0;
    
    try{
        String nama = tfNama.getText();
        
        int invest = Math.abs(Integer.parseInt(tfJumlahInvest.getText()));
        
        if(user[idx].getStatus().equals("Open for invest") && user[idx].getJumlahInvest() < user[idx].getTarget()){
            user[idx].setJumlahInvest(invest);
            
        if(user[idx].getJumlahInvest() >= user[idx].getTarget()){
            user[idx].setStatus("Closed for invest");
        }
        
        while(acc[i] != null){
            if(acc[i].getUserName().equals(nama)){
                acc[i].setJumlahInvestasi(invest);
                saveMethodAccount();
            }
            i++;
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Transaction Success");
        alert.showAndWait();
                
        saveMethodTable();
        loadMethodTable();
        }
        else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The following barn is closed for invest or reach the target");
        alert.setContentText("Transaction Failed");
        alert.showAndWait();
        }
        
    }
    catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Requirement Found!!!");
        alert.setHeaderText("Fill all the requirement");
        alert.setContentText(null);
        alert.showAndWait();
        }
    }
   
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
    
    private void loadMethodTable(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream fis = new FileInputStream("savedFileTableData.xml");
            int isi, i=0;char c; String s="";
            
            while((isi = fis.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            user = (UserDataClass[])xstream.fromXML(s);
            fis.close();
            
            for(UserDataClass udc : user){
                tvInvestasi.getItems().add(udc);
            }System.out.println("");
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    private void saveMethodTable(){ 
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
    
    private void saveMethodAccount(){ 
        XStream save = new XStream(new StaxDriver());
        String xml = save.toXML(acc);
        
        try{
            FileOutputStream sd = new FileOutputStream("savedData.xml");
            byte[] bit = xml.getBytes("UTF-8");
            sd.write(bit);
            sd.close();
        }catch(IOException e){
            System.err.println("Terjadi kesalahan"+e.getMessage());
        }
    }
    
    private void loadMethodAccount(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream fis = new FileInputStream("savedData.xml");
            int isi, i=0;char c; String s="";
            
            while((isi = fis.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            acc = (AccountDataClass[])xstream.fromXML(s);
            fis.close();
            
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new UserDataClass[999];
        acc = new AccountDataClass[999];
        loadMethodTable();
        loadMethodAccount();
        
        tcnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        tcTerkumpul.setCellValueFactory(new PropertyValueFactory<>("jumlahInvest"));
        tcAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }
    
}
