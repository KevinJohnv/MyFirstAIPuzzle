package AI;

import java.util.ArrayList;

public class SelectNextStep {
    int currentStep = 0;
    public ArrayList<PossibleMove> moveList = new ArrayList<PossibleMove>();
    public ArrayList<PossibleMove> unusedMoves = new ArrayList<PossibleMove>();
    public PossibleMove currentMove = new PossibleMove();
    ArrayList<String> movesToMessage = new ArrayList<String>();

    //This intializer handles the first array
    SelectNextStep(int[][] arr){
        //Find the 0 in the system
        int[] loc = findNum(arr,0);

        int[]bounds = new int []{arr.length,arr.length};

        moveList.add(new PossibleMove(arr,0));
        //Check possible moves
        int[] checkPossibleMoves = new checkSurroundings().checkSurroundings(arr,loc,bounds);

        //Check if left is valid
        currentStep++;
        if(checkPossibleMoves[0] == 1){
            System.out.println("Left Movement Valid");
            moveLeft(arr,loc, moveList.get(0)) ;
        }
        if(checkPossibleMoves[1] == 1){
            System.out.println("Right Movement Valid");
            moveRight(arr,loc, moveList.get(0));
        }
        if(checkPossibleMoves[2] == 1){
            System.out.println("Up Movement Valid");
            moveUp(arr, moveList.get(0)) ;
        }
        if(checkPossibleMoves[3] == 1){
            System.out.println("Down Movement Valid");
            moveDown(arr,loc, moveList.get(0)) ;
        }
        // To remove the first "move" or the prompt

        // Check the possible moves with the lowest f(n)
        int i =0;
        int fNMin =i;
        while(i<unusedMoves.size()){
            System.out.println(unusedMoves.get(i).getFn());
            if(unusedMoves.get(i).getFn() < unusedMoves.get(fNMin).getFn()){
                fNMin = i;
            }
            i++;
        }
        boolean done = false;
        while(i<this.unusedMoves.size()){
            //System.out.println(this.unusedMoves.get(i).getFn());
            if(this.unusedMoves.get(i).getHn() == 0){
                done = true;
                printArray(this.unusedMoves.get(i).getMatrix());
                moveSummary(this.unusedMoves.get(i));
            }
            i++;
        }
        if(unusedMoves.get(fNMin).getMovment() == 0){
            int value = unusedMoves.get(fNMin).getFn();
            System.out.println("Left move was selected with an fn of: "+ value);

        } else if(unusedMoves.get(fNMin).getMovment() == 1){
            int value = unusedMoves.get(fNMin).getFn();
            System.out.println("Right move was selected with an fn of: "+ value);

        } else if(unusedMoves.get(fNMin).getMovment() == 2){
            int value = unusedMoves.get(fNMin).getFn();
            System.out.println("Up move was selected with an fn of: "+ value);

        } else if(unusedMoves.get(fNMin).getMovment() == 3){
            int value = unusedMoves.get(fNMin).getFn();
            System.out.println("Down move was selected with an fn of: "+ value);

        }
        printArray(this.unusedMoves.get(fNMin).getMatrix());

        PossibleMove n = unusedMoves.get(fNMin);
        this.unusedMoves.remove(fNMin);

        //currentStep++;
        new SelectNextStep(moveList, unusedMoves, n ,n.step);
    }

