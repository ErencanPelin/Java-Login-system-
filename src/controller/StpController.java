package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.text.*;
import model.Report;
import model.STP;


public class StpController extends Controller<STP>
{
    @FXML private Text totalWagesTx;
    @FXML private Text totalNetTx;
    @FXML private Text quaterBASTx;
    @FXML private Text totaTaxTx;
    @FXML private Text totalSuperTx;
    
    @FXML private TableColumn<Report, String> nameClm;
    @FXML private TableColumn<Report, String> wagesClm;
    @FXML private TableColumn<Report, String> taxClm;
    @FXML private TableColumn<Report, String> netClm;
    @FXML private TableColumn<Report, String> superClm;
    
    public final STP getStp() { return model; }
        
    @FXML private void initialize()
    {
        nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        wagesClm.setCellValueFactory(cellData -> cellData.getValue().wagesProperty().asString("$%.2f"));
        taxClm.setCellValueFactory(cellData -> cellData.getValue().taxProperty().asString("$%.2f"));
        netClm.setCellValueFactory(cellData -> cellData.getValue().netProperty().asString("$%.2f"));
        superClm.setCellValueFactory(cellData -> cellData.getValue().superannuationProperty().asString("$%.2f"));

        totalWagesTx.textProperty().bind(model.totalWagesProperty().asString("$%.2f"));
        totalNetTx.textProperty().bind(model.totalNetProperty().asString("$%.2f"));
        quaterBASTx.textProperty().bind(model.basProperty().asString("$%.2f"));
        totaTaxTx.textProperty().bind(model.totalTaxProperty().asString("$%.2f"));
        totalSuperTx.textProperty().bind(model.totalSuperProperty().asString("$%.2f"));
    }
    
    @FXML private void handleClose()
    {
        stage.close();
    }
}
