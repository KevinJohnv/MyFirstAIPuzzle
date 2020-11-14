package AI;

import java.util.*;
public class AIStart {
    public ArrayList<PossibleMove> moveList = new ArrayList<PossibleMove>();
    public AIStart(int[][] arr){
        new SelectNextStep(arr);
    }
}
