import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
    Flyweight

    - Use, deploy an algorithmic approach to reducing resource usage (space optimisation).
    - Avoid redundancy when storing data e.g. similar to compression in JPEG but with data.
    - Example:

        - Multiple common first/last name in username fields.
        - There may be large number of 'Johns' and 'Smith' (or names which contain x1 of those names).
        - Rather than allocate space for each name taking x1 byte per character.
        - Use a reference to a look-up table, whereby the reference stored is only x4 bytes i.e. an int.
    
    - Overall:
    
        - A space optimisation technique that enables the use of less memory by storing externally the common/most frequent data associated with similar objects.
*/
class User
{
    private String sFullName;

    public User(String sFullName) {
        this.sFullName = sFullName;
    }
}
class UserFlyweight
{
    static List<String> sNamesList = new ArrayList<>();
    
    private int[] iNamesArray;
    
    public UserFlyweight(String sFullname)
    {
        /*
            In this case it is just a convenient method of being able to pass the function in the mapToInt() method below.
        
            - The below sFullname is passed to constructor.
            - .splt(" "):           It is split into x2 names.
            - getOrAdd.apply(s):    
        */

        iNamesArray = Arrays.stream(sFullname.split(" "))
                .mapToInt(s -> getOrAdd.apply(s))
                .toArray();
        
        System.out.println("iNamesArray Length: " + iNamesArray.length);
        for(int i = 0 ; i<sNamesList.size() ; i++)
            System.out.println(sNamesList.get(i));
    }
    
    /*
        Function<T,R>: 

            - An interface that represents and encapsulates a single function.
            - It takes a String as parameter and returns an Integer.
            - Lambda expression forms function body.
            - This function can be called using .apply(String).
            - This function can be passed, copied and all same operations as an object.
    */
    Function<String, Integer> getOrAdd = (String s) ->
    {
        int iIndex = sNamesList.indexOf(s);

        if(iIndex != -1)    return iIndex;      //The name already exists in the ArrayList sName.
        else
        {
            sNamesList.add(s);
            return sNamesList.size() - 1;       //The name does not exist in the ArrayList therefore it is added to end and returned
        }
    };
}
public class App {

    public static void main(String[] args)
    {
        //These users will be stored fully as the names shown.
        User objUser1 = new User("John Smith");
        User objUser2 = new User("Jane Smith");
        
        //These Flyweight Users will store their names a lot more efficiently
        UserFlyweight objUserUserFlyweight1 = new UserFlyweight("John Smith");
        UserFlyweight objUserUserFlyweight2 = new UserFlyweight("Jane Smith");
    }
}
