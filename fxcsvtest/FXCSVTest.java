/*
Java AT2.6 - Read and Write data CSV file
 */
package fxcsvtest;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import com.opencsv.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class FXCSVTest extends Application {

    Stage window;
    TableView<DataStore> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window = primaryStage;
        window.setTitle("CSV Data Read And Write");

        TableColumn<DataStore, Integer> quickC = new TableColumn("Quick500K");
        quickC.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DataStore, Integer> combC = new TableColumn("Comb500K");
        combC.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DataStore, Integer> mergeC = new TableColumn("Merge500K");
        mergeC.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        // Create table view and add columns created above
        tableView = new TableView<>();
        tableView.getColumns().addAll(quickC, combC, mergeC);

        // Button to open file directory and read the data
        Button loadBtn = new Button("Open File");
        loadBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(window);
            if (file != null) {
                tableView.setItems(readFromCSV(file));
            }
        });
        // Button to save/write data to file in project folder
        Button saveBtn = new Button("Save File");
        saveBtn.setOnAction(e -> {
            ObservableList<DataStore> results;
            results = tableView.getItems();
            writeToCSV(results);
        });
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(100);
        hBox.getChildren().addAll(loadBtn, saveBtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView, hBox);
        vBox.setStyle("-fx-padding: 10;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: red;");
        
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    // Using third party library, read data from file - Book1.csv supplied in project folder to match table
    public static ObservableList<DataStore> readFromCSV(File fileName) {

        ObservableList<DataStore> dataList = FXCollections.observableArrayList();
        CSVReader csvReader;

        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] nextColumn;
            DataStore nextDataStore;
            while ((nextColumn = csvReader.readNext()) != null) {
                nextDataStore = new DataStore();
                nextDataStore.setName(nextColumn[0]);
                nextDataStore.setName(nextColumn[1]); 
                nextDataStore.setName(nextColumn[2]); 
                dataList.add(nextDataStore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    // Using third party library, write data to new file Book2.csv
    public static void writeToCSV(ObservableList<DataStore> results) {

        CSVWriter writer;
        File file = new File("Book2.csv");

        try {
            writer = new CSVWriter(new FileWriter(file));
            String[] columns = {"Quick500K", "Comb500K", "Merge500K"};
            writer.writeNext(columns);
            for (DataStore result : results) {
                String[] nextResult = {result.getName(), result.getName(), result.getName()};
                writer.writeNext(nextResult);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
