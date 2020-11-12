package controller;

import MVC.Controller;
import MVC.ViewLoader;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import model.*;

public class UniversityController extends Controller<University>{
    @FXML private Button addStuBtn;
    @FXML private Button remStuBtn;
    @FXML private Button loginBtn;
    @FXML private ListView<Student> stuLv;
    
    @FXML private void initialize() {
        stuLv.getSelectionModel().selectedItemProperty().addListener((observable, oldSubject, newSubject) -> {
                remStuBtn.setDisable(newSubject == null);
                loginBtn.setDisable(newSubject == null);
        });
    }
  
    public final University getUniversity() { return model; }
    
    @FXML private void handleAddStu(ActionEvent event) throws Exception {
        ViewLoader.showStage(getUniversity(), "/view/add_student.fxml", "Add Student", new Stage());
    }
    
    @FXML private void handleRemStu(ActionEvent event) throws Exception {
        Student student = getSelectedStudent();
        ObservableList<Activity> activities = student.getActivities();
        for(Activity i : activities){
            i.withdraw();
        }
        activities.clear();
        getUniversity().getStudents().remove(student);
    }
        
    @FXML private void handleLogin(ActionEvent event) throws Exception {
        Student student = getSelectedStudent();
        ViewLoader.showStage(student, "/view/student.fxml", "Student", new Stage());
    }
    
    private Student getSelectedStudent() {
	return stuLv.getSelectionModel().getSelectedItem();
    }
}
