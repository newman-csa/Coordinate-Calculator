import java.text.DecimalFormat;

public class LinearEquation {
    DecimalFormat formatAsInt = new DecimalFormat("0.#");

    /* Instance Variables */
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double xDelta;
    private double yDelta;

    /* Creates a LinearEquation object */
    /*
     * PRECONDITION: x1 and x2 are NOT equal (client programs are responsible for
     * ensuring
     * this precondition is not violated)
     * public LinearEquation(int x1, int y1, int x2, int y2)
     */
    public LinearEquation(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        xDelta = x2 - x1;
        yDelta = y2 - y1;
    }

    /*
     * Calculates and returns distance between (x1, y1) and (x2, y2), rounded to
     * the nearest hundredth
     */
    public double distance() {
        return roundedToHundredth(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    /*
     * Calculates and returns the y-intercept of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth
     */
    public double yIntercept() {
        return roundedToHundredth(y1 - x1 * slope());
    }

    /*
     * Calculates and returns the slope of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth
     */
    public double slope() {
        return roundedToHundredth(yDelta / xDelta);
    }

    /*
     * Returns a String that represents the linear equation of the line through
     * points
     * (x1, y1) and (x2, y2) in slope-intercept (y = mx + b) form, e.g.
     * "y = 3x + 1.5".
     */
    public String equation() {
        String str = "y = ";
        if (yDelta == 0) {
            return str + formatAsInt.format(y1);
        }
        str += (slope() < 0) ? "-" : "";
        str += formatAsInt.format(Math.abs(yDelta / gcf())) + "/" + formatAsInt.format(Math.abs(xDelta / gcf())) + "x ";
        str += (yIntercept() == 0) ? "" : (yIntercept() < 0) ? "- " + Math.abs(yIntercept()) : "+ " + yIntercept();
        return str;
    }

    /*
     * Returns a String of the coordinate point on the line that has the given x
     * value, with
     * both x and y coordinates as decimals to the nearest hundredth, e.g (-5.0,
     * 6.75)
     */
    public String coordinateForX(double xValue) {
        return "(" + formatAsInt.format(roundedToHundredth(xValue)) + ", " + //
                formatAsInt.format(roundedToHundredth((xValue * slope() + yIntercept()))) + ")";
    }

    public double roundedToHundredth(double toRound) {
        return Math.round(toRound * 100.0) / 100.0;
    }

    public int gcf() {
        if (xDelta == 0 || yDelta == 0) {
            return 1;
        } else if (xDelta % 1 != 0 || yDelta % 1 != 0) {
            return 1;
        }

        // absH = Absolute Value of Highest Number
        // absL = Absolute Value of Lowest Number
        int absH = Math.max((int) Math.abs(xDelta), (int) Math.abs(yDelta));
        int absL = Math.min((int) Math.abs(xDelta), (int) Math.abs(yDelta));
        int lcm = absH;
        while (lcm % absL != 0) {
            lcm += absH;
        }

        return (absH * absL) / lcm;
    }

    /*
     * Returns a string that includes all information about the linear equation,
     * each on separate lines:
     */
    public String lineInfo() {
        String str = "The two points are: (" + formatAsInt.format(x1) + ", " + formatAsInt.format(y1) + ") and (" + //
                formatAsInt.format(x2) + ", " + formatAsInt.format(y2) + ")\n" + //
                "The equation of the the line between these points is: " + equation() + "\n" + //
                "The slope of this line is: " + slope() + "\n" + //
                "The y-intercept of the line is: " + yIntercept() + "\n" + //
                "The distance between the two points is: " + distance(); //
        return str;
    }
}