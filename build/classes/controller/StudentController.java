package controller;

import MVC.Controller;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.*;

public class StudentController extends Controller<Student>{
    @FXML private TableView stuTv;
    @FXML private TableView subjectTv;
    @FXML private ComboBox subjectCb;
    @FXML private Button withdrawBtn;
    @FXML private Button enrolBtn;
    
    @FXML private void initialize() {
        stuTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> withdrawBtn.setDisable(newSubject == null));
        subjectTv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> {
            if(newSubject != null){
                enrolBtn.setDisable(((Activity)newSubject).getCapacity() == ((Activity)newSubject).getEnrolled());
            }
            else{
                enrolBtn.setDisable(true);
            }
        });
        subjectCb.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> {
                if(newSubject != null){
                    subjectTv.setItems(((Subject)subjectCb.getSelectionModel().getSelectedItem()).getActivities());
                }
        });
    }
  
    public final Student getStudent() { return model; }
    
    @FXML private void handleWithdraw(ActionEvent event) throws Exception {
        getStudent().withdraw((Activity)stuTv.getSelectionModel().getSelectedItem());
        stuTv.getSelectionModel().select(null);
        subjectTv.refresh();
    }
    
    @FXML private void handleEnrol(ActionEvent event) throws Exception {
        getStudent().enrol((Activity)subjectTv.getSelectionModel().getSelectedItem());
        subjectTv.getSelectionModel().select(null);
        subjectTv.refresh();
    }
    
    @FXML private void handleLogout(ActionEvent event) throws Exception {
        stage.close();
    }
    
    public final String getScholarshipString(){
        if(getStudent().getScholarship()){
            return("Yes");
        }
        return("No");
    }
    
    public final String getAttendanceString(){
        if(getStudent().getAttendance().equals("ft")){
            return("Full Time");
        }
        return("Part Time");
    }
}