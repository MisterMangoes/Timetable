package controller;

import MVC.Controller;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import model.*;

public class AddStudentController extends Controller<University>{
    @FXML private Button cancelBtn;
    @FXML private Button addStuBtn;
    @FXML private TextField stuNumberTf;
    @FXML private TextField stuNameTf;
    @FXML private RadioButton fullRb;
    @FXML private RadioButton partRb;
    @FXML private CheckBox scholarshipCb;
    @FXML private ToggleGroup attendanceTg;
    @FXML private Text errorText;
    
    @FXML private void initialize() {
        stuNumberTf.textProperty().addListener((observable, oldText, newText) -> updateButtons());
        stuNameTf.textProperty().addListener((observable, oldText, newText) -> updateButtons());
        attendanceTg.selectedToggleProperty().addListener((observable, oldSelection, newSelection) -> updateButtons());
    }
  
    public final University getUniversity() { return model; }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception {
        stage.close();
    }
    
    private void updateButtons(){
        addStuBtn.setDisable(stuNumberTf.getText().isEmpty() || stuNameTf.getText().isEmpty() || attendanceTg.getSelectedToggle() == null);
    }
    
    @FXML private void handleAdd(ActionEvent event) throws Exception {
        try{
            String attendance = attendanceTg.getSelectedToggle().getUserData().toString();
            getUniversity().addStudent(stuNumberTf.getText(), stuNameTf.getText(), attendance, scholarshipCb.isSelected());
            stage.close();
        }
        catch(Exception e){
            errorText.setText(e.getMessage());
        }
    }
}