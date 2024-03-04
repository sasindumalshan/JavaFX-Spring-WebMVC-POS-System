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
import lk.interleon.pos.model.CategoryModel;
import lk.interleon.pos.model.UnitModel;
import lk.interleon.pos.tm.CategoryTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/4/2024
 */

public class CategoryFromController implements Initializable {
    private static CategoryFromController controller;
    public TableView tblCategory;
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
    ObservableList<CategoryTM> list = FXCollections.observableArrayList();

    public CategoryFromController() {
        controller = this;
    }

    public static CategoryFromController getController() {
        return controller;
    }

    public void categoryFromTblOnClick(MouseEvent mouseEvent) {
        btnText.setText("UPDATE");
        CategoryTM selectedItem = (CategoryTM) tblCategory.getSelectionModel().getSelectedItem();
        String id = selectedItem.getId();
        setDataTextFilLd(id);
    }

    private void setDataTextFilLd(String id) {
        CategoryDTO dto = CategoryModel.findById(id);
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtCode.setText(dto.getCode());
        cmbStates.setValue(dto.getStatus());

    }

    public void categoryFromAddOnAction(ActionEvent actionEvent) {
        if (btnText.getText().equals("NEW")) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    nextId(),
                    txtCode.getText(),
                    txtName.getText(),
                    (String) cmbStates.getValue()
            );
            boolean isSave = CategoryModel.add(categoryDTO);
            if (isSave) {
                loadAll();
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }

        } else if (btnText.getText().equals("UPDATE")) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    txtId.getText(),
                    txtCode.getText(),
                    txtName.getText(),
                    (String) cmbStates.getValue()


            );
            boolean isUpdate = CategoryModel.update(categoryDTO);
            if (isUpdate) {
                loadAll();
                txtId.setText("");
                txtName.clear();
                txtCode.clear();
                cmbStates.getItems().clear();
                btnText.setText("NEW");
                new Alert(Alert.AlertType.CONFIRMATION, "ok").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "error").show();
            }
        }
    }

    public void categoryFromClearOnAction(ActionEvent actionEvent) {
    }

    public void categoryFromSearchOnKeyReleased(KeyEvent keyEvent) {
        List<CategoryDTO> all=  CategoryModel.findCategoryByText(txtSearchText.getText());
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
        tblCategory.setItems(list);
        loadAll();
        setCMBData();
        txtCategoryCount.setText(CategoryModel.getCategoryCount());
        txtUnitCount.setText(UnitModel.getCount());

    }

    private void setCMBData() {
        cmbStates.getItems().add("ACTIVE");
        cmbStates.getItems().add("INACTIVE");
    }

    public void loadAll() {
        setTableData(CategoryModel.findAll());
    }

    private void setTableData(List<CategoryDTO> all) {
        tblCategory.getItems().clear();
        if (all!=null) {
            List<CategoryTM> list = new ArrayList<>();
            for (CategoryDTO dto : all) {
                list.add(new CategoryTM(
                        dto.getId(),
                        dto.getCode(),
                        dto.getName(),
                        dto.getStatus(),
                        null
                ));
            }
            tblCategory.getItems().addAll(list);
        }
    }
    public String nextId()  {
        List<CategoryDTO> ids = CategoryModel.findAll();
        String oldId = null;
        for (CategoryDTO dto : ids) {
            oldId = dto.getId();
        }
        int lastIndex;
        try {
            String[] split = oldId.split("C00");
            lastIndex = Integer.parseInt(split[1]);
        } catch (NullPointerException nullPointerException) {
            return "C001";
        }
        lastIndex++;
        return "C00" + lastIndex;
    }

}
