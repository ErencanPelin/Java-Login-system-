package controller;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.HashSet;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EmployerController extends Controller<Employer>
{      
    @FXML private TableView<Employee> employeeTV;
    @FXML private Button deleteBtn;
    @FXML private Button selectBtn;
    @FXML private Button PAYGBtn;

    @FXML private TextField nameFilterTf;
    @FXML private TextField emailFilterTf;
    
    private String getNameFilter() {return nameFilterTf.getText(); }
    private String getEmailFilter() {return emailFilterTf.getText(); }
    
    public static Employee selectedEmployee;
    
    @FXML private void initialize()
    {
      
        if (selectedEmployee != null) //we are trying to modify an existing employee or we are loading the PAYG report
        {
                //we are loading the PAYG report
        }
          
        //set buttons inactive at first
        deleteBtn.setDisable(selectedEmployee == null);
        selectBtn.setDisable(selectedEmployee == null);
        PAYGBtn.setDisable(selectedEmployee == null);

        //add listener to the table view
        employeeTV.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldEmployee, newAccount) -> 
                {
                    selectedEmployee = getSelectedEmployee();
                     deleteBtn.setDisable(selectedEmployee == null);
                     selectBtn.setDisable(selectedEmployee == null);
                     PAYGBtn.setDisable(selectedEmployee == null);
                             });

        //add listener to both text field filters
        nameFilterTf.textProperty().addListener((event, oldTxt, newTxt) ->  
               model.filterList(getNameFilter(), (getEmailFilter().length() < 1) ? getNameFilter() : getEmailFilter()) //update filters
        );
        emailFilterTf.textProperty().addListener((event, oldTxt, newTxt) ->  
               model.filterList((getNameFilter().length() < 1) ? getEmailFilter() : getNameFilter(), getEmailFilter()) //update filters
        );    
    }
    
    private Employee getSelectedEmployee()
    {
        return employeeTV.getSelectionModel().getSelectedItem();
    }
    
    public Employee getStaticEmployee()
    {
        return selectedEmployee;
    }
    
    @FXML private void handleAdd() throws IOException
    {     
        //disable buttons
        deleteBtn.setDisable(true);
        selectBtn.setDisable(true);
        PAYGBtn.setDisable(true);
            
        selectedEmployee = null;
        //load next window
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/view/edit.png"));
        newStage.setX(670);
        newStage.setY(0);

        ViewLoader.showStage(model, "/view/employee.fxml", "Adding New Employee", newStage);
    }
    
    @FXML private void handleDelete()
    {
        model.removeEmployee(getSelectedEmployee());
    }
    
    @FXML private void handleSelect() throws IOException
    {
        //disable buttons
        deleteBtn.setDisable(true);
        selectBtn.setDisable(true);
        PAYGBtn.setDisable(true);
        
        //load next window
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/view/edit.png"));
        newStage.setX(670);
        newStage.setY(0);
        
        ViewLoader.showStage(model, "/view/employee.fxml", "Adding New Employee", newStage);
    }
    
    @FXML private void handlePAYG() throws IOException
    {
        //disable buttons
        deleteBtn.setDisable(true);
        selectBtn.setDisable(true);
        PAYGBtn.setDisable(true);
        
        //load next window
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/view/edit.png"));
        newStage.setX(670);
        newStage.setY(600);
        
        ViewLoader.showStage(model, "/view/PAYG.fxml", selectedEmployee.getName() + "PAYG Report", newStage);
    }

    @FXML private void handleSTP() throws IOException
    {
        STP newSTP = new STP(model);
        
        //disable buttons
        deleteBtn.setDisable(true);
        selectBtn.setDisable(true);
        PAYGBtn.setDisable(true);
        
        //load next window
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("/view/stp.jpg"));
        newStage.setX(670);
        newStage.setY(0);
        
        ViewLoader.showStage(newSTP, "/view/STP.fxml", "STP Report", newStage);
    }
    
    @FXML private void handleClose()
    {
        stage.close();
    }
    
    public final Employer getEmployer() { return model; }
}
