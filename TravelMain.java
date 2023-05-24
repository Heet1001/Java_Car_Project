import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import TravelClasses.*;
import javax.swing.*;
import java.awt.*;

public class TravelMain {
    
    public static void main(String[] args) {
        //System.out.println("Where would you like to go?");
        try{
            Scanner inFile = new Scanner(new File(args[0]));
            HashMap<State, String> allStates = new HashMap<State, String>();
            inFile.nextLine();
            while(inFile.hasNextLine()){
                String[] stateInfo = inFile.nextLine().split(",");
                State newState = new State(stateInfo[0], stateInfo[1], Integer.parseInt(stateInfo[2]), Float.parseFloat(stateInfo[3]));
                allStates.put(newState, stateInfo[0]);
            }
        }
        catch(Exception e){
            System.out.println("State information needs to be passed.");
        }
        
    }
}