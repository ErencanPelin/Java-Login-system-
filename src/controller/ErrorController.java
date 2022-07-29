package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import model.Employer;

public class ErrorController extends Controller<Employer> 
{
    public static final StringProperty errorMsg = new SimpleStringProperty();
    public String getError() { return errorMsg.getValue(); }
    
    @FXML private void handleClose()
    {
        stage.close();
    }
}
