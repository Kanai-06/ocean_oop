public class Main {
    public static void main(String[] args) {
        String WHITE = "\u001B[47m";
        String RESET = "\u001B[0m";
        String DEAD_CELL = "ㅤ";
        String LIVE_CELL = WHITE + DEAD_CELL + RESET;

        

        
    }

    public boolean[][] createRandomGrid(int size, double lifeProbability){
        boolean[][] grid = new boolean[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = Math.random() < lifeProbability;
            }
        }

        return grid;
    }


}
