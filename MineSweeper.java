import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    Random random = new Random();
    Scanner input = new Scanner(System.in);
   int row,col;
   int mineNum;
   String[][] map;
   String[][] playerMap;
   boolean game = true;

   MineSweeper(int row,int col){
       this.row = row;
       this.col = col;
       this.map = new String[this.row][this.col];
       this.playerMap = new String[this.row][this.col];
       this.mineNum = (row * col) / 4;
   }
   public void play(){
       int row,col;
       int isWin = 0;
       layingMines();

       System.out.println("Welcome To Minesweeper Game");
       System.out.println("Find all boxes that do not contain mines");
       System.out.println("GAME STARTED");
       while (game){
           showMap(this.playerMap);
           System.out.print("Row: ");
           row = input.nextInt();
           System.out.print("Column: ");
           col = input.nextInt();

           if (!Objects.equals(this.map[row][col], "*")){
               checkMine(row,col);
               isWin++;
               if (isWin(isWin)){
                   System.out.println("CONGRATULATIONS. You finished the game without stepping any mine.");
                   break;
               }
           }
           else {
               game = false;
               System.out.println("BOOM! You stepped on a mine");
               System.out.println("GAME OVER!");
           }
       }
       showMap(this.playerMap);
   }
   public void checkMine(int r,int c){
       if (Objects.equals(this.map[r][c], "-")){
           if ((r > 0) && (Objects.equals(this.map[r - 1][c], "*"))){
               this.playerMap[r][c] = "1";
           }
           if ((r < this.row - 1) && (Objects.equals(this.map[r + 1][c], "*"))){
               this.playerMap[r][c] = "1";
           }
           if ((c > 0) && (Objects.equals(this.map[r][c - 1], "*"))){
               this.playerMap[r][c] = "1";
           }
           if ((c < this.col - 1) && (Objects.equals(this.map[r][c + 1], "*"))){
               this.playerMap[r][c] = "1";
           }
           if (Objects.equals(this.playerMap[r][c], "-")){
               this.playerMap[r][c] = "0";
           }

       }
   }
   public void mapStart(){
       for (int i = 0;i < this.map.length;i++){
           for (int j = 0;j < this.map[0].length;j++){
               this.map[i][j] = "-";
               this.playerMap[i][j] = "-";
           }
       }
   }
   public void layingMines(){
       int randCol;
       int randRow;
       int counter = 0;

       mapStart();
       while (counter != mineNum){
           randRow = random.nextInt(this.row);
           randCol = random.nextInt(this.col);

           if (!Objects.equals(this.map[randRow][randCol], "*")){
               this.map[randRow][randCol] = "*";
               counter++;
           }

       }

   }
    public void showMap(String[][] arr){
        for (String[] s: arr){
            for (int j = 0;j < arr[0].length;j++){
                System.out.print(s[j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWin(int t) {
        return t == ((this.row * this.col) - this.mineNum);
    }
}
