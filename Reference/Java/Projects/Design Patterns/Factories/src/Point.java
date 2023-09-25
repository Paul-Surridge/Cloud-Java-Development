public class Point {
    
    double x, y;

    private Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    //NB: The inner class must be static given that it has static methods.
    public static class Factory
    {
        public static Point newCartesianPoint(double x, double y)
        {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theata)
        {
            return new Point(rho*Math.cos(theata), rho*Math.sin(theata));
        }
    }
}