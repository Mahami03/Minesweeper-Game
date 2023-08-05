import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row,col;
        System.out.print("Enter Minesweeper Size (Row and Column): ");
        row = input.nextInt();
        col = input.nextInt();
        MineSweeper mine = new MineSweeper(row,col);
        mine.play();
    }
}
