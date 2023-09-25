import java.util.HashMap;

/*
    Multiton

        - An extension of the idea and basic concept of a Singleton.
        - Where the Singleton is to be x1 instance in whole program.
        - The Multiton is to be a fixed number of Singletons i.e. that only say x3 Singleton objects can be created.
        - The Multiton is implemented by using a HashMap<> or similar in order to check the number of objects created.
        - The Multiton could be considered a Key, Value store with lazy loading.
*/

enum Systems
{
    PRIMARY,
    SECONDARY,
    AUXILLARY
}
class Printer
{
    private static int iPrinterCount;
    private static HashMap<Systems, Printer> SystemInstances = new HashMap<>();
    
    private Printer()
    {
        //Private constructor to ensure that the Printers can be created at will from outside the class.
        //The constructor is of course called when the Printer objects are created below as when required.
        iPrinterCount++;
        System.out.println("A total of " + iPrinterCount + " printers created so far");
    }
    
    public static Printer get(Systems ss)
    {
        //Use lazy initialisation/loading of printers i.e. only create one if needed.
        
        //Check to see if the value of the enum passed to get already contains a key i.e. already had a Printer assigned to it.
        //If it has just return the Printer object for that system
        if(SystemInstances.containsKey(ss))
            return SystemInstances.get(ss);
        
        //If no Printer exists for that value of enum then create a new one, put it in the HashMap<> and send back.
        Printer objPrinter = new Printer();
        SystemInstances.put(ss, objPrinter);
        
        return objPrinter;
    }
}
public class App {

    public static void main(String[] args)
    {
        Printer priPrinter = Printer.get(Systems.PRIMARY);
        Printer secPrinter = Printer.get(Systems.SECONDARY);
        Printer auxPrinter = Printer.get(Systems.AUXILLARY);
        Printer aux2Printer = Printer.get(Systems.AUXILLARY);
        
        //When run console displays only x3 Printers being created (rather than x4) given that when an enum with value of .AUXILLARY is passed for the second time.
        //it does not create a second .AUXILLARY printer.
        
        //Integer or String could have equally been deployed in place of an enum if preferred.
    }
}
