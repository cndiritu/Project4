import java.awt.Color;
import java.util.*;

/**
* Represents a forest in the ForestFire simulation using a 2-dimensional array.
*
* @author Christine Reilly
*/
public class Forest {

  // PROJECT 2: you may not add instance or class variables

  /** The two-dimensional array that represents the forest */
  private ForestPatch[][] forest;

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Creates a Forest object by making a deep copy of the parameter array.
  *
  * @param f The array to copy into this Forest object.
  *
  * @throws IllegalArgumentException if the rows of the parameter array are not all the same length.
  */
  public Forest(ForestPatch[][] f) {
      this.forest = new ForestPatch[f.length][f[0].length];
      for (int i = 0; i < f.length; i++){ 
        for(int j = 0; j < f[i].length; j++){
          if(f[0].length != f[i].length){
            throw new IllegalArgumentException("Sorry length is not the same in the tree");
          }
          else{
            this.forest[i][j] = new ForestPatch(f[i][j].getState());
          }
        }
      }



  }



    // Project 2: write this method
   // end of constructor

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Returns a deep copy of the array representing this Forest object.
  *
  * @return A deep copy of the array representing this Forest object.
  */
  public ForestPatch[][] getForest() {
    ForestPatch[][] copy = new ForestPatch[this.forest.length][this.forest[0].length];
    for (int i = 0; i < this.forest.length; i++){
      for ( int j = 0; j < this.forest[i].length; j++){
        copy[i][j] = new ForestPatch(this.forest[i][j].getState());
    }

  }
  return copy;
}








  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Returns an array of the Color objects associated with each patch in this forest.
  *
  * @return An array of the Color objects associated with each patch in this forest.
  */
  public Color[][] getForestColors() {
    Color [][] colorr = new Color[this.forest.length][this.forest[0].length];

    for (int i = 0; i < this.forest.length; i++){
      for (int j = 0; j < this.forest[i].length;j++){
        colorr[i][j] = this.forest[i][j].getState().getColor();

    // Project 2: write this method
      }
   }
    return colorr;
    }

  /**
  * PROJECT 2: COMPLETE THIS METHOD
  *
  * Runs one step of the forest fire simulation. The next state of forest patch
  * is determined based on the current state of its neighbors.
  */



 public void runOneStep(){ //where do I store the next State??
  int numofrows = this.forest.length - 1;
  int rowlength = this.forest[0].length - 1; //every forest is a balanced 2d array so this can work
  ForestPatch [][] nextForest = new ForestPatch[this.forest.length][this.forest[0].length];

 for (int i = 0; i < this.forest.length; i++) {
   for (int j = 0; j < this.forest[i].length; j++) {
     if(i == 0 && j == 0){//top left corner
       ForestPatch[] neighbors = new ForestPatch[2];
       neighbors[0] = this.forest[i][j+1];
       neighbors[1] = this.forest[i+1][j];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );
     }
     else if(i == 0 && j == rowlength){ //top right corner
       ForestPatch[] neighbors = new ForestPatch[2];
       neighbors[0] = this.forest[i+1][j];
       neighbors[1] = this.forest[i][j-1];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );
     }
     else if(i == numofrows && j == 0 ){ //bottom left corner
          ForestPatch[] neighbors = new ForestPatch[2];
           neighbors[0] = this.forest[i-1][j];
           neighbors[1] = this.forest[i][j+1];
           nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );

     }
     else if(i == numofrows && j == rowlength){//bottom right corner
       ForestPatch[] neighbors = new ForestPatch[2];
       neighbors[0] = this.forest[i-1][j];
       neighbors[1] = this.forest[i][j-1];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );

     }
     else if(i == 0){ //top row
       ForestPatch[] neighbors = new ForestPatch[3];
       neighbors[0] = this.forest[i][j+1];
       neighbors[1] = this.forest[i+1][j];
       neighbors[2] = this.forest[i][j-1];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );

     }
     else if(i == numofrows){ //bottom row
       ForestPatch[] neighbors = new ForestPatch[3];
       neighbors[0] = this.forest[i][j-1];
       neighbors[1] = this.forest[i-1][j];
       neighbors[2] = this.forest[i][j+1];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );

     }
     else if(j == 0){ // //left column excludes corners
       ForestPatch[] neighbors = new ForestPatch[3];
       neighbors[0] = this.forest[i+1][j]; //right
       neighbors[1] = this.forest[i][j+1]; // up
       neighbors[2] = this.forest[i-1][j+1]; // down
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );


     }
     else if(j == rowlength){ //right column excludes corners
       ForestPatch[] neighbors = new ForestPatch[3];
       neighbors[0] = this.forest[i-1][j];
       neighbors[1] = this.forest[i][j-1];
       neighbors[2] = this.forest[i+1][j];
       nextForest[i][j] = new ForestPatch(this.forest[i][j].nextState(neighbors));


     }
     else {
       // not an edge piece
       ForestPatch[] neighbors = new ForestPatch[4];
       neighbors[0] = this.forest[i+1][j];
       neighbors[1] = this.forest[i-1][j];
       neighbors[2] = this.forest[i][j+1];
       neighbors[3] = this.forest[i][j-1];
       nextForest[i][j] = new ForestPatch( this.forest[i][j].nextState(neighbors) );
     }
   }

}
for(int k = 0; k < this.forest.length; k++){ // putting this.forest in its next state
  for(int r = 0; r < this.forest[k].length;r++){
    this.forest[k][r].setState(nextForest[k][r].getState());
  }
}


}// end of constructor
public String toString(){

  //lines below convert the ForestPatch probabilities into a string to display with the forest
  String b = Double.toString(ForestPatch.burnHot_burnMed) + " " + Double.toString(ForestPatch.burnMed_burnMild)+ " "  +  Double.toString(ForestPatch.burnMild_ashes)+ " "  +Double.toString(ForestPatch.ashes_growLow)+ " "  +Double.toString(ForestPatch.growLow_growMed)+ " "  +Double.toString(ForestPatch.growMed_growHigh)+ " "  + Double.toString(ForestPatch.growHigh_burnHot_spon)+ " "  + Double.toString(ForestPatch.growHigh_burnHot_neighbor)+ " "  + Integer.toString(this.forest.length) +" "+ Integer.toString(this.forest[0].length);
  String a = "";
  for(int i = 0; i < this.forest.length; i++){//display the char that represents the state of reach forestpatch
    for(int j = 0; j < this.forest[i].length;j++){
      a += this.forest[i][j].getState().getChar();

    }
    a += "\n";
  }
  return b + "\n" + a;
}
}
