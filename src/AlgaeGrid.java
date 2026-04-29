public class AlgaeGrid extends Grid{
    public AlgaeGrid(int length, int width, double probability){
        super(length, width);

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                grid[i][j] = Math.random() < probability;
            }
        }
    }

    protected Boolean computeCell(int x, int y){
        int nbNeighors = nbNeighors(x, y);

        if((!(boolean)(grid[x][y]) && nbNeighors == 3)) return true;
        return (Boolean)(grid[x][y]);
    }

    public void compute(){
        Boolean[][] nextGeneration = new Boolean[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                nextGeneration[i][j] = computeCell(i, j);
            }
        }

        grid = nextGeneration;
    }
}
