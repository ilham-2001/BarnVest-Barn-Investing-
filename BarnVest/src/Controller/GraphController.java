package Controller;

import Model.UserDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


public class GraphController implements Initializable {
    private ObservableList<PieChart.Data> dataChart = FXCollections.observableArrayList();
    UserDataClass user[];
    
    @FXML private PieChart PChart;

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
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/ReportClass.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("");
        stage.setScene(sewaScene);
        stage.show();
    }    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMethod();
        int x = 0, a = 0, b = 0;
        
        while(user[x] != null){
            x++;
        }
        for(int i = 0;i < x;i++){
            if(user[i].getJenisHwn().equals("Sapi")){
                a = a + user[i].getJumlahHwn();
            }
            else if(user[i].getJenisHwn().equals("Kambing")){
                b = b + user[i].getJumlahHwn();
            }
        }
        
        dataChart.add(new PieChart.Data("Sapi", a));
        dataChart.add(new PieChart.Data("Kambing", b));
        PChart.setData(dataChart);
    }    
    
}