    //Handles finding best routes after the first step is done
    SelectNextStep(ArrayList<PossibleMove> moves, ArrayList<PossibleMove> unusedMoves, PossibleMove currentMove, int currentStep) {
        moveList = moves;
        this.unusedMoves = unusedMoves;
        this.currentMove = currentMove;
        this.currentStep = currentStep;

        if (currentMove.getHn() == 0) {
            System.out.println("Done in 1 move! Last move was ");
            if(currentMove.getMovment() == 0){
                System.out.print("move left");
            }else if (currentMove.getMovment() == 1){
                System.out.print("move right");
            }else if(currentMove.getMovment() == 2){
                System.out.print("move up");
            }else if(currentMove.getMovment() == 3){
                System.out.print("move down");
            }

            moveSummary(currentMove);

        } else {

        //Check possible moves and adds valid moves to movelist and unusedmoves
        int[] loc = findNum(currentMove.getMatrix(), 0);
        int[] bounds = new int[]{currentMove.getMatrix().length, currentMove.getMatrix().length};
        int[] checkPossibleMoves = new checkSurroundings().checkSurroundings(currentMove.getMatrix(), loc, bounds);

        currentStep++;
        if (checkPossibleMoves[0] == 1) {
            System.out.println("Left Movement Valid STEP: " + currentStep);
            moveLeft(currentMove.getMatrix(), loc, moveList.get(currentMove.getAddress()));
        }
        if (checkPossibleMoves[1] == 1) {
            System.out.println("Right Movement Valid STEP: " + currentStep);
            moveRight(currentMove.getMatrix(), loc, moveList.get(currentMove.getAddress()));
        }
        if (checkPossibleMoves[2] == 1) {
            System.out.println("Up Movement Valid STEP: " + currentStep);
            moveUp(currentMove.getMatrix(), moveList.get(currentMove.getAddress()));
        }
        if (checkPossibleMoves[3] == 1) {
            System.out.println("Down Movement Valid STEP: " + currentStep);
            moveDown(currentMove.getMatrix(), loc, moveList.get(currentMove.getAddress()));
        }


        //Determine which move has the lowest f(n) and is unused and print out the appropriate message
        int i = 0;
        boolean done = false;
        //Check to see if any of the movements have a hn=0 if so you are done else continue
        while (i < this.unusedMoves.size()) {
            //System.out.println(this.unusedMoves.get(i).getFn());
            if (this.unusedMoves.get(i).getHn() == 0) {
                done = true;
                printArray(this.unusedMoves.get(i).getMatrix());
            }
            i++;
        }
        if (!done) {
            i = 0;
            int fNMin = i;
            while (i < this.unusedMoves.size()) {
                System.out.println(this.unusedMoves.get(i).getFn());
                if (this.unusedMoves.get(i).getFn() < this.unusedMoves.get(fNMin).getFn()) {
                    fNMin = i;
                }
                i++;
            }
            System.out.println("\n\n\n---------------------");
            if (unusedMoves.get(fNMin).getMovment() == 0) {
                int value = this.unusedMoves.get(fNMin).getFn();
                System.out.println("Left move was selected with an fn of: " + value);


            } else if (unusedMoves.get(fNMin).getMovment() == 1) {
                int value = this.unusedMoves.get(fNMin).getFn();
                System.out.println("Right move was selected with an fn of: " + value);


            } else if (unusedMoves.get(fNMin).getMovment() == 2) {
                int value = this.unusedMoves.get(fNMin).getFn();
                System.out.println("Up move was selected with an fn of: " + value);


            } else if (unusedMoves.get(fNMin).getMovment() == 3) {
                int value = this.unusedMoves.get(fNMin).getFn();
                System.out.println("Down move was selected with an fn of: " + value);


            }
            printArray(this.unusedMoves.get(fNMin).getMatrix());

            PossibleMove n = this.unusedMoves.get(fNMin);
            this.unusedMoves.remove(fNMin);

            new SelectNextStep(moveList, this.unusedMoves, n, n.step + 1);
        } else {
            System.out.println("Done! " + currentStep);
            moveSummary(currentMove);
        }


        // NEXT STEP IS TO COMPARE FN OF POSSIBLE MOVES THAT HAVENT BEEN USED--------------------------------------------------
    }

    }


    //Cycles through a 2d array and returns index of the value it is looking for. If it does not find it, it returns -1
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

    // Given a PossibleMove it will switch value at location with value to the left of it and return a possible move
    PossibleMove moveLeft(int[][] arr, int[] loc, PossibleMove previousMove){
        System.out.println("Possible left move: ");
        int[][] tempArr = copyArray(arr) ;

        //Gets the value to the left
        int intLeft;
        int temp;
        intLeft = tempArr[loc[0]][loc[1]-1];
        temp = tempArr[loc[0]][loc[1]];
        tempArr[loc[0]][loc[1]]= intLeft;
        tempArr[loc[0]][loc[1]-1] = temp;

        printArray(tempArr);

        PossibleMove x = new PossibleMove(tempArr, previousMove, currentStep, 0,moveList.size());
        System.out.println("\n"+x.getFn());

        moveList.add(x);
        unusedMoves.add(x);

        return x;

    }

