package Controller;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReportClassController implements Initializable {

    @FXML private TableView <UserDataClass> tvReport;
    @FXML private TableColumn<UserDataClass, String> tcnama;
    @FXML private TableColumn<UserDataClass, Integer> tcTarget;
    @FXML private TableColumn<UserDataClass, Integer> tcTerkumpul;
    @FXML private TableColumn<UserDataClass, String> tcAlamat;
    @FXML private TableColumn<UserDataClass, String> tcTanggal;
    @FXML private TableColumn<UserDataClass, String> tcJenisHwn;
    @FXML private TableColumn<UserDataClass, Integer> tcJumlahHwn;
    @FXML private TableColumn<UserDataClass, String> tcStatus;
    
    UserDataClass[] user;
     
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePagePeternak.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("");
        stage.setScene(sewaScene);
        stage.show();
    }
    
    @FXML
    private void handleButtonEdit(ActionEvent event) throws IOException{
        UserDataClass ucd = tvReport.getSelectionModel().getSelectedItem();
        int index = tvReport.getSelectionModel().getSelectedIndex();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ReportClassController.class.getResource("/View/UpdateReportClass.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);

        Stage stage = new Stage();
        stage.setTitle("Update Data");
        
        UpdateReportClassController controller = loader.getController();

        controller.setIndex(index);
        controller.setDefault(ucd);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void handleButtonGraph(ActionEvent event) throws IOException{
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/Graph.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("");
        stage.setScene(sewaScene);
        stage.show();
    }
    
    @FXML
    private void handleButtonDelete(ActionEvent event){
        int i = 0;
        
        int delete = tvReport.getSelectionModel().getSelectedIndex();
        tvReport.getItems().remove(delete);
        
        while(user[i] != null){
            i++;
        }
        
        for(int x = delete;x < i;x++){
            user[x] = user[x + 1];
        }
        saveMethod();        
    }
    
    @FXML
    private void handleButtonRefresh(){
        loadMethod();
    }
    
    private void loadMethod(){
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
            
            for(UserDataClass udc : user){
                tvReport.getItems().add(udc);
            }System.out.println("");
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new UserDataClass[999];
        loadMethod();
        
        tcnama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        tcTerkumpul.setCellValueFactory(new PropertyValueFactory<>("jumlahInvest"));
        tcAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        tcTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        tcJenisHwn.setCellValueFactory(new PropertyValueFactory<>("jenisHwn"));
        tcJumlahHwn.setCellValueFactory(new PropertyValueFactory<>("jumlahHwn"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
    }    
    
}
