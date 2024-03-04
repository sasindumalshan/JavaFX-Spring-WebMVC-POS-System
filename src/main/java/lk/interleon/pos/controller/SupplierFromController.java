package lk.interleon.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.interleon.pos.dto.SupplerDTO;
import lk.interleon.pos.model.SupplierModel;
import lk.interleon.pos.tm.SupplierTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/3/2024
 */

public class SupplierFromController implements Initializable {
    private static SupplierFromController supplierFromController;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCode;
    public TableColumn colStatus;
    public TableColumn colAction;
    public JFXTextField txtName;
    public JFXTextField txtCode;
    public Text txtId;
    public JFXTextField txtAddress;
    public JFXComboBox cmbStates;
    public JFXButton btnClear;
    public Text btnText;
    public JFXTextField txtSearchText;
    public Text txtSupplerCount;
    public Text txtSupplierCount;
    ObservableList<SupplierTM> list = FXCollections.observableArrayList();

    public SupplierFromController() {
        supplierFromController = this;
    }

    public static SupplierFromController controller() {
        return supplierFromController;
    }

    public void supplerFromTblOnClick(MouseEvent mouseEvent) {
        btnText.setText("UPDATE");
        SupplierTM customerTm = (SupplierTM) tblCustomer.getSelectionModel().getSelectedItem();
        String id = customerTm.getId();
        setDataTextFilLd(id);
    }

    private void setDataTextFilLd(String id) {
        SupplerDTO dto = SupplierModel.findById(id);
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtCode.setText(dto.getCode());
        cmbStates.setValue(dto.getStatus());

    }

    public void supplerFromAddOnAction(ActionEvent actionEvent) {
        if (btnText.getText().equals("NEW")) {
            SupplerDTO supplerDTO = new SupplerDTO(
                    nextId(),
                    txtCode.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    (String) cmbStates.getValue()
            );
            boolean isSave = SupplierModel.add(supplerDTO);
            if (isSave) {
                loadAll();
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }

        } else if (btnText.getText().equals("UPDATE")) {
            SupplerDTO supplerDTO = new SupplerDTO(
                    txtId.getText(),
                    txtCode.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    (String) cmbStates.getValue()


            );
            boolean isUpdate = SupplierModel.update(supplerDTO);
            if (isUpdate) {
                loadAll();
                txtId.setText("");
                txtName.clear();
                txtAddress.clear();
                txtCode.clear();
                cmbStates.getItems().clear();
                btnText.setText("NEW");
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }
        }
    }

    public void supplerFromClearOnAction(ActionEvent actionEvent) {
    }

    public void supplerFromSearchOnKeyReleased(KeyEvent keyEvent) {
        List<SupplerDTO> all=  SupplierModel.findSupplierByText(txtSearchText.getText());
        setTableData(all);
    }

    public void statusOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("supplier_code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        tblCustomer.setItems(list);
        loadAll();
        setCMBData();
        txtSupplierCount.setText(SupplierModel.count());

    }

    private void setCMBData() {
        cmbStates.getItems().add("ACTIVE");
        cmbStates.getItems().add("INACTIVE");
    }

    public void loadAll() {
        setTableData(SupplierModel.findAll());
    }

    private void setTableData(List<SupplerDTO> all) {
        tblCustomer.getItems().clear();
        List<SupplierTM> list = new ArrayList<>();
        for (SupplerDTO dto : all) {
            list.add(new SupplierTM(
                    dto.getId(),
                    dto.getCode(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getStatus(),
                    null
            ));
        }
        tblCustomer.getItems().addAll(list);
    }
    public String nextId()  {
        List<SupplerDTO> ids = SupplierModel.findAll();
        String oldId = null;
        for (SupplerDTO dto : ids) {
            oldId = dto.getId();
        }
        int lastIndex;
        try {
            String[] split = oldId.split("S00");
            lastIndex = Integer.parseInt(split[1]);
        } catch (NullPointerException nullPointerException) {
            return "S001";
        }
        lastIndex++;
        return "S00" + lastIndex;
    }
}
