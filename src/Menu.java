import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void firstPage(Notebook notebook) {
        Scanner scanner = new Scanner(System.in);
        int whatToDo = 0;
        System.out.println("What do you want to do? (enter the number)");
        System.out.println("1. add a note");
        System.out.println("2. remove a note");
        System.out.println("3. view all of the notes");
        System.out.println("4. export");
        System.out.println("5. exit");
        try {
            whatToDo = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a NUMBER. Try again.");
            firstPage(notebook);
            return;
        }
        if (whatToDo > 5 || whatToDo < 1) {
            System.out.println("The number should be between 1 and 5. Try again.");
            firstPage(notebook);
            return;
        } else if (whatToDo == 1) {
            return;
        } else if (whatToDo == 2) {
            return;
        } else if (whatToDo == 3) {
            return;
        } else if (whatToDo == 4) {
            return;
        } else if (whatToDo == 5) {
            System.exit(0);
        }
    }
}
