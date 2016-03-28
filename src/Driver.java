
import java.io.File;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose difficulty:");
        System.out.println("1) Beginner");
        System.out.println("2) Veteran");
        String choice = input.nextLine();
        Game g;
        if (choice.equals("1")) {
            g = new Game(Game.BEGINNER_TOKENS, new File("beginner.txt"));
        } else {
            g = new Game(Game.VETERAN_TOKENS, new File("veteran.txt"));
        }
        g.start();
    }
}
