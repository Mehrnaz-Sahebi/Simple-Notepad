import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the notebook (for example : myNotebook)");
        String notebookName = scanner.nextLine();
        Notebook notebook = new Notebook(notebookName);
        Menu.firstPage(notebook);
    }
}
