package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    Memory memory;
    Cash cash;

    @FXML
    private TextArea areaMemory;

    @FXML
    private TextArea areaCash;

    @FXML
    private TextField strOne;

    @FXML
    private TextField strkOne;

    @FXML
    private TextField elemOne;

    @FXML
    private TextField value;

    @FXML
    private Button btnRead;

    @FXML
    private Button btnCreate;

    @FXML
    private TextField strTwo;

    @FXML
    private TextField strkTwo;

    @FXML
    private TextField elemTwo;

    @FXML
    private Button btnWrite;

    @FXML
    private Label labelTime;

    @FXML
    private Label labelElement;

    @FXML
    private Label labelStr;

    public void warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Введите корректные данные!");
        alert.showAndWait();
    }

    @FXML
    public void createMemory() {
        if(strkOne.getText().equals("") || strOne.getText().equals("") || elemOne.getText().equals("")) {
            warning();
            return;
        }
        cash = new Cash(Integer.parseInt(strOne.getText()),Integer.parseInt(strkOne.getText()),Integer.parseInt(elemOne.getText()));
        memory.randomArray(cash.getArray());
        memory.writeArray(cash.getArray());
        areaMemory.setText(memory.readArray());
    }

    @FXML
    public void readMemory() {
        if(strkTwo.getText().equals("") || strTwo.getText().equals("") || elemTwo.getText().equals("")) {
            warning();
            return;
        }
        if(areaMemory.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Создайте ОП");
            alert.showAndWait();
            return;
        }
        String text = "";
        String str = "";
        String element = "";
        int page = Integer.parseInt(strTwo.getText());
        int n = Integer.parseInt(strkTwo.getText());
        int m = Integer.parseInt(elemTwo.getText());
        for (int i = 0; i < cash.getArray()[page].length; i++) {
            for (int j = 0; j < cash.getArray()[page][i].length; j++) {
                text += String.valueOf(cash.getArrayValue(page,i,j)) + " ";
                if(i==n) {
                    str+=String.valueOf(cash.getArrayValue(page,i,j)) + " ";
                    if(j==m) {
                        element+=String.valueOf(cash.getArrayValue(page,i,j));
                    }
                }
            }
            text+="\n";
        }
        areaCash.setText(text);
        labelStr.setText(str);
        labelElement.setText(element);
    }

    @FXML
    public void changeCashValue() {
        if(strkTwo.getText().equals("") || strTwo.getText().equals("") || elemTwo.getText().equals("")) {
            warning();
            return;
        }
        if(value.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите новое значение!");
            alert.showAndWait();
            return;
        }
        int page = Integer.parseInt(strTwo.getText());
        int n = Integer.parseInt(strkTwo.getText());
        int m = Integer.parseInt(elemTwo.getText());
        long startTime = System.nanoTime();
        cash.setArrayValue(Integer.parseInt(value.getText()),page,n,m);
        memory.writeArray(cash.getArray());
        memory.readArray();
        readMemory();
        long endTime = System.nanoTime();
        long timeSpent = endTime - startTime;
        String temp = String.valueOf(timeSpent/1000);
        labelTime.setText(temp + " мс");
    }



    public void initialize() {
        memory = new Memory("memory.txt");
    }



}
