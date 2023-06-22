package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController {

    public TextField txtName;
    public PasswordField txtPassword;
    public AnchorPane pane;
    static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        if (Pattern.matches("^[a-zA-Z\\s]+", txtName.getText())) {
            userName=txtName.getText();
            Stage window =(Stage) pane.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ClientChatController.fxml"))));
            window.centerOnScreen();
            window.setTitle(userName + "'s ChatRoom");
        }else {
            new Alert(Alert.AlertType.ERROR, "Enter your UserName!").show();
        }

    }
}
