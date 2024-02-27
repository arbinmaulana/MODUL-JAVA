import java.nio.channels.ClosedByInterruptException;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.Period;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int menu;
        System.out.println("Library System Lobby");
        System.out.println("1. login as student");
        System.out.println("2. login as admin");
        System.out.println("3. exit");
        System.out.print("Choose option (1-3):");
        menu = obj.nextInt();
        obj.nextLine();


        switch (menu){
            case 1 :
                System.out.print("Enter your NIM:");
                String nim = obj.nextLine();
                int len = nim.length();
                if (len == 15){
                    System.out.println("succesful login as student");
                }else if (len > 15 || len < 15) {
                    System.out.println("User not found");
                    break;
                }
            case 2 :
                System.out.print("Enter your username (admin): ");
                String usn = obj.nextLine();
                System.out.print("Enter your password (admin): ");
                String pass = obj.nextLine();

                if (usn.equals("admin")&& pass.equals("adm1n")){
                    System.out.println("Succesful login as Admin");
                }else {
                    System.out.println("Admin User Not FOund");
                }
                break;
            case 3 :
                System.out.println("adios");
                break;

        }



    }
}






