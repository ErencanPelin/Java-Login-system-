package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import static controller.EmployerController.selectedEmployee;
import static controller.ErrorController.errorMsg;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class EmployeeController extends Controller<Employer> 
{
    
    public String getNameTf() { return nameTf.getText(); }
    public void setNameTf(String nameTf) { this.nameTf.setText(nameTf); }
    public String getEmailTf() { return emailTf.getText(); }
    public void setEmailTf(String emailTf) { this.emailTf.setText(emailTf); }
    public String getPhoneTf() {  return phoneTf.getText(); }
    public void setPhoneTf(String phoneTf) { this.phoneTf.setText(phoneTf); }
    public String getAddressTf() { return addressTf.getText(); }
    public void setAddressTf(String addressTf) { this.addressTf.setText(addressTf); }
    public String getTFNTf() { return TFNTf.getText(); }
    public void setTFNTf(String TFNTf) { this.TFNTf.setText(TFNTf); }
    public int getPaidHrsTf() { return Integer.parseInt(paidHrsTf.getText());  }
    public void setPaidHrsTf(int paidHrsTf) {  this.paidHrsTf.setText(paidHrsTf + "");  }
    public double getHourlyRateTf() { return Double.parseDouble(hourlyRateTf.getText());  }
    public void setHourlyRateTf(double hourlyRateTf) { this.hourlyRateTf.setText(hourlyRateTf + ""); }
    public String getJobTypeTf() {  return jobTypeTf.getText(); }
    public void setJobTypeTf(String jobTypeTf) {  this.jobTypeTf.setText(jobTypeTf); }

    @FXML private TextField nameTf;
    @FXML private TextField emailTf;
    @FXML private TextField phoneTf;
    @FXML private TextField addressTf;
    
    @FXML private TextField TFNTf;
    @FXML private TextField paidHrsTf;
    @FXML private TextField hourlyRateTf;
    @FXML private TextField jobTypeTf;
    
    @FXML private Button addValidateBtn;
    @FXML private Button updateValidateBtn;
    
    private Validator validator;
    
        
    @FXML private void initialize()
    {
        validator = new Validator();
        addValidateBtn.setDisable(selectedEmployee != null);
        updateValidateBtn.setDisable(selectedEmployee == null);
        
        try
        {
            setNameTf(selectedEmployee.getName());
            setEmailTf(selectedEmployee.getEmail());
            setAddressTf(selectedEmployee.getAddress());
            setPhoneTf(selectedEmployee.getPhone());
            setTFNTf(selectedEmployee.getTFN());
            setPaidHrsTf(selectedEmployee.getHours());
            setHourlyRateTf(selectedEmployee.getPayPerHour());
        setJobTypeTf(selectedEmployee.getType());
        }
        catch (Exception e) //we are adding employee not updating
        {
            
        }
    }
            
    @FXML private void handleValidateUpdate() throws IOException
    {
        validator.GenerateErrors(getNameTf(), getEmailTf(), getAddressTf(), getPhoneTf(), getTFNTf(), paidHrsTf.getText(), hourlyRateTf.getText(), getJobTypeTf());
     //   validator.GenerateErrors(getNameTf(), getEmailTf(), getAddressTf(), getPhoneTf(), getTFNTf(), paidHrsTf.getText(), hourlyRateTf.getText(), getJobTypeTf());
        if (validator.Errors().size() > 0)
        {
            //we have some errors!
            errorMsg.set("");
            for (String i : validator.Errors())
            {
                errorMsg.set(errorMsg.get() + i + "\n");
                System.out.println(errorMsg.get());
            } //add all the errors up
                    
            //load the error page
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            stage.setX(300);
            stage.setY(200);
            ViewLoader.showStage(model, "/view/error.fxml", "Input Exception", stage);
        }
        else //no errors, everything in correct format!
        {
            model.removeEmployee(selectedEmployee);
            model.addEmployee(new Employee(getNameTf(), getEmailTf(), getPhoneTf(), getAddressTf(), getTFNTf(), getJobTypeTf(), getPaidHrsTf(), getHourlyRateTf()));
            stage.close();
        }
    }
    
    
    @FXML private void handleValidateAdd() throws IOException
    {
        validator.GenerateErrors(getNameTf(), getEmailTf(), getAddressTf(), getPhoneTf(), getTFNTf(), paidHrsTf.getText(), hourlyRateTf.getText(), getJobTypeTf());
        if (validator.Errors().size() > 0)
        {
            //we have some errors!
            errorMsg.set("");
            for (String i : validator.Errors())
            {
                errorMsg.set(errorMsg.get() + i + "\n");
                System.out.println(errorMsg.get());
            } //add all the errors up
                    
            //load the error page
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            stage.setX(300);
            stage.setY(200);
            ViewLoader.showStage(model, "/view/error.fxml", "Input Exception", stage);
        }
        else //no errors, everything in correct format!
        {
            model.addEmployee(new Employee(getNameTf(), getEmailTf(), getPhoneTf(), getAddressTf(), getTFNTf(), getJobTypeTf(), getPaidHrsTf(), getHourlyRateTf()));
            stage.close();
        }
           // model.addEmployee(new Employee(getNameTf(), getEmailTf(), getPhoneTf(), getAddressTf(), getTFNTf(), getJobTypeTf(), getPaidHrsTf(), getHourlyRateTf()))
    }
    
    @FXML private void handleClose()
    {
        stage.close();
    }
}
