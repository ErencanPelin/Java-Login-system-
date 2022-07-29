package controller;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController extends Controller<Session>
{
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    
    private String getEmail() { return emailField.getText(); }
    private void setEmail(String text) { emailField.setText(text); }
    
    private String getPassword() { return passwordField.getText(); }
    private void setPassword(String text) { passwordField.setText(text); }
    
    @FXML private void handleCancel()
    {
       stage.close();
    }
    
    @FXML private void handleSubmission() throws IOException
    {
        model.Employer employer = model.getEmployer(getEmail(), getPassword());
        model.setEmployer(employer);
        
        if (employer != null)
        {
            //if valid
            //load next window
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("/view/employer.png"));
            newStage.setX(0);
            newStage.setY(530);
            ViewLoader.showStage(employer, "/view/employer.fxml", "Session Admin: " + employer.getName(), newStage);
            
            
            stage.close();
        }
        else
        {
            //if no valid
            //clear fields
            setEmail("");
            setPassword("");
            errorLabel.setVisible(true);            
        }
    }
}
