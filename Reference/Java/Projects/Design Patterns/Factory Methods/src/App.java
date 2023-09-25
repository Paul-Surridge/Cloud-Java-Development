public class App
{
    public static void main(String[] args)
    {
        /*
            - Given that it is not possible to overload x2 constructors with the same name e.g. Point(double x, double y) and Point(double rho, double theta).
            - The constructor is hidden via private and x2 static methods of Point are used to build Point objects which correspond to either Cartesian or Polar points.
            - The constructor is hidden via private to ensure that the programmer is forced to use x1 of the factory methods.
        */
        Point pointCartesian    = Point.newCartesianPoint(2, 3);
        Point pointPolar        = Point.newPolarPoint(2, 3);
    }
}
