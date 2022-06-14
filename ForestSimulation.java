import java.io.*;
import java.util.*;
import java.util.Scanner;

/**
* Runs the forest fire simulation.
*
* @author Christine Reilly
*/
public class ForestSimulation {

  // PROJECT 2: you may not add instance or class variables

  /** The simulated forest */
  private Forest simForest;

  /** The grid display of the simulated forest */
  private DisplayForest simDisplay;

  /**
  * PROJECT 2: DO NOT MODIFY THIS METHOD
  *
  * Creates a ForestSimulation object with a 3x3 grid. All transition probablities
  * are set to 0.5, probability of spontaneous combustion is set to 0. The grid
  * is initialized with the middle patch burning and all other patches in the
  * high growth state.
  *
  * @param inFile The file containing the data for the starting forest.
  */
  public ForestSimulation() throws FileNotFoundException, IncorrectFormatException, WrongLengthException  {
    // Set probability of spontaneous combustion to 0
    // Set probability of catching fire from neighbor to 1
    ForestPatch.rand = new Random();
    ForestPatch.burnHot_burnMed = 0.5;
    ForestPatch.burnMed_burnMild = 0.5;
    ForestPatch.burnMild_ashes = 0.5;
    ForestPatch.ashes_growLow = 0.5;
    ForestPatch.growLow_growMed = 0.5;
    ForestPatch.growMed_growHigh = 0.5;
    ForestPatch.growHigh_burnHot_spon = 0;
    ForestPatch.growHigh_burnHot_neighbor = 0.5;


    ForestPatch[][] myForestArray = new ForestPatch[3][3]; // Array to pass to Forest constructor

    // Set corner patches to hot burn
    // Set other patches to high growth
    myForestArray[0][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[0][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[0][2] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[1][0] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[1][1] = new ForestPatch(ForestState.BURN_HOT); // middle
    myForestArray[1][2] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][0] = new ForestPatch(ForestState.GROW_HIGH); // corner
    myForestArray[2][1] = new ForestPatch(ForestState.GROW_HIGH);
    myForestArray[2][2] = new ForestPatch(ForestState.GROW_HIGH); // corner

    simForest = new Forest(myForestArray);

    simDisplay = new DisplayForest(simForest);

  } // end of default constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Creates a ForestSimulation object using data from a file as the starting forest.
  *
  * @param inFile The file containing the data for the starting forest.
  */
  public ForestSimulation(String inFile) throws FileNotFoundException, IncorrectFormatException, WrongLengthException{
    try{
      this.simForest = new Forest(ForestIO.dataCollect(inFile));
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Sorry error with file");

    }


    this.simDisplay = new DisplayForest(this.simForest);


}





    // Project 2: write this method
   // end of parameterized constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Runs the forest fire simulation for the specified number of steps then saves
  * the ending state to the output file and closes the display window.
  *
  * @param steps The number of steps of the simulation
  */
  public void runSimulation(String steps, String outFile) throws FileNotFoundException, IncorrectFormatException, WrongLengthException {
    int stepz = Integer.parseInt(steps);
    for (int i = 0; i <= stepz; i++){
      simForest.runOneStep();
      this.simDisplay.update();
    }

    try{
      ForestIO.writeto(simForest,outFile);
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Sorry error with file");
    }



  } // end of runSimulation method

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * main method starts the simulation. Command line arguments: the input
  * file pathname, the output file pathname, and the number of simulation steps.
  *
  * @param args The pathnames for the input and output files
  */
  public static void main(String[] args) throws FileNotFoundException, IncorrectFormatException, WrongLengthException {

    ForestSimulation test = new ForestSimulation(args[0]);
    test.runSimulation(args[1],args[0]);

    // Project 2: write this method
  } // end of main method

} // end of ForestSimulation class
