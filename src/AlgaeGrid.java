public class AlgaeGrid extends Grid{
    public AlgaeGrid(int length, int width, double probability){
        super(length, width);

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                grid[i][j] = new Algae(Math.random() < probability);
            }
        }
    }

    protected int nbNeighors(int x, int y){
        int res = 0;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0) continue;

                int checkX = x + i;
                int checkY = y + j;
                
                if(checkX > length - 1) checkX = checkX % length;
                if(checkY > width - 1) checkY = checkY % width;

                if(checkX < 0) checkX = length + checkX;
                if(checkY < 0) checkY = width + checkY;

                boolean algaePresent = ((Algae)(grid[checkX][checkY])).get();

                res += algaePresent ? 1 : 0;
            }
        }

        return res;
    }

    protected Algae computeCell(int x, int y){
        int nbNeighors = nbNeighors(x, y);
        Algae val = (Algae)(grid[x][y]);

        if((!val.get() && nbNeighors > 2)) val.set(true);

        return val;
    }

    public void compute(){
        Algae[][] nextGeneration = new Algae[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                nextGeneration[i][j] = computeCell(i, j);
            }
        }

        grid = nextGeneration;
    }
}
