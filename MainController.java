// The size should be 400 x 600

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {
    @FXML
    ImageView logo;
    Button pageTwo;
    Button pageThree;
    Button createButton;
    Button loginButton;

    // FOR LOG IN
    @FXML
    private Label titleLabel;
    @FXML
    private TextField nameTextField; // for name Log IN
    @FXML
    private TextField passwordTextField; // for Password

    // FOR CREATE ACCOUNT
    @FXML
    private TextField newNameField; // for new name acc
    @FXML
    private TextField newPassField; // for new pass acc
    @FXML
    private TextField newConfirmField; // for new pass confirm

    private Connection connection;
    private Stage mainStage;
    private Scene mainScene;
    private Parent root;

    @FXML
    private void initialize() {
        try {
            String url = "jdbc:mysql://localhost:8181/Usernm";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = nameTextField.getText();
        String password = passwordTextField.getText();

        try {
            String query = "SELECT * FROM User WHERE Usernm = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Login successful, switch to the next scene
                switchToLogin(event);
            } else {
                // Invalid credentials, show an alert
                showAlert("Login Failed", "Incorrect username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void swithTofirstController(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("firstControllerScene.fxml"));
            mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            String css = this.getClass().getResource("Main.css").toExternalForm();
            mainScene.getStylesheets().add(css);
            mainStage.setScene(mainScene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void swithTosecondController(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("secondControllerScene.fxml"));
            mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            String css = this.getClass().getResource("Main.css").toExternalForm();
            mainScene.getStylesheets().add(css);
            mainStage.setScene(mainScene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            String css = this.getClass().getResource("Main.css").toExternalForm();
            mainScene.getStylesheets().add(css);
            mainStage.setScene(mainScene);
            mainStage.show();

            // Database operations for creating a user account
            String url = "jdbc:mysql://localhost:8181/Usernm";
            String dbUser = "root";
            String dbPassword = "";

            String insertQuery = "INSERT INTO User (Usernm, Password) VALUES (?, ?)";

            // Get the input values from the text fields in your CreateAccount.fxml
            TextField newNameField = (TextField) root.lookup("#newNameField");
            TextField newPassField = (TextField) root.lookup("#newPassField");
            TextField newConfirmField = (TextField) root.lookup("#newConfirmField");

            String username = newNameField.getText();
            String password = newPassField.getText();
            String confirmPassword = newConfirmField.getText();

            if (password.equals(confirmPassword)) {
                try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("User account created successfully.");
                    } else {
                        System.out.println("Failed to create user account.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Passwords do not match.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while creating the account.");
            alert.showAndWait();
        }
    }

    public void switchToLogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            String css = this.getClass().getResource("Main.css").toExternalForm();
            mainScene.getStylesheets().add(css);
            mainStage.setScene(mainScene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNext(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("nextSubs.fxml"));
            mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
            String css = this.getClass().getResource("Main.css").toExternalForm();
            mainScene.getStylesheets().add(css);
            mainStage.setScene(mainScene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
