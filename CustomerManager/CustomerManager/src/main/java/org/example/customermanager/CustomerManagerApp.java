package org.example.customermanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        // 1. Form Controls
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., Mary Banda");
        nameLabel.setLabelFor(nameField);

        Label provinceLabel = new Label("Province:");
        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Central", "Copperbelt", "Eastern", "Luapula",
                "Lusaka", "Muchinga", "Northern", "North-Western",
                "Southern", "Western"
        );
        provinceBox.setPromptText("Choose a province");

        Button saveButton = new Button("Save Customer");
        saveButton.setDefaultButton(true);

        Button deleteButton = new Button("Delete Selected");
        Label statusLabel = new Label();

        // 2. TableView Setup
        TableView<Customer> table = new TableView<>();
        table.setItems(customers);

        TableColumn<Customer, String> nameCol = new TableColumn<>("Customer Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));

        table.getColumns().addAll(nameCol, provinceCol);

        // 3. Save Action with Validation
        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                statusLabel.setText("Enter the customer name.");
                nameField.requestFocus();
                return;
            }

            String province = provinceBox.getValue();
            if (province == null) {
                statusLabel.setText("Choose a province.");
                provinceBox.requestFocus();
                return;
            }

            customers.add(new Customer(name, province));
            statusLabel.setText("Customer saved.");
            nameField.clear();
            provinceBox.setValue(null);
            nameField.requestFocus();
        });

        // 4. Delete Action with Confirmation Dialog
        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("Select a customer from the table to delete.");
                return;
            }

            ButtonType deleteBtnType = new ButtonType("Delete");
            Alert ask = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Delete the selected customer?",
                    deleteBtnType,
                    ButtonType.CANCEL
            );
            ask.setHeaderText("Confirm deletion");

            if (ask.showAndWait().orElse(ButtonType.CANCEL) == deleteBtnType) {
                customers.remove(selected);
                statusLabel.setText("Customer deleted.");
            }
        });

        // 5. Layout Setup
        HBox formBox = new HBox(10, nameLabel, nameField, provinceLabel, provinceBox, saveButton);
        HBox actionBox = new HBox(10, deleteButton, statusLabel);
        VBox mainLayout = new VBox(15, formBox, table, actionBox);
        mainLayout.setPadding(new Insets(15));

        Scene scene = new Scene(mainLayout, 680, 400);
        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}