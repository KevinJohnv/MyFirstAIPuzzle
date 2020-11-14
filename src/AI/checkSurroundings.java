package AI;

public class checkSurroundings {
    checkSurroundings(){}
    /* Takes an array and checks to see if there are cells to the left, right, up and down of the selected location.
     Returns an array [left,right,up,down] with 1 or 0. 1 signifying there is a cell and 0 saying there isnt. If error is found
    throws a -1 instead.*/
    public int[] checkSurroundings(int[][] arr,int[] loc, int[]matrixBounds){
        int l=0;
        int r=0;
        int u=0;
        int d=0;

        int x= loc[1];
        int y= loc[0];

        int xMax = matrixBounds[1]-1;
        int yMax = matrixBounds[0]-1;

        if(x>0 && x<xMax){
            l=1;
            r=1;
        }else if(x==0){
            r=1;
        }else if(x==xMax) {
            l=1;
        }else{
            r=-1;
            l=-1;
        }

        if(y>0 && y<xMax){
            u=1;
            d=1;
        }else if(y==0){
            d=1;
        }else if(y==yMax) {
            u=1;
        }else{
            u=-1;
            d=-1;
        }

        return new int[]{l,r,u,d};
    }
}
