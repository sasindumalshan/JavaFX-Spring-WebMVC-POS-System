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
import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.SupplerDTO;
import lk.interleon.pos.dto.UnitDTO;
import lk.interleon.pos.model.CategoryModel;
import lk.interleon.pos.model.SupplierModel;
import lk.interleon.pos.model.UnitModel;
import lk.interleon.pos.tm.UnitTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/4/2024
 */

public class UnitFromController implements Initializable {
    private static UnitFromController controller;
    public TableView tblUnit;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCode;
    public TableColumn colStatus;
    public TableColumn colAction;
    public JFXTextField txtName;
    public JFXTextField txtCode;
    public Text txtId;
    public JFXComboBox cmbStates;
    public JFXButton btnClear;
    public Text btnText;
    public JFXTextField txtSearchText;
    public Text txtCategoryCount;
    public Text txtUnitCount;
    public Text txtSupplier;
    public Text txtCategory;
    public JFXComboBox cmbSupplierID;
    public JFXComboBox cmbCategoryID;
    ObservableList<UnitTM> list = FXCollections.observableArrayList();

    public UnitFromController() {
        controller = this;
    }

    public static UnitFromController getController() {
        return controller;
    }

    public void UnitFromTblOnClick(MouseEvent mouseEvent) {
        btnText.setText("UPDATE");
        UnitTM selectedItem = (UnitTM) tblUnit.getSelectionModel().getSelectedItem();
        String id = selectedItem.getId();
        setDataTextFilLd(id);
        UnitDTO unitDTO = UnitModel.findById(selectedItem.getId());

        txtId.setText(unitDTO.getId());
        txtName.setText(unitDTO.getName());
        txtCode.setText(unitDTO.getCode());
        cmbStates.setValue(unitDTO.getStatus());
        cmbSupplierID.setValue(unitDTO.getSupplierID());
        cmbCategoryID.setValue(unitDTO.getCategoryID());
    }

    private void setDataTextFilLd(String id) {
        UnitDTO dto = UnitModel.findById(id);
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtCode.setText(dto.getCode());
        cmbStates.setValue(dto.getStatus());

    }

    public void unitFromAddOnAction(ActionEvent actionEvent) {
        if (btnText.getText().equals("NEW")) {
            UnitDTO unitDTO = new UnitDTO(
                    nextId(),
                    txtCode.getText(),
                    txtName.getText(),
                    (String) cmbStates.getValue(),
                    (String) cmbSupplierID.getValue(),
                    (String) cmbCategoryID.getValue()
            );
            boolean isSave = UnitModel.add(unitDTO);
            if (isSave) {
                loadAll();
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }

        } else if (btnText.getText().equals("UPDATE")) {
            UnitDTO unitDTO = new UnitDTO(
                    txtId.getText(),
                    txtCode.getText(),
                    txtName.getText(),
                    (String) cmbStates.getValue(),
                    (String) cmbSupplierID.getValue(),
                    (String) cmbCategoryID.getValue()

            );
            boolean isUpdate = UnitModel.update(unitDTO);
            if (isUpdate) {
                loadAll();
                txtId.setText("");
                txtName.clear();
                txtCode.clear();
                cmbStates.getItems().clear();
                cmbCategoryID.getItems().clear();
                cmbSupplierID.getItems().clear();
                btnText.setText("NEW");
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }
        }
    }

    public void unitFromClearOnAction(ActionEvent actionEvent) {
    }

    public void unitFromSearchOnKeyReleased(KeyEvent keyEvent) {
        List<UnitDTO> all = UnitModel.findUnitByText(txtSearchText.getText());
        setTableData(all);
    }

    public void statusOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        tblUnit.setItems(list);
        loadAll();
        setCMBData();
        txtCategoryCount.setText(CategoryModel.getCategoryCount());
        txtUnitCount.setText(UnitModel.getCount());
    }

    private void setCMBData() {
        cmbStates.getItems().add("ACTIVE");
        cmbStates.getItems().add("INACTIVE");

        List<CategoryDTO> all = CategoryModel.findAll();
        for (CategoryDTO dto : all) {
            cmbCategoryID.getItems().add(dto.getId());
        }

        List<SupplerDTO> supplerDTOS = SupplierModel.findAll();
        for (SupplerDTO dto : supplerDTOS) {
            cmbSupplierID.getItems().add(dto.getId());
        }
    }

    public void loadAll() {
        setTableData(UnitModel.findAll());
    }

    private void setTableData(List<UnitDTO> all) {
        tblUnit.getItems().clear();
        if (all != null) {
            List<UnitTM> list = new ArrayList<>();
            for (UnitDTO dto : all) {
                list.add(new UnitTM(
                        dto.getId(),
                        dto.getCode(),
                        dto.getName(),
                        dto.getStatus(),
                        null
                ));
            }
            tblUnit.getItems().addAll(list);
        }
    }

    public void supplierOnAction(ActionEvent actionEvent) {
        SupplerDTO dto = SupplierModel.findById((String) cmbSupplierID.getValue());
        txtSupplier.setText(dto.getName());
    }

    public void categoryOnAction(ActionEvent actionEvent) {
        CategoryDTO dto = CategoryModel.findById((String) cmbCategoryID.getValue());
        txtCategory.setText(dto.getName());
    }

    public String nextId() {
        List<UnitDTO> ids = UnitModel.findAll();
        int lastIndex;
        try {
            String oldId = null;
            for (UnitDTO dto : ids) {
                oldId = dto.getId();
            }
            String[] split = oldId.split("U00");
            lastIndex = Integer.parseInt(split[1]);
        } catch (NullPointerException nullPointerException) {
            return "U001";
        }
        lastIndex++;
        return "U00" + lastIndex;
    }
}
