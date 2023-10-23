import java.text.DecimalFormat;

/**
 * A class dealing with attributes pertaining to the linear equation of two
 * points.
 * 
 * @author Logan Newman
 */
public class LinearEquation {
    /*
     * Format: Cut trailing zeros
     * 2.30 → 2.3
     * 23.0 → 2
     */
    DecimalFormat formatAsInt = new DecimalFormat("0.#");

    /* Instance Variables */
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double xDelta;
    private double yDelta;

    /**
     * Creates a LinearEquation object.
     * 
     * <p>
     * PRECONDITION: x1 and x2 are NOT equal. (Client programs are responsible for
     * ensuring this precondition is not violated.)
     * <p\>
     * 
     * @param x1 - the x-value of the first point.
     * @param y1 - the y-value of the first point.
     * @param x2 - the x-value of the second point.
     * @param y2 - the y-value of the second point.
     * TODO: Make this throw exception if x1 == x2
     */
    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        xDelta = x2 - x1;
        yDelta = y2 - y1;
        // TODO: Make this throw exception if x1 == x2
    }

    /**
     * Creates a LinearEquation object.
     * 
     * <p>
     * PRECONDITION: x1 and x2 are NOT equal. (Client programs are responsible for
     * ensuring this precondition is not violated.)
     * <p\>
     * 
     * @param x1 - the x-value of the first point.
     * @param y1 - the y-value of the first point.
     * @param x2 - the x-value of the second point.
     * @param y2 - the y-value of the second point.
     */
    public LinearEquation(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        xDelta = x2 - x1;
        yDelta = y2 - y1;
    }

    /**
     * Calculates and returns distance between (x1, y1) and (x2, y2), rounded to
     * the nearest hundredth.
     * 
     * @return a double; the distance between two points.
     */
    public double distance() {
        return roundedToHundredth(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    /**
     * Calculates and returns the y-intercept of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth.
     * 
     * @return a double; the y-intercept of the linear equation.
     */
    public double yIntercept() {
        return roundedToHundredth(y1 - x1 * slope());
    }

    /**
     * Calculates and returns the slope of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth.
     * 
     * @return a double; the slope of two points.
     */
    public double slope() {
        return roundedToHundredth(yDelta / xDelta);
    }

    /**
     * Returns a String that represents the linear equation of the line through
     * points (x1, y1) and (x2, y2) in slope-intercept (y = mx + b) form, e.g.
     * "y = 3x + 1.5".
     * 
     * <p>
     * Case 1: returns horizontal line (e.g. y = 3)
     * <p/>
     * <p>
     * Case 2: returns slope is "x" or "-x" (e.g. y = -x + 3)
     * <p/>
     * <p>
     * Case 3: returns whole number slope (e.g. y = 3x - 9)
     * <p/>
     * <p>
     * Case 4: returns fractional slope in simplified form( e.g. y = 1/2x + 1.5)
     * <p/>
     * 
     * @return a string; the linear equation of the two points
     */
    public String equation() {
        String str = "y = ";
        if (yDelta == 0) { // If line is horizontal
            return str + formatAsInt.format(y1);
        } else if (Math.abs(xDelta) == Math.abs(yDelta)) { // If slope is x or -x
            str += (slope() < 0) ? "-" : "";
            str += "x ";
        } else if (Math.abs(xDelta) == gcf()) { // If slope is integer number
            str += formatAsInt.format(yDelta / xDelta) + "x ";
        } else { // If slope is fraction
            str += (slope() < 0) ? "-" : "";
            str += formatAsInt.format(Math.abs(yDelta / gcf())) + "/" + formatAsInt.format(Math.abs(xDelta / gcf()))
                    + "x ";

        }
        // y-intercept if not horizontal
        str += (yIntercept() == 0) ? "" : (yIntercept() < 0) ? "- " + Math.abs(yIntercept()) : "+ " + yIntercept();
        return str;
    }

    /**
     * Returns a String of the coordinate point on the line that has the given x
     * value, with both x and y coordinates as decimals to the nearest hundredth,
     * e.g (-5.0, 6.75).
     * 
     * @param xValue - the x-value of the y-value you want.
     * @return a double; corresponding y-value of xValue.
     */
    public String coordinateForX(double xValue) {
        return "(" + formatAsInt.format(roundedToHundredth(xValue)) + ", " + //
                formatAsInt.format(roundedToHundredth((xValue * slope() + yIntercept()))) + ")";
    }

    public double roundedToHundredth(double toRound) {
        return Math.round(toRound * 100.0) / 100.0;
    }

    /**
     * Finds the greatest common factor of Delta-y and Delta-x.
     * 
     * <p>
     * Special Case: If (xDelta % 1 != 0 || yDelta % 1 != 0): return 1,
     * the value given is not integer.
     * <p\>
     * 
     * @return an integer, the gcf of delta-x and delta-y.
     */
    public int gcf() {
        if (xDelta % 1 != 0 || yDelta % 1 != 0) {
            return 1;
        }
        // absH = Absolute Value of Highest Number
        // absL = Absolute Value of Lowest Number
        int absH = Math.max((int) Math.abs(xDelta), (int) Math.abs(yDelta));
        int absL = Math.min((int) Math.abs(xDelta), (int) Math.abs(yDelta));
        int lcm = absH;
        // Finds the lcm of xDelta and yDelta
        while (lcm % absL != 0) {
            lcm += absH;
        }
        // * gcf = (a × b)/ LCM (a, b)
        return (absH * absL) / lcm;
    }

    /**
     * Returns a string that includes all information about the linear equation,
     * each on separate lines.
     * 
     * @return a string; all the information about the line in a formatted message.
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