public class Grid {
    private boolean[][] grid;
    private final int size;
    private final double lifeProbability;
    private static final String WHITE = "\u001B[46m";
    private static final String RESET = "\u001B[0m";
    private static final String DEAD_CELL = "ㅤ";
    private static final String LIVE_CELL = WHITE + DEAD_CELL + RESET;   

    public Grid(int size, double lifeProbability){
        this.size = size;
        this.lifeProbability = lifeProbability;

        grid = new boolean[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = Math.random() < lifeProbability;
            }
        }
    }

    public void print(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j]) System.out.print(LIVE_CELL);
                else System.out.print(DEAD_CELL);
            }
            System.out.println();
        }
    }
    
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
