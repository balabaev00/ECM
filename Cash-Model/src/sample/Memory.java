package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Memory {

    private String fileName;

    public Memory(String fileName) {
        this.fileName = fileName;
    }

    public void randomArray(int[][][] array) {
        int min = 1000;
        int max = 8000;
        int diff = max - min;
        Random random = new Random();
        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++) {
                    array[i][j][k]= random.nextInt(diff + 1)+min;
                }
            }
        }
    }

    public void writeArray(int[][][] array) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    for (int k = 0; k < array[i][j].length; k++) {
                        writer.write(array[i][j][k] + " ");
                    }
                    writer.write("\n");
                }
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readArray() {
        String[] temp = new String[1];
        String result = new String();
        FileReader fr= null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            temp[0] = scan.nextLine();
            result+=temp[0] +"\n";
        }
        return result;
    }

}
