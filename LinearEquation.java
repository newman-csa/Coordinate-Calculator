public class LinearEquation {
    /* Instance Variables */
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double xDelta = x2 - x1;
    private double yDelta = y2 - y1;

    /* Creates a LinearEquation object */
    /*
     * PRECONDITION: x1 and x2 are NOT equal (client programs are responsible for
     * ensuring
     * this precondition is not violated)
     * public LinearEquation(int x1, int y1, int x2, int y2)
     * 
     * 
     * /* Calculates and returns distance between (x1, y1) and (x2, y2), rounded to
     * the nearest hundredth
     */
    public double distance() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
     * Calculates and returns the y-intercept of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth
     */
    public double yIntercept() {
        return x1 * slope() - y1;
    }

    /*
     * Calculates and returns the slope of the line between (x1, y1) and
     * (x2, y2), rounded to the nearest hundredth
     */
    public double slope() {
        return (y2 - y1) / (x2 - x1);
    }

    /*
     * Returns a String that represents the linear equation of the line through
     * points
     * (x1, y1) and (x2, y2) in slope-intercept (y = mx + b) form, e.g.
     * "y = 3x + 1.5".
     * 
     * When generating the m value (slope), here are examples of "printable" slopes:
     * 5, -5, 1/2, 6/8 (reducing not required), 8/5, -2/3, -18/7
     * 
     * Here are non-examples of "printable" slopes:
     * 1/-2 (should be -1/2), -4/-3 (should be 4/3), 8/4 (should be reduced to 2),
     * -12/3 (should be -4), 3/3 (should be 1), -6/6 (should be -1)
     * 
     * HINT: Be sure to check if the line is horizontal and return an appropriate
     * string,
     * e.g. y = 6
     * HINT: Absolute value might be helpful for ensuring proper placement of
     * negative sign!
     * 
     * 
     * When generating the b value, here are some examples of "printable"
     * y-intercepts:
     * + 1.0 - 2.35 + 12.5 - 8.0 + 17.19
     * 
     * Here are non-examples of "printable" y-intercepts:
     * - -1.0 + -2.35 - -12.5 + -8.0 - -17.19 + 0 - 0
     * 
     * HINT: Absolute value might be helpful for printing negative y-intercepts as
     * subtraction!
     */
    public String equation() {
        return "y = " + yDelta / gcf();
    }

    /*
     * Returns a String of the coordinate point on the line that has the given x
     * value, with
     * both x and y coordinates as decimals to the nearest hundredth, e.g (-5.0,
     * 6.75)
     */
    public String coordinateForX(double xValue) {
        return "(" + xValue + ", " + (xValue * slope() + yIntercept()) + ")";
    }

    /*
     * "Helper" method for use elsewhere in your methods; returns the value toRound
     * rounded
     * to the nearest hundredth
     * 
     * HINT: the Math.round method can help with this!
     */
    public double roundedToHundredth(double toRound) {
        return Math.round(toRound) * 100;
    }

    public int gcf() {
        if ((y2 - y1) <= 0 || (x2 - x1) <= 0) {
            return 0;
        }

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
     * each on
     * separate lines:
     * - The original points: (x1, y1) and (x2, y2)
     * - The equation of the line in y = mx + b format (using equation() method)
     * - The slope of the line, as a decimal (using slope() method)
     * - The y-intercept of the line (using yIntercept() method)
     * - The distance between the two points (using distance() method)
     * 
     * This method should call all other appropriate methods to get the info it
     * needs:
     * equation(), slope(), yIntercept(), distance().
     * 
     */
    public String lineInfo() {
        String str =
        "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n" + //
        "The slope of this line is: " + slope() + "\n" + //
        "The y-intercept of the line is: " + yIntercept() + "\n" + //
        "The distance between the two points is: " + distance(); //
        return str;
    }
}
