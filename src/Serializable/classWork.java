package Serializable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class classWork {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File file = new File("src/Serializable/myFirstFile.txt");

        try {
            FileWriter fw = new FileWriter(file);
            String name = "Hello my name is Abdullokh";
            fw.write(name);
            fw.close();
            FileReader reader = new FileReader(file);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
