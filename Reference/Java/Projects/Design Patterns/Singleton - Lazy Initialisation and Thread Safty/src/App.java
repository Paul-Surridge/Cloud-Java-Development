import java.io.Serializable;

/*
    Lazy Initialisation:

        - Implemented for performance purposes.
        - Objects are only created when actually needed, rather than statically or within a constructor
*/

class Singleton implements Serializable
{
    //Creating private static object of this class ensures that only x1 of these are created throughout the whole program.
    //Whereby providing access methods in order to interact with it.
    private static Singleton SingletonInstance;
    
    //Ensure that the constructor is set to private to ensure/prevent people from creating multiple instances.
    private Singleton()
    {
        System.out.println("Singleton Initialisation");
    }
    
    //The below 'exposes' the x1 Singleton object to the outside world, ensuring that only x1 reference of the Singleton is created and used in the whole program.
    //The SingletonInstance is only created when it is actually needed and if one does not already exist.
    
//    public static Singleton getInstance()
//    {
//        if(SingletonInstance == null)
//        {
//            SingletonInstance = new Singleton();
//        }
//        
//        return SingletonInstance;
//    }
    
    //However this may potentially lead to a race condition if multiple threads call getInstance() at same time and lead to multiple calls to constructor.
    //The constructor maybe 'sensitive' to multiple calls.
    //Therefore this needs to be handled via synchronisation.
    
//    public static synchronized Singleton getInstance()
//    {
//        if(SingletonInstance == null)
//        {
//            SingletonInstance = new Singleton();
//        }
//        
//        return SingletonInstance;
//    }
    
    //However this will reduce performance such that it can be further improved using a double check lock.
    //Double locking is an outdated approach but should be aware nonetheless.
    //The 'double lock' is as shown below whereby the instance is checked twice, the latter once in the synchronised block.
    //This will now be thread safe
    
//    public static Singleton getInstance()
//    {
//        if(SingletonInstance == null)
//        {
//            //Utilise the lock on the Singleton.class
//            synchronized(Singleton.class)
//            {
//                if(SingletonInstance == null)
//                {
//                    SingletonInstance = new Singleton();
//                }
//            }
//        }
//        
//        return SingletonInstance;
//    }
    
    //This can be improved further by using an inner static class as shown.
    //This will always ensure that only x1 instance is ever created and can be return regardless of the number of threads.
    //The below is a lot simpler, cleaner to implement without needing to use synchronized().
    private static class Impl
    {
        private static final Singleton SingletonInstance = new Singleton();
    }
    
    public static Singleton getInstance()
    {
        return Impl.SingletonInstance;
    }
}
public class App {

    public static void main(String[] args)
    {
        //Code application logic
    }
}
