package main.java.com.pc_registration.controller;

// Ensure JavaFX libraries are accessible


import com.pc_registration.dao.UserDAO;
import com.pc_registration.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = UserDAO.login(username, password);
        if (user.getRole().equals("ADMIN")) {
            // Redirect to admin dashboard
        } else if (user.getRole().equals("USER")) {
            // Redirect to user dashboard
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
