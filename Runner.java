import java.util.Scanner;

public class Runner {
    public static double parseX(String rawPoint) {
        return Double.parseDouble(rawPoint.substring(1, rawPoint.indexOf(",")));
    }

    public static double parseY(String rawPoint) {
        return Double.parseDouble(rawPoint.substring(rawPoint.indexOf(",") + 1, rawPoint.indexOf(")")));
    }

    

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome!");
        System.out.print("Enter coordinate 1: ");
        String coord1 = scn.nextLine();

        try {
            parseX(coord1);
            parseY(coord1);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.print("Enter coordinate 2: ");
        String coord2 = scn.nextLine();

        try {
            parseX(coord2);
            parseY(coord2);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println();
    }
}