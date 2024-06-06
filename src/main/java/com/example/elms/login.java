package com.example.elms;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;


public class login implements Initializable {
    @FXML
    private Button loginBtn;
    @FXML
    private Button close;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink register;

    @FXML
    private ChoiceBox<String> roleChoice;

    @FXML
    private Label wrongLogIn;
//
    @FXML
    private TextField username;

    private String[] role = {"admin", "employee"};


    private String chooseRole;
//    public void close(){
//        System.exit(1);
//    }
//
//    public void userLogin(ActionEvent event) throws IOException{
//        checkLogin();
//    }
//
//    @FXML
//    void register(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
//        Stage stage = new Stage();
//        stage.setFullScreen(false);
//        Scene scene = new Scene(root);
//
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void checkLogin() throws IOException{
//        ElmsApplication m = new ElmsApplication();
//
//        if(username.getText().toString().equals("aditya") && password.getText().toString().equals("12345")){
//            wrongLogIn.setText("Success!");
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//
//            stage.initStyle(StageStyle.TRANSPARENT);
//            stage.setScene(scene);
//            stage.show();
//            //  m.changeScene("dashboardEmployee.fxml");
//        }
//
//
//        else if(username.getText().isEmpty() || password.getText().isEmpty()){
//            wrongLogIn.setText("Please Enter your data.");
//        }
//
//        else {
//            wrongLogIn.setText("Wrong Username or password");
//        }
//    }
//
//    public void initialize(URL url, ResourceBundle rb){
//
//    }
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;
     public static String empLabel;
    public void loginAdmin(){

        String sql = "SELECT * FROM employee_details WHERE username = ? and password = ?;";

        connect = Database.connectDb();


        try{
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty() || roleChoice.getSelectionModel().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("All fields are mandatory to fill!!");
                alert.showAndWait();
            }else{
                if(result.next()) {
                    getData.username = username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    loginBtn.getScene().getWindow().hide();

                    chooseRole = roleChoice.getValue();
                    Parent root;
                    if (chooseRole == "employee") {
                         root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                    }
                    else{
                         root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminDashboard.fxml")));
                    }
                    Stage stage = new Stage();

                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneY();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleChoice.getItems().addAll(role);
        roleChoice.setValue("Role");
    }

    public void register(ActionEvent event) {
    }
}