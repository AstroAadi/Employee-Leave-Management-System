package com.example.elms;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private TableColumn<LeaveTable, String> Date;

    @FXML
    private TableColumn<LeaveTable, Integer> Days;

    @FXML
    private TableColumn<LeaveTable, String> Reason;

    @FXML
    private TableColumn<LeaveTable, String> Status;

    @FXML
    private TableView<LeaveTable> Table;

    @FXML
    private TableColumn<LeaveTable, String> Time;

    @FXML
    private Button logout;

    @FXML
    private Button leaveBalance;
    @FXML
    private Button dashboardButton;
    @FXML
    private Label employeeLabel;
    @FXML
    private Button leaveRequest;

    @FXML
    private Button minimizeBtn;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane historyPane;

    @FXML
    private Button leaveHistoryBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getUsername();
        tableConn();
    }
    ObservableList<LeaveTable> listM;

    public void tableConn(){
        Reason.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("reason"));
        Days.setCellValueFactory(new PropertyValueFactory<LeaveTable, Integer>("days"));
        Time.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("time"));
        Date.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("date"));
        Status.setCellValueFactory(new PropertyValueFactory<LeaveTable, String>("status"));

        listM = Database.getDataLeave();
        Table.setItems(listM);
    }
    public void getUsername(){
        employeeLabel.setText(getData.username);
    }

    public void closeBtn(ActionEvent event) {
        System.exit(1);
    }

    public void leaveRequest(ActionEvent event) throws IOException {
        leaveRequest.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("leaveRequest.fxml")));
        Stage stage = new Stage();
        stage.setFullScreen(false);
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void minimizeBtn(ActionEvent event) {
        Stage stage = (Stage) minimizeBtn.getScene().getWindow();
        stage.setIconified(true);
    }

    public void dashboardBtn(ActionEvent event) {
        dashboardButton.setOnAction(event1 -> {
            pane1.setVisible(true);
            pane2.setVisible(false);
            historyPane.setVisible(false);
        });
    }

    public void leaveBalanceBtn(ActionEvent event) {
        leaveBalance.setOnAction(event1 -> {
            pane1.setVisible(false);
            pane2.setVisible(true);
            historyPane.setVisible(false);
        });
    }

    public void history(ActionEvent event) {
        leaveHistoryBtn.setOnAction(event1 -> {
            historyPane.setVisible(true);
            pane2.setVisible(false);
            pane1.setVisible(false);
        });
    }

    @FXML
    void logout(ActionEvent event) {
        System.exit(1);
    }

}