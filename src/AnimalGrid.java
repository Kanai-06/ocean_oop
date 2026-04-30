import java.util.ArrayList;

public class AnimalGrid extends Grid{
    private Object[][] algaeGrid;

    public AnimalGrid(int length, int width, double fishProbability, double sharkProbability, AlgaeGrid algaeGrid){
        super(length, width);
        this.algaeGrid = algaeGrid.grid;

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                if(Math.random() < sharkProbability) grid[i][j] = new Shark();
                else if(Math.random() < fishProbability) grid[i][j] = new Fish();
                else grid[i][j] = new Animal(0);
            }
        }
    }

    protected ArrayList<int[]> availableNeighbors(int x, int y){
        ArrayList<int[]> res = new ArrayList<int[]>();

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((i == 0 && j == 0) || (Math.abs(i) == 1 && Math.abs(j) == 1)) continue;

                int checkX = x + i;
                int checkY = y + j;
                
                if(checkX > length - 1) checkX = checkX % length;
                if(checkY > width - 1) checkY = checkY % width;

                if(checkX < 0) checkX = length + checkX;
                if(checkY < 0) checkY = width + checkY;

                Animal val = (Animal)(grid[x][y]);
                Animal neighborVal = (Animal)(grid[checkX][checkY]);
                boolean animalPresent;

                if(val instanceof Shark){
                    animalPresent = neighborVal instanceof Shark && neighborVal.isAlive();
                } else {
                    animalPresent = neighborVal.isAlive();
                }

                if(!animalPresent) res.add(new int[] {checkX, checkY});
            }
        }

        return res;
    }

    protected void computeCell(int x, int y){
        ArrayList<int[]> availableNeighbors = availableNeighbors(x, y);
        Animal val = (Animal)(grid[x][y]);

        val.step();

        for(int[] cell : availableNeighbors){
            if(val instanceof Fish){
                Algae algae = ((Algae)(algaeGrid[cell[0]][cell[1]]));
                
                if(algae.get()){
                    val.eat(Fish.MAX_ENERGY);
                    grid[cell[0]][cell[1]] = val.clone();
                    ((Animal)(grid[x][y])).die();
                    algae.set(false);

                    break ;
                }
            }

            if(val instanceof Shark){
                Animal neighbor = ((Animal)(grid[cell[0]][cell[1]]));
                
                if(neighbor instanceof Fish && neighbor.isAlive()){
                    val.eat(Shark.MAX_ENERGY);
                    neighbor.die();
                    grid[cell[0]][cell[1]] = val.clone();
                    ((Animal)(grid[x][y])).die();
                    
                    break ;
                }
            }
            
            int[] randomCell = availableNeighbors.get((int)(Math.random()*availableNeighbors.size()));
            grid[randomCell[0]][randomCell[1]] = val.clone();
            ((Animal)(grid[x][y])).die();
        }
    }

    public void compute(){
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                Animal val = (Animal)(grid[i][j]);

                if(val instanceof Fish && val.isAlive()) computeCell(i, j);
            }
        }

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                Animal val = (Animal)(grid[i][j]);

                if(val instanceof Shark && val.isAlive()) computeCell(i, j);
            }
        }
    }
}
