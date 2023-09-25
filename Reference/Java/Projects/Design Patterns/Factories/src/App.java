public class App {

    public static void main(String[] args) {

        /*
	        - An inner class is used as a Factory in order to build and return the required Point object.
            - The inner class if referenced as shown via .operator.
            - This is in general in order to make the overall construction process a lot easier, clearer and cleaner.
            - The inner class is added to the Point class with the presumption that there is access to the Point class source code.
        */
        Point pointCartesian    = Point.Factory.newCartesianPoint(2, 3);
        Point pointPolar        = Point.Factory.newPolarPoint(2, 3);
    }
}
