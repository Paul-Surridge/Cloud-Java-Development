import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

class Singleton implements Serializable
{
    private int iValue;
    
    //Creating private static object of this class ensures that only x1 of these are created throughout the whole program.
    //Whereby providing access methods in order to interact with it.
    private static Singleton SingletonInstance;
    
    //Ensure that the constructor is set to private to ensure/prevent people from creating multiple instances.
    //However should it be the case as shown below that the constructor may throw an exception it is necessary for any call of the constructor to be within a Try/Catch block.
    //Such that the static block below is required in order to instantiate the object.
    private Singleton() throws IOException
    {
        System.out.println("Singleton Initialisation");
        Files.createTempFile(".", ".");
    }

    //Static block is run once when the class is loaded.
    //This is effectively a 'static constructor'.
    static
    {
        try
        {
            SingletonInstance = new Singleton();
        }
        catch (Exception e)
        {
            System.err.println("Unable to instantiate the singleton instance");
        }
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
    
    protected Object readResolve()
    {
        return SingletonInstance;
    }
}
public class App {

    /*
        - It maybe the case that your Singleton constructor may throw an exception.
        - In which case the x1 private static final Singleton instance can not be created/used given that the constructor throws an exception.
        - A try/catch is required in order to handle the potential Exception
    */
    public static void main(String[] args) {
        //Code application logic
    }
}
