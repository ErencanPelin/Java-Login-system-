package controller;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SessionController extends Controller<Session>
{
    @FXML private void handleExit()
    {
       // stage.close();  to close windows one by one
        Platform.exit(); //closes all windows
    }
        
    @FXML private void handleLogin(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/book.png"));
        stage.setX(100);
        stage.setY(200);
        ViewLoader.showStage(model, "/view/login.fxml", "Sign In", stage);
    }
    
    public final Session getSession() {return model; }
}
