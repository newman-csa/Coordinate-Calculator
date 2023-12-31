import java.util.Scanner;

public class Runner {
    /**
     * Prints welcome message to user
     */
    public static void start() {
        System.out.println("Welcome!");
        System.out.println("Please write any point, integer or decimal");
    }

    /**
     * Parses the x-value of a coordinate in the form "(x, y)". Can parse either an
     * integer or double but will always return double type.
     * 
     * @param rawPoint - a point in form "(x, y)" to parse x from.
     * @return a double; the x value of the given point.
     */
    public static double parseX(String rawPoint) {
        return Double.parseDouble(rawPoint.substring(1, rawPoint.indexOf(",")));
    }

    /**
     * Parses the y-value of a coordinate in the form "(x, y)". Can parse either an
     * integer or double but will always return double type.
     * 
     * @param rawPoint - a point in form "(x, y)" to parse y from.
     * @return a double; the y value of the given point.
     */
    public static double parseY(String rawPoint) {
        return Double.parseDouble(rawPoint.substring(rawPoint.indexOf(",") + 1, rawPoint.indexOf(")")));
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        // Welcomes user; asks to input coordinates
        start();
        System.out.print("Enter coordinate 1: ");
        String coord1 = scn.nextLine();
        System.out.print("Enter coordinate 2: ");
        String coord2 = scn.nextLine();
        System.out.println();
        // Catch if user inputs coordinate in wrong form
        try {
            parseX(coord1);
            parseY(coord1);
            parseX(coord2);
            parseY(coord2);
        } catch (Exception e) {
            System.out.println("Write point in form \"(x, y)\"");
            System.exit(0);
        }

        // Check if user inputs an undefined line
        if (parseX(coord1) == parseX(coord2)) {
            System.out.println("These points are on a vertical line x = " + parseX(coord1));
            System.exit(0);
        }

        // Create LinearEquation object
        LinearEquation obj = new LinearEquation(parseX(coord1), parseY(coord1), parseX(coord2), parseY(coord2));
        System.out.println(obj.lineInfo());

        // Asks user for x-value on line
        System.out.print("\nEnter a value for x: ");
        double x = scn.nextDouble();

        System.out.println("\n" + obj.coordinateForX(x));

    }
}