    // Given a PossibleMove it will switch value at location with value to the right of it and return a possible move
    PossibleMove moveRight(int[][] arr, int[] loc, PossibleMove previousMove){
        System.out.println("Possible right movement: ");
        int[][] tempArr = copyArray(arr) ;

        //Gets the value to the right
        int intRight;
        int temp;
        intRight = tempArr[loc[0]][loc[1]+1];
        temp = tempArr[loc[0]][loc[1]];
        tempArr[loc[0]][loc[1]]= intRight;
        tempArr[loc[0]][loc[1]+1] = temp;

        printArray(tempArr);

        PossibleMove x = new PossibleMove(tempArr, previousMove, currentStep,1,moveList.size());
        System.out.println("\n"+x.getFn());

        moveList.add(x);
        unusedMoves.add(x);

        return x;


    }

    // Given a PossibleMove it will switch value at location with value to the above of it and return a possible move
    PossibleMove moveUp(int[][] arr, PossibleMove previousMove){
        System.out.println("Possible up movement: ");
        int[][] tempArr = copyArray(arr) ;
        int[] loc = findNum(arr,0);

        //Gets the value to the up
        int intUp;
        int temp;
        intUp = tempArr[loc[0]-1][loc[1]];
        temp = tempArr[loc[0]][loc[1]];
        tempArr[loc[0]][loc[1]]= intUp;
        tempArr[loc[0]-1][loc[1]] = temp;

        printArray(tempArr);

        PossibleMove x = new PossibleMove(tempArr, previousMove, currentStep, 2,moveList.size());
        System.out.println("\n"+x.getFn());

        moveList.add(x);
        unusedMoves.add(x);

        return x;


    }

    // Given a PossibleMove it will switch value at location with value to the below it and return a possible move
    PossibleMove moveDown(int[][] arr, int[] loc, PossibleMove previousMove){
        System.out.println("Possible down movement: ");
        int[][] tempArr = copyArray(arr) ;

        //Gets the value to the down
        int intDown;
        int temp;
        intDown = tempArr[loc[0]+1][loc[1]];
        temp = tempArr[loc[0]][loc[1]];
        tempArr[loc[0]][loc[1]]= intDown;
        tempArr[loc[0]+1][loc[1]] = temp;

        printArray(tempArr);

        PossibleMove x = new PossibleMove(tempArr, previousMove, currentStep, 3,moveList.size());
        System.out.println("\n"+x.getFn());

        moveList.add(x);
        unusedMoves.add(x);

        return x;

    }

    // Prints the array
    public static void printArray(int[][] arr){
        int i=0;
        while(i< arr.length){
            int j=0;
            while(j<arr.length){
                System.out.print(arr[i][j]+" ");
                j++;
            }
            System.out.println();
            i++;
        }
    }

    public int[][] copyArray(int[][] arr){
        int[][]tempArray = new int[arr.length][arr.length];
        int i=0;
        while(i< arr.length){
            int j=0;
            while(j<arr.length){
                tempArray[i][j] = arr[i][j];
                j++;
            }
            i++;
        }
        return tempArray;
    }

    void moveSummary(PossibleMove lastMove){
        String message = new String();
        if(lastMove.getMovment()==0){
            message = "Slide tile left";
        }else if(lastMove.getMovment()==1){
            message = "Slide tile right";
        }else if(lastMove.getMovment()==2){
            message = "Slide tile up";
        }else if(lastMove.getMovment()==3){
            message = "Slide tile down";
        }

        movesToMessage.add(message);
        if(lastMove.previousMove == null){
            for(int i=movesToMessage.size()-1;i>-1;i--){
                System.out.println(movesToMessage.get(i));
            }
        }
    }


}
