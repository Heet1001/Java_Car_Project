import java.io.File;
import java.util.Scanner;
import javax.swing.JPanel;
import guipackage.*;
import travelpackage.*;


public class TravelMain {
    private static void readFileData(String filename, MainFrame mainFrame, JPanel nextPanel) {
        try {
            Scanner inFile = new Scanner(new File(filename));
            while(inFile.hasNextLine()) {
                String[] stateData = inFile.nextLine().split(",");
                new State(stateData[0], Integer.parseInt(stateData[1]), mainFrame, nextPanel);
            }
        } catch (Exception e) {
            System.out.println("Error opening file...");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Please enter a filename to run with this program.");
            System.exit(0);
        }
        MainFrame mainFrame = new MainFrame("Travel Cost Estimator");
        TravelModePanel selectTravelScreen = new TravelModePanel();
        Group group = new Group("./Pictures/Travel.png", mainFrame, State.getJPanel());
        readFileData(args[0], mainFrame, selectTravelScreen);
        
        mainFrame.initializeFrame(group.getJPanel());

        JPanel infoPanel = new JPanel();
        while(!(infoPanel instanceof Airport || infoPanel instanceof Garage))
        {
            System.out.print(""); // Enables program to function
            if(selectTravelScreen.getTravelMode().equals("By Air")) {
                infoPanel = new Airport(State.getSelectedState().getDistance());
            } else if(selectTravelScreen.getTravelMode().equals("By Road")) {
                infoPanel = group.initializeAndGetGarage(State.getSelectedState().getDistance());
            }
        }
        mainFrame.resetFrame(infoPanel);

        JPanel lastPanel = new JPanel();
        if(infoPanel instanceof Airport){
            while(((Airport)infoPanel).getSelectedPlane() == null) {
                System.out.print(""); // Enables program to function
                if(((Airport)infoPanel).getSelectedPlane() != null) {
                    lastPanel = new FinalPanel(State.getSelectedState(), group.getGroupSize(), group.checkWallet(), infoPanel, mainFrame);
                }
            }
        }
        else {
            while(!((Garage)infoPanel).isFinished()) {
                System.out.print(""); // Enables program to function
                if(((Garage)infoPanel).isFinished()) {
                    lastPanel = new FinalPanel(State.getSelectedState(), group.getGroupSize(), group.checkWallet(), infoPanel, mainFrame);
                }
            }
        }
        mainFrame.resetFrame(lastPanel);
    }
}
