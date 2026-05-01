import java.util.ArrayList;
import java.util.Collections;

public class AnimalGrid extends Grid{
    private Object[][] algaeGrid;

    public AnimalGrid(int length, int width, double fishProbability, double sharkProbability, AlgaeGrid algaeGrid){
        super(length, width);
        this.algaeGrid = algaeGrid.grid;

        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                if(Math.random() < sharkProbability) grid[i][j] = new Shark();
                else if(Math.random() < fishProbability) grid[i][j] = new Fish();
                else grid[i][j] = new Animal(0);
            }
        }
    }

    private ArrayList<int[]> availableNeighbors(int x, int y){
        ArrayList<int[]> res = new ArrayList<int[]>();

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((i == 0 && j == 0) || (Math.abs(i) == 1 && Math.abs(j) == 1)) continue;

                int checkX = x + i;
                int checkY = y + j;
                
                if(checkX > width - 1) checkX = checkX % width;
                if(checkY > length - 1) checkY = checkY % length;

                if(checkX < 0) checkX = width + checkX;
                if(checkY < 0) checkY = length + checkY;

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

        Collections.shuffle(res);

        return res;
    }

    private void computeCell(int x, int y){
        ArrayList<int[]> availableNeighbors = availableNeighbors(x, y);
        Animal val = (Animal)(grid[x][y]);

        val.step();

        boolean hasMoved = false;

        for(int[] cell : availableNeighbors){
            if(val instanceof Fish){
                boolean checkBirth = val.checkBirth(Fish.TIME_TO_BIRTH);
                Algae algae = ((Algae)(algaeGrid[cell[0]][cell[1]]));
                
                if(algae.get()){
                    val.eat(Fish.MAX_ENERGY);
                    algae.set(false);
                    moveCell(x, y, cell[0], cell[1], checkBirth);
                    hasMoved = true;

                    break ;
                }
            }

            if(val instanceof Shark){
                boolean checkBirth = val.checkBirth(Shark.TIME_TO_BIRTH);
                Animal neighbor = ((Animal)(grid[cell[0]][cell[1]]));
                
                if(neighbor instanceof Fish && neighbor.isAlive()){
                    val.eat(Shark.MAX_ENERGY);
                    neighbor.die();
                    moveCell(x, y, cell[0], cell[1], checkBirth);
                    hasMoved = true;
                    
                    break ;
                }
            }
        }

        if(!hasMoved && availableNeighbors.size() > 0){
            int[] randomCell = availableNeighbors.get((int)(Math.random()*availableNeighbors.size()));
            moveCell(x, y, randomCell[0], randomCell[1], false);
        }
    }

    private void moveCell(int x, int y, int destX, int destY, boolean birth){
        Animal val = (Animal)(grid[x][y]);

        grid[destX][destY] = val.clone();
        ((Animal)(grid[x][y])).die();

        val.setMoved(true);

        if(birth){
            if(val instanceof Fish) grid[x][y] = new Fish();
            if(val instanceof Shark) grid[x][y] = new Shark();
        }
    }


    public void compute(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                Animal val = (Animal)(grid[i][j]);

                if(val instanceof Fish && val.isAlive() && !val.movedThisStep()) computeCell(i, j);
            }
        }

        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                Animal val = (Animal)(grid[i][j]);

                if(val instanceof Shark && val.isAlive() && !val.movedThisStep()) computeCell(i, j);
            }
        }

        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                Animal val = (Animal)(grid[i][j]);

                val.setMoved(false);
            }
        }
    }
}
