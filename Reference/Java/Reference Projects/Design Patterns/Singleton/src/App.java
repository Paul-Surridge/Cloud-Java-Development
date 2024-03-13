class Singleton
{
    private int iValue;
    
    //Creating private static final object of this class ensures that only x1 of these are created throughout the whole program.
    //Whereby providing access methods in order to interact with it.
    private static final Singleton SingletonInstance = new Singleton();
    
    //Ensure that the constructor is set to private to ensure/prevent people from creating multiple instances.
    private Singleton()
    {

    }

    //The below 'exposes' the x1 Singleton object to the outside world, ensuring that only x1 reference of the Singleton is created and used in the whole program 
    public static Singleton getInstance()
    {
        return SingletonInstance;
    }

    public int getValue() {
        return iValue;
    }

    public void setValue(int iValue) {
        this.iValue = iValue;
    }
}
public class App {

    /*
        Singleton:
    
        - A class which enforces that there may only be x1 instance of the class throughout the whole program.
        - It should not be possible to make multiple instances/objects.
        - For example a connection to a database.
     */
    public static void main(String[] args)
    {
        //Obtain a reference to the x1 static instance of the Singleton
        Singleton objSingleton = Singleton.getInstance();
        
        //Use/employ functions as normal.
        objSingleton.setValue(123);
        System.out.println("Value: " + objSingleton.getValue());
    }
}
