package lk.interleon.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import lk.interleon.pos.util.NavigationUtility;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/3/2024
 */

public class DashboardController implements Initializable {
    public Pane pane;

    public void supplierPageViewOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(pane,"SupplierFrom.fxml");
    }

    public void homePageViewOnAction(ActionEvent actionEvent) {
        pane.getChildren().clear();

    }

    public void UnitPageViewOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(pane,"UnitFrom.fxml");
    }

    public void CategoryPageViewOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(pane,"CategoryFrom.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavigationUtility.onTheTopNavigation(pane,"SupplierFrom.fxml");
    }

    public void onKeyReleased(KeyEvent keyEvent) {

    }
}
