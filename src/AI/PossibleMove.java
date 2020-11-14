package AI;

import java.lang.*;
import java.math.*;
public class PossibleMove {
    //Vars
    int[][] matrix;
    int[] locOf0;
    PossibleMove previousMove = null;
    int fn;
    int hn;
    int step;
    // 0=left 1=right 2=up 3=down
    int movment;
    boolean used = false;
    int address;

    PossibleMove(){}

    PossibleMove(int[][] arr,PossibleMove pm,int newStep, int move, int address){
        matrix=arr;
        previousMove = pm;
        step = newStep;
        locOf0 = findNum(arr,0);
        movment = move;
        hnCalc();
        fnCalc();
        this.address= address;
    }
    PossibleMove(int[][] arr,int newStep){
        matrix=arr;
        step=newStep;
        locOf0 = findNum(arr,0);
        hnCalc();
        fnCalc();
        used = true;
        address=0;
    }

    int getFn(){
        return fn;
    }

    boolean getUsed(){
        return used;
    }

    int getHn(){
        return hn;
    }

    int getMovment(){return movment;}

    int[][] getMatrix(){return matrix;}

    int getAddress(){return  address;}

    PossibleMove getPreviousMove(){return previousMove;}

    void setUsed(boolean x){
        used = x;
    }



    void fnCalc(){
        fn = step+hn;
    }

    void hnCalc(){
        hn =
        manhattanCalc(findNum(matrix,0), new int []{2,2})+
        manhattanCalc(findNum(matrix,1), new int[]{0,0})+
        manhattanCalc(findNum(matrix,2), new int[]{0,1})+
        manhattanCalc(findNum(matrix,3), new int[]{0,2})+
        manhattanCalc(findNum(matrix,4), new int[]{1,0})+
        manhattanCalc(findNum(matrix,5), new int[]{1,1})+
        manhattanCalc(findNum(matrix,6), new int[]{1,2})+
        manhattanCalc(findNum(matrix,7), new int[]{2,0})+
        manhattanCalc(findNum(matrix,8), new int[]{2,1});
    }

    int manhattanCalc(int[]current,int[]desired){
        int value = Math.abs(current[0]-desired[0])+ Math.abs(current[1]-desired[1]);
        return value;
    }

    public int[] findNum(int[][]arr,int x){
        boolean found = false;
        int[] loc = new int[]{-1};
        while(found == false){
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr.length;j++){
                    if(arr[i][j]==x){
                        loc = new int[]{i,j};
                        found = true;

                        //break out of the loop
                        j= arr.length;
                        i= arr.length;
                    }
                }
            }
        }

        return loc;
    }
}
