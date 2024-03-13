import java.util.ArrayList;
import java.util.List;

/*
    Facade

        - A simple design pattern.
        - Exposing several components through a single interface.
        - Whereby a complex and elaborate system has a nice user-friendly, minimised, safe API placed over it.
        - Primarily to keep internals safe, increase simplicity and reduce potential for error.
        - With option to allow 'Power Users' the ability to have exposure to more complex API (with the appropriate warnings...
*/

class Buffer
{
    //The Buffer receives large amount of information from the stock market.
    private char[]  arrCharacters;
    private int     iLineWidth;
    
    //The data from the stock market comes in a stream.
    //It is necessary to have this information display on a x2 dimensional console window.
    //It is necessary to split the incoming data into lines as required using a designated line width.
    public Buffer(int iLineHeight, int iLineWidth)
    {
        this.iLineWidth = iLineWidth;
        arrCharacters = new char[iLineWidth * iLineWidth];
    }
    
    //It is useful to have an ability to obtain a character at a particular position on the console window.
    //Therefore an x, y are passed as arguments to get a particular char.
    //It is necessary to map these x2 coordinates onto the x1 dimensional stream/array.
    public char charAt(int x, int y)
    {
        return arrCharacters[(y * iLineWidth) + x];
    }
}

class Viewport
{
    //Viewport used to format and display section of buffer.
    
    private final Buffer    objBuffer;
    private final int       iWdith;
    private final int       iHeight;
    private final int       iOffsetX;
    private final int       iOffsetY;

    //Offset is the particular portion of the buffer that wish to view i.e. the portion that needs to be displayed onscreen.
    public Viewport(Buffer objBuffer, int iWdith, int iHeight, int iOffsetX, int iOffsetY) {
        this.objBuffer  = objBuffer;
        this.iWdith     = iWdith;
        this.iHeight    = iHeight;
        this.iOffsetX   = iOffsetX;
        this.iOffsetY   = iOffsetY;
    }
    
    //Retrieve a certain character at a particular location within the x2 dimensional displayed window.
    public char charAt(int iX, int iY)
    {
        return objBuffer.charAt((iX + iOffsetX), (iY + iOffsetY));
    }
}

//A console make take multiple viewports and display them over multiple monitors.
class Console
{
    private List<Viewport> arrViewports = new ArrayList<>();
    int iWidth, iHeight;

    public Console(int iWidth, int iHeight) {
        this.iWidth = iWidth;
        this.iHeight = iHeight;
    }
    
    public void addViewport(Viewport objViewport)
    {
        arrViewports.add(objViewport);
    }
    
    //Render all viewports onto the monitors
    public void render()
    {
        for(int y = 0 ; y < iHeight ; ++y)
        {
            for(int x = 0 ; x < iHeight ; ++x)
            {
                for(Viewport vp: arrViewports)
                    System.out.print(vp.charAt(x, y));
            }
            System.out.println();
        }
    }
    
    //Factory method (which employs teh Facade design pattern) for the creation of consoles.
    public static Console newConsole(int iWidth, int iHeight)
    {
        Buffer      objBuffer   = new Buffer(iWidth, iHeight);
        Viewport    objViewport = new Viewport(objBuffer, iWidth, iHeight, 0, 0);
        Console     objConsole  = new Console(iWidth, iHeight);
        
        objConsole.addViewport(objViewport);
        objConsole.render();
        return objConsole;
    }
}

//Of course the user does not wish to know or manually have to create buffers or viewports, they just 'wish it to work'.
//Therefore a simple API is presented/exposed from the Console.
public class App {

    public static void main(String[] args) {
        
        /*
            - Rather than place all low level aspects below for the programmer to have to implement manually.
            - They are just placed within Console under x1 factory method newConsole() shown above.
        
        Buffer      objBuffer   = new Buffer(30, 20);
        Viewport    objViewport = new Viewport(objBuffer, 30, 20, 0, 0);
        Console     objConsole  = new Console(30, 20);
        
        objConsole.addViewport(objViewport);
        objConsole.render();
        */
        
        //Simplified creation of console.
        Console objConsole = Console.newConsole(30, 20);
        objConsole.render();
    }
}
