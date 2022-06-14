import java.io.*;
import java.util.*;
import java.awt.Color;

public class ForestIO{


  public static ForestPatch[][] dataCollect(String inFile) throws FileNotFoundException, IncorrectFormatException, WrongLengthException {
  ForestPatch[][] forestGrid;
  int linecount = 1;
  try{

    File forest = new File(inFile);
    Scanner data = new Scanner(forest);


    String line = data.nextLine();
    String[] lineArr = line.split(" ");

    if(lineArr.length != 10){
      throw new WrongLengthException("Sorry first line is missing values");
    }


    for(int i = 0; i < lineArr.length; i++){
      try{
        Double test = Double.parseDouble(lineArr[i]); //this works because we know in java you can store a int in a double but not an double in a int.
      }catch(Exception e){
        throw new IncorrectFormatException("Sorry should be numeric data");
      }
    }


    Double prob1 = Double.parseDouble(lineArr[0]);
    Double prob2 = Double.parseDouble(lineArr[1]);
    Double prob3 = Double.parseDouble(lineArr[2]);
    Double prob4 = Double.parseDouble(lineArr[3]);
    Double prob5 = Double.parseDouble(lineArr[4]);
    Double prob6 = Double.parseDouble(lineArr[5]);
    Double prob7 = Double.parseDouble(lineArr[6]);
    Double prob8 = Double.parseDouble(lineArr[7]);
    int length = Integer.parseInt(lineArr[8]);
    int width = Integer.parseInt(lineArr[9]);


    ForestPatch.burnHot_burnMed = prob1;
    ForestPatch.burnMed_burnMild = prob2;
    ForestPatch.burnMild_ashes = prob3;
    ForestPatch.ashes_growLow = prob4;
    ForestPatch.growLow_growMed = prob5;
    ForestPatch.growMed_growHigh = prob6;
    ForestPatch.growHigh_burnHot_spon = prob7;
    ForestPatch.growHigh_burnHot_neighbor = prob8;
    ForestPatch.rand = new Random(226);
    forestGrid = new ForestPatch[length][width];


    while(data.hasNext()){
      String line2 = data.nextLine();
      linecount +=1;
      String[] values = line2.split("");
      for(int i = 0; i < forestGrid.length; i++){
        for (int j = 0; j < forestGrid[i].length; j++){
          if (values[j].equals("h")){// lines below set patchs and there state
            forestGrid[i][j] = new ForestPatch(ForestState.GROW_HIGH);
          }
          else if (values[j].equals("l")){
            forestGrid[i][j] = new ForestPatch(ForestState.GROW_LOW);
          }
          else if (values[j].equals("m")){
            forestGrid[i][j] = new ForestPatch(ForestState.GROW_MED);
          }
          else if (values[j].equals("b")){
            forestGrid[i][j] = new ForestPatch(ForestState.BURN_HOT);
          }
          else if (values[j].equals("a")){
            forestGrid[i][j] = new ForestPatch(ForestState.ASH);
          }
          else if (values[j].equals("r")){
            forestGrid[i][j] = new ForestPatch(ForestState.BURN_MED);
          }
          else if (values[j].equals("n")){
            forestGrid[i][j] = new ForestPatch(ForestState.BURN_MILD);
          }
          else{
            throw new IncorrectFormatException("Sorry should the letter h, l or m. Line Error:" + linecount);
          }
        }


      }

    }
    data.close(); //closing scanner
    return forestGrid;

    //index 8 height index 9 width
    }


  catch(FileNotFoundException ab){
      System.out.println("File could not be found");
      return null;
  }

}



public static void writeto(Forest f , String filepath) throws FileNotFoundException{

  try{

    File f1 = new File(filepath);
    PrintStream output = new PrintStream(f1);

    String add = f.toString();

    output.print(add);

    output.close();
  }catch(FileNotFoundException a){
    System.out.println("Sorry file not found");
  }


  }

}
