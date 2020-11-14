package Engine;

import java.util.Random;

import AI.AIStart;
import AI.SelectNextStep;

public class Engine {
    //Vars
    int[][] matrix = new int[3][3];
    boolean solvable = false;

    // Creates a solvable 3x3 matrix
    public Engine() {
        randomMatrixGenerator();
        /*while(solvable == false){
            randomMatrixGenerator();
            System.out.println("Created matrix:");
            SelectNextStep.printArray(matrix);
            solvable = isSolvable(matrix);
            if(solvable == false){
                System.out.println("Unsolvable matrix found.... creating new matrix\n\n");
            }
        }*/
        System.out.println("Created matrix:");
        SelectNextStep.printArray(matrix);
        new AIStart(matrix);

    }

    int[] delItem(int index, int[] arr){
        int[] newArr = new int[arr.length - 1];

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            newArr[j++] = arr[i];
        }
        return newArr;
    }

    static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return rnd;
    }

    // Generates random 3x3 matrix
    void randomMatrixGenerator(){
        int[] array = new int[]{0,1,2,3,4,5,6,7,8};

        int randomNum = -1 + (int)(Math.random() * 3);
        if(randomNum==0){
            matrix[0][0] = 2;
            matrix[0][1] = 3;
            matrix[0][2] = 6;
            matrix[1][0] = 1;
            matrix[1][1] = 4;
            matrix[1][2] = 8;
            matrix[2][0] = 7;
            matrix[2][1] = 5;
            matrix[2][2] = 0;
        }else if (randomNum == 1){
            matrix[0][0] = 1;
            matrix[0][1] = 2;
            matrix[0][2] = 3;
            matrix[1][0] = 4;
            matrix[1][1] = 5;
            matrix[1][2] = 6;
            matrix[2][0] = 7;
            matrix[2][1] = 0;
            matrix[2][2] = 8;
        }else if(randomNum == 2){
            matrix[0][0] = 0;
            matrix[0][1] = 1;
            matrix[0][2] = 3;
            matrix[1][0] = 4;
            matrix[1][1] = 2;
            matrix[1][2] = 5;
            matrix[2][0] = 7;
            matrix[2][1] = 8;
            matrix[2][2] = 6;
        }

    }

    static int getInvCount(int[][] arr)
    {
        int inv_count = 0;
        for (int i = 0; i < 3 - 1; i++)
            for (int j = i + 1; j < 3; j++)

                // Value 0 is used for empty space
                if (arr[j][i] > 0 &&
                        arr[j][i] > arr[i][j])
                    inv_count++;
        return inv_count;
    }

    // This function returns true
    // if given 8 puzzle is solvable.
    static boolean isSolvable(int[][] puzzle)
    {
        // Count inversions in given 8 puzzle
        int invCount = getInvCount(puzzle);

        // return true if inversion count is even.
        return (invCount % 2 == 0);
    }

}
