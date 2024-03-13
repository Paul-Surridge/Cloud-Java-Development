/*
    Monostate

        - Not a particularly useful but sometime misleading design pattern.
        - Whereby given that the x2 members are static i.e. only x1 copy of each regardless of number of objects made.
        - All modifications via get/set will all modify the same variables regardless of how many Monostate objects are created.
        - This may be misleading or not immediately apparent to the programmer that even though multiple references are being created it is actually a singleton.
*/
class Monostate
{
    private static String   sName;
    private static int      iAge;

    public void setAge(int iAge) {
        Monostate.iAge = iAge;
    }
    public void setName(String sName) {
        Monostate.sName = sName;
    }
    public int getAge() {
        return iAge;
    }
    public String getName() {
        return sName;
    }  
}
public class App {

    public static void main(String[] args)
    {
        Monostate objMonostate1 = new Monostate();
        Monostate objMonostate2 = new Monostate();
        Monostate objMonostate3 = new Monostate();
        
        objMonostate1.setAge(1);
        objMonostate2.setAge(2);
        objMonostate3.setAge(3);
        
        System.out.println(objMonostate1.getAge());
    }
}
