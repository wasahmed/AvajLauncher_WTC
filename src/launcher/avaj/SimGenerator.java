package launcher.avaj;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimGenerator {
    public void writeMessage(String message){
        try {
            FileWriter fileWriter = new FileWriter("simulation.txt", true);
            fileWriter.write(message + "\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
