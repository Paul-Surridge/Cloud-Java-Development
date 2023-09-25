import java.util.Map;
import java.util.Set;

public class App{

    public static void main(String[] args) {

        //System.arraycopy()
        
            int i0 = 0;
            TestObject objTestObject1 = new TestObject(1);

            int[] intArray          = new int[]{i0,1,2,3,4,5,6,7,8,9};
            int[] intTargetArray    = new int[10];

            TestObject[] objArray       = new TestObject[]{objTestObject1, new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5)};
            TestObject[] objTargetArray = new TestObject[10];

            System.arraycopy(intArray, 2, intTargetArray, 5, 4);                //Integer[]     = [0,0,0,0,0,2,3,4,5,0]         Return the specified range from the source array, can be an array of anything, method parameters are Object.
            printInts(intTargetArray);

            System.arraycopy(objArray, 0, objTargetArray, 0, objArray.length);  //TestObject[]  = [Test Object:1,Test Object:2,Test Object:3,Test Object:4,Test Object:5,0,0,0,0,0].
            printTestObjects(objTargetArray);

            //Non-Primitives Elements - Copies Reference Only

            i0 = 10;                            //Primitve arrays are truely 'copied' and no link remains with the original array elements.
            printInts(intTargetArray);

            objTestObject1.setValue(10);        //Non-Primitive arrays copy only the references, therefore any subsequent changes to any of the elements in the original array will be reflected in the target array.                                     
            printTestObjects(objTargetArray);
        
        //System.getenv:    Environment Variables
        
            printMap(System.getenv());
    }
    private static void printlnBreak(String sTitle)
    {
        System.out.println("\n" + sTitle + ": - - - - - - - - - - - - - - - - - - - - - - - - -");
    }
    public static void printLn(String s)
    {
        System.out.println(s);
    }
    public static void printLn(char c)
    {
        System.out.println(c);
    }
    public static void printLn(int i)
    {
        System.out.println(i);
    }
    public static void printLn(boolean b)
    {
        System.out.println(b);
    }
    public static void printMap(Map<String, String> m)
    {
        Set<String> Keys = m.keySet();
        String sBuild;
        
        for(String s : Keys)
        {
            sBuild = "[" + s + ", " + (String)m.get(s) + "]";
            System.out.println(sBuild);
        }
    }
    public static void printBytes(byte[] b)
    {
        String sBuild = "[";
        for(int i = 0 ; i<b.length ; i++)
            sBuild += b[i] + ",";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
    public static void printInts(int[] a)
    {
        String sBuild = "[";
        for(int i = 0 ; i<a.length ; i++)
            sBuild += a[i] + ",";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
    public static void printChars(char[] c)
    {
        String sBuild = "[";
        for(int i = 0 ; i<c.length ; i++)
            sBuild += c[i] + ",";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
    public static void printStrings(String[] s)
    {
        String sBuild = "[";
        for(int i = 0 ; i<s.length ; i++)
            sBuild += s[i] + ",";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
    public static void printCharSequence(CharSequence cs)
    {
        String sBuild = "[";
        for(int i = 0 ; i<cs.length() ; i++)
            sBuild += cs.charAt(i) + ",";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
    public static void printTestObjects(TestObject[] o)
    {
        String sBuild = "[";
        for(int i = 0 ; i<o.length ; i++)
            if(o[i] != null)    sBuild += o[i].toString() + ",";
            else                sBuild += "0,";
        sBuild = sBuild.substring(0, sBuild.length()-1) + "]";
        
        System.out.println(sBuild);
    }
}
