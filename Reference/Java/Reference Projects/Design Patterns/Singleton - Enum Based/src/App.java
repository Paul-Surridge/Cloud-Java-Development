import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*In its simplest forms this is a singleton.
enum Singleton
{
    INSTANCE;
}
However issues arise when trying to include and serialise a more complex enum as shown:
*/
enum Singleton
{
    //All Enumeration Constants are implicitly 'public static final'
    INSTANCE;
    
    //Enums may include constructors, methods and instance variables.
    private int iValue;
    
    //Enum constructors are implicitly private.
    Singleton()
    {
        iValue = 42;
    }
    public int getValue()
    {
        return iValue;
    }
    public void setValue(int iValue)
    {
        this.iValue = iValue;
    }
}
/*Enums are:
 
    - Considered a class.
    - But not instantiated using new but treated a primitive types.
    - Implicitly final.
    - Cannot be extended/subclassed.
    - Implicitly serialisable.  However only the name is serialised but none of the instance variables are, therefore 
*/
public class App {

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
    
    public static void main(String[] args) throws Exception {
        
        Singleton objSingleton1, objSingleton2;
        
        String sFilename = "Singleton.bin";
        
        objSingleton1 = Singleton.INSTANCE; 
        objSingleton1.setValue(111);
        saveToFile(objSingleton1, sFilename);
        
        objSingleton2 = readFromFile(sFilename);
        System.out.println(objSingleton2.getValue());
        
        /*
            Bizarre behaviour occurs above when serialising enums.
            Only their name ever gets serialised and saved to the file, not any of their instance variables.
        
        Such that:
        
            Scenario 1:
        
            1. Program is run.
            2. Enum is created and stored on heap as usual.
            2. Set iValue to 111.
            2. iValue is stored on the heap and set to 111.
            3. Save to file.
        
            4. Read from file.
            5. iValue remains at 111.
        
            Scenario 2:
        
            However if:
        
            1. Program is re-run.
            2. iValue is not set to 111 and not saved to file but enum is just read from file.
            3. Even though the enum in the file had its iValue set to 111, iValue is not actually saved to the file.
            4. Such that upon just reading in the enum from the file it does not contain iValue set to 111 but the contructor is called and initailised to 42.
            5. If lines 81-83 are commented out 42 is returned and sent to console.
        
            It maybe the case that in scenario 1:
        
            1. Enum (and it's iValue) is initially stored/allocated a place on the heap.
            2. When the enum of the same name is read in from file.
            3. Given that the enum is implicitly static and that the enum name already exists, what gets read in from the file is 'overlayed' the existing incarnation.
        */
    }
}
