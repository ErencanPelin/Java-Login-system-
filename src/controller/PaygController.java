package controller;

import au.edu.uts.ap.javafx.Controller;
import static controller.EmployerController.selectedEmployee;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Employee;
import model.Employer;


public class PaygController extends Controller<Employer>  
{
    @FXML private Text grossIncomeTx;
    @FXML private Text netIncomeTx;
    @FXML private Text superTx;
    @FXML private Text hourlyRateTx;
    @FXML private Text taxRateTx;
    @FXML private Text deductionTx;
    @FXML private Text superRateTx;
    
    @FXML private void initialize()
    {
        grossIncomeTx.textProperty().bind(selectedEmployee.incomeProperty().asString("$%.2f"));
        netIncomeTx.textProperty().bind(selectedEmployee.netProperty().asString("$%.2f"));
        superTx.textProperty().bind(selectedEmployee.superProperty().asString("$%.2f"));
        hourlyRateTx.textProperty().bind(selectedEmployee.payPerHourProperty().asString("$%.2f"));
        deductionTx.textProperty().bind(selectedEmployee.deductionProperty().asString("$%.2f"));
        
        taxRateTx.textProperty().bind(selectedEmployee.rateProperty().multiply(100).asString("%.2f").concat("%"));
        superRateTx.textProperty().bind(selectedEmployee.superRateProperty().multiply(100).asString("%.2f").concat("%"));
    }
    
    public Employee getStaticEmployee()
    {
        return selectedEmployee;
    }
        
    @FXML private void handleClose()
    {
        stage.close();
    }  
}
