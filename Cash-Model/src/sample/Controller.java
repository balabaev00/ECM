package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    Memory memory;
    Cash cash;
    int[][] storage;
    int[] tag;

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

    @FXML
    private Label labelFound;

    public void createTagArray(int[] tag) {
        for (int i = 0; i < tag.length ; i++) {
            tag[i]=-1;
        }
    }

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
        long startTime = System.nanoTime();
        cash = new Cash(Integer.parseInt(strOne.getText()),Integer.parseInt(strkOne.getText()),Integer.parseInt(elemOne.getText()));
        memory.randomArray(cash.getArray());
        memory.writeArray(cash.getArray());
        long endTime = System.nanoTime();
        long timeSpent = endTime - startTime;
        String temp = String.valueOf(timeSpent/1000);
        labelTime.setText(temp + " мс");
        areaMemory.setText(memory.readArray());
        storage = new int[cash.getArray()[0].length][cash.getArray()[0][0].length];
        tag = new int[cash.getArray()[0].length];
        createTagArray(tag);
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
        long startTime = System.nanoTime();
        int page = Integer.parseInt(strTwo.getText());
        int n = Integer.parseInt(strkTwo.getText());
        int m = Integer.parseInt(elemTwo.getText());
        String text = "";
        String str = "";
        String element = "";

        // Смотрим откуда считывать
        if(storage[n][m]==cash.getArrayValue(page,n,m)) {
            labelFound.setText("Из - Кэша");
            element=String.valueOf(storage[n][m]);
            for (int j = 0; j < storage[n].length; j++) {
                str += String.valueOf(storage[n][j])  + " ";
            }
        }
        else {
            labelFound.setText("Из - ОП");
            element=String.valueOf(cash.getArrayValue(page,n,m));
            for (int j = 0; j < cash.getArray()[page][n].length; j++) {
                str += String.valueOf(cash.getArrayValue(page,n,j))  + " ";
            }
            for (int j = 0; j < storage[n].length; j++) {
                storage[n][j]=cash.getArrayValue(page,n,j);
                }
            for (int j = 0; j < storage.length; j++) {
                if(j==n) {
                    tag[j]=page;
                }
            }
            }

        // Итоговый вывод текста
        for (int i = 0; i < storage.length; i++) {
            text += tag[i] + "      ";
            for (int j = 0; j < storage[i].length; j++) {
                text+=storage[i][j]+"   ";
            }
            text+="\n";
        }

        areaCash.setText(text);
        labelStr.setText(str);
        labelElement.setText(element);
        long endTime = System.nanoTime();
        long timeSpent = endTime - startTime;
        String temp = String.valueOf(timeSpent/1000);
        labelTime.setText(temp + " мс");
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
