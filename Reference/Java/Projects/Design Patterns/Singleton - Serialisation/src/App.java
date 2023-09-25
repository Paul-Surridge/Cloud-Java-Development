import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Singleton implements Serializable
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
    
    protected Object readResolve()
    {
        return SingletonInstance;
    }
}
public class App {

    //Example:  Serialisation
    //          The below x2 methods write out and read back the serialised Singleton to the supplied filename
    //          Illustrating that multiple Singleton instances can be inadvertently created.
    static void saveToFile(Singleton objSingleton, String sFilename) throws Exception
    {
        //Utilise the Try-With-Resources paradigm, whereby the resources will be automatically closed once the try block has finished
        try(FileOutputStream objFileOutputStream        = new FileOutputStream(sFilename);
            ObjectOutputStream objObjectOutputStream    = new ObjectOutputStream(objFileOutputStream))
        {
            objObjectOutputStream.writeObject(objSingleton);
        }
    }
    static Singleton readFromFile(String sFilename) throws Exception
    {
        //Utilise the Try-With-Resources paradigm, whereby the resources will be automatically closed once the try block has finished
        try(FileInputStream objFileInputStream        = new FileInputStream(sFilename);
            ObjectInputStream objObjectInputStream    = new ObjectInputStream(objFileInputStream))
        {
            return (Singleton) objObjectInputStream.readObject();
        }
    }

    /*
        It is possible to potentially get around and 'break the contract' of the singleton by creating multiple instances of the Singleton by the following means:
    
            1. Reflection:      It is possible to use Reflection to gain access to the private constructor to make it effectively public.
            2. Serialisation:   Write out and read back the instance to a new reference variable (unless readResolve() is added to the Singleton).
                                Adding readResolve() to the Singleton and having it return the original static instance prevents multiple instances of the Singleton.
     */
    public static void main(String[] args) throws Exception
    {
        Singleton objSingleton1, objSingleton2;
        String sFilename;
        
        //Obtain a reference to the x1 static instance of the Singleton.
        objSingleton1 = Singleton.getInstance();
        
        //Use/employ functions as normal.
        objSingleton1.setValue(123);
        //The Singleton file is placed into the project folder if path not defined.
        sFilename = "Singleton.bin";
        saveToFile(objSingleton1, sFilename);
        
        objSingleton1.setValue(456);
        objSingleton2 = readFromFile(sFilename);
        
        //Evaluate whether the x2 instances are indeed the same or x2 distinct instances.
        //When run it can be shown that they are indeed distinct i.e. we now have x2 instances, when there should only be x1 per program.
        System.out.println(objSingleton1 == objSingleton2);
        System.out.println("objSingleton1: " + objSingleton1.getValue());
        System.out.println("objSingleton2: " + objSingleton2.getValue());
        
        //The above problem is overcome by implementing readResolve() in the Serializable object.
    }
}
