package com.example.elms;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboard implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button dashboardBtn;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Button employeeListBtn;

    @FXML
    private AnchorPane employeeListPane;

    @FXML
    private AnchorPane leaveBalancePane;

    @FXML
    private Button leaveBalanceBtn;

    @FXML
    private AnchorPane leaveHistoryPane;

    @FXML
    private Button leaveHistoryBtn;

    @FXML
    private Button minimize;

    @FXML
    private Button close;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<LeaveTable> adminTable;

    @FXML
    private TableColumn<LeaveTable, String> empDate;

    @FXML
    private TableColumn<LeaveTable, String> empDepartment;

    @FXML
    private TableColumn<LeaveTable, String> empLeaveType;

    @FXML
    private TableColumn<LeaveTable, String> empName;

    @FXML
    private TableColumn<LeaveTable, String> empReason;

    @FXML
    private TableColumn<LeaveTable, String> empTime;


    ObservableList<LeaveTable> listA;

    public void pendingRequests(){
        empName.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empName"));
        empDepartment.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empDepartment"));
        empReason.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empReason"));
        empLeaveType.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empLeaveType"));
        empDate.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empDate"));
        empTime.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("empTime"));

        listA = Database.getEmpLeave();
        adminTable.setItems(listA);
    }

    @FXML
    void closeBtn(ActionEvent event) {
    System.exit(1);
    }

    @FXML
    void minimizeBtn(ActionEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void balanceClick(ActionEvent event) {
        leaveBalanceBtn.setOnAction(event1 -> {
            dashboardPane.setVisible(false);
            leaveHistoryPane.setVisible(false);
            leaveBalancePane.setVisible(true);
            employeeListPane.setVisible(false);
        });
    }

    @FXML
    void dashboardClick(ActionEvent event) {
    dashboardBtn.setOnAction(event1 -> {
        dashboardPane.setVisible(true);
        employeeListPane.setVisible(false);
        leaveHistoryPane.setVisible(false);
        leaveBalancePane.setVisible(false);
    });
    }

    @FXML
    void employeeClick(ActionEvent event) {
    employeeListBtn.setOnAction(event1 -> {
        dashboardPane.setVisible(false);
        employeeListPane.setVisible(true);
        leaveHistoryPane.setVisible(false);
        leaveBalancePane.setVisible(false);
    });
    }

    @FXML
    void historyClick(ActionEvent event) {
    leaveHistoryBtn.setOnAction(event1 -> {
        dashboardPane.setVisible(false);
        leaveHistoryPane.setVisible(true);
        employeeListPane.setVisible(false);
        leaveBalancePane.setVisible(false);
    });
    }

    @FXML
    void logout(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pendingRequests();

    }

}
