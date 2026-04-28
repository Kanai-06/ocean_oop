public class Main {
    public static void main(String[] args) {
        String WHITE = "\u001B[47m";
        String RESET = "\u001B[0m";
        String CELL = "ㅤ";

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 10; j++){
                if(Math.random() < 0.5){
                    System.out.print(WHITE + CELL + RESET );
                } else{
                    System.out.print(CELL);
                }
            }
            System.out.println();
        }
        
    }
}
