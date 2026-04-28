public class Grid {
    private boolean[][] grid;
    private final int length;
    private final int width;
    private final double lifeProbability;
    private static final String GREEN = "\u001B[42m";
    private static final String BLUE = "\u001B[44m";
    private static final String WHITE = "\u001B[47m";
    private static final String BLACK = "\u001B[40m";
    private static final String RESET = "\u001B[0m";
    private static final String CELL = "ㅤ";
    private static final String DEAD_CELL = BLACK + CELL + RESET; 
    private static final String LIVE_CELL = WHITE + CELL + RESET;   

    public Grid(int length, int width, double lifeProbability){
        this.length = length;
        this.width = width;
        this.lifeProbability = lifeProbability;

        grid = new boolean[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                grid[i][j] = Math.random() < lifeProbability;
            }
        }
    }

    private int cellScore(int x, int y){
        int res = 0;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                int checkX = x + i;
                int checkY = y + j;
                
                if(checkX > length - 1) checkX = checkX % length;
                if(checkY > width - 1) checkY = checkY % width;

                if(checkX < 0) checkX = length + checkX;
                if(checkY < 0) checkY = width + checkY;

                res += grid[checkX][checkY] ? 1 : 0;
            }
        }

        return res;
    }

    private boolean computeCell(int x, int y){
        int cellScore = cellScore(x, y);

        if(cellScore == 3) return true;
        if(cellScore == 4) return grid[x][y];
        return false;
    }

    public void compute(){
        boolean[][] nextGeneration = new boolean[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                nextGeneration[i][j] = computeCell(i, j);
            }
        }

        grid = nextGeneration;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                s.append(grid[i][j] ? LIVE_CELL : DEAD_CELL);
            }
            s.append("\n");
        }

        return s.toString();
    }
    
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
