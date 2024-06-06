package com.example.elms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.format.DateTimeFormatter.*;

public class LeaveRequest implements Initializable {
    @FXML
    private DatePicker leavedate;

    @FXML
    private Button handleBackBtn;
    @FXML
    private ChoiceBox<String> typeOfLeaves;

    @FXML
    private Button closeBtn;

    @FXML
    private TextArea leaveReason;

    @FXML
    private DatePicker leaveEnddate;

    @FXML
    private DatePicker leaveStartdate;

    @FXML
    void close(ActionEvent event) {
        System.exit(1);
    }


    private final String[] leaves = {"Casual Leave", "Medical Leave", "Maternity Leave", "Paternity Leave", "Duty Leave", "Compensatory Leave", "Study Leave","Others"};

    public void getDate(ActionEvent event){

        LocalDate myDate = leavedate.getValue();
        String myFormatedDate = myDate.format(ofPattern("dd-MM-yyyy"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeOfLeaves.setValue("Type of Leave");
        typeOfLeaves.getItems().addAll(leaves);
    }

    public void handleBackButtonAction(ActionEvent event) {
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the previous scene
            try {
                Pane previousPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                Scene previousScene = new Scene(previousPane);

                // Set the previous scene
                stage.setScene(previousScene);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an error message)
            }
    }
    Alert alert;
    public void sendLeaveRequest(ActionEvent event) throws SQLException {
        if (leaveReason.getText().isEmpty() || typeOfLeaves.getItems().isEmpty() || leaveEnddate.getValue()==null || leaveStartdate.getValue()==null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are mandatory to fill!!");
            alert.showAndWait();
        }

        else {
            addLeaveRequest();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Leave request sent successfully");

            alert.showAndWait();

        }



    }
    int index = -1;
    static Connection conn = null;
    ResultSet rs = null;
    static PreparedStatement pst = null;

    public void addLeaveRequest() throws SQLException {
        conn = Database.connectDb();
        String empName = getData.username;
        String sql = "insert into admin_dashboard (eid, name, department, reason, typeOfLeave, date, time) values (?, ?, ?, ?, ?, ?, ? )";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);

            long millis = System.currentTimeMillis();
            java.sql.Time time = new java.sql.Time(millis);
            java.sql.Date date = new java.sql.Date(millis);
            pst.setInt(1, 101);
            pst.setString(2, empName);
            pst.setString(3,"CSE");
            pst.setString(4, leaveReason.getText());
            pst.setString(5, typeOfLeaves.getValue());
            pst.setString(6, date.toString());
            pst.setString(7,time.toString() );

            pst.execute();
            JOptionPane.showMessageDialog(null,"Leave sent");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}
}
