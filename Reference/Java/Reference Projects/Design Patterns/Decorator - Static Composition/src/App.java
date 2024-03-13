import java.util.function.Supplier;

interface Shape
{
  String info();
}

class Circle implements Shape
{
  private float fRadius;

  public Circle(float fRadius)
  {
    this.fRadius = fRadius;
  }

  void resize(float fFactor)
  {
    fRadius *= fFactor;
  }

  @Override
  public String info()
  {
    return "A circle of radius " + fRadius;
  }
}

class Square implements Shape
{
  private float fSide;

  public Square(float fSide)
  {
    this.fSide = fSide;
  }

  @Override
  public String info()
  {
    return "A square with side " + fSide;
  }
}

//The below x2 classes are Static Decorators.
class ColouredShape<T extends Shape> implements Shape
{
    private Shape   objShape;
    private String  sColour;
    
    public ColouredShape(Supplier<? extends T> objConstructor, String sColour)
    {
        objShape        = objConstructor.get();
        this.sColour    = sColour;
    }
            
    @Override
    public String info() {
        return objShape.info() + " has the colour " + sColour;
    }
}
class TransparentShape<T extends Shape> implements Shape
{
    private Shape   objShape;
    private int     iTransparency;
    
    public TransparentShape(Supplier<? extends T> objConstructor, int iTransparency)
    {
        objShape            = objConstructor.get();
        this.iTransparency  = iTransparency;
    }
            
    @Override
    public String info() {
        return objShape.info() + " has the transparency " + iTransparency;
    }
}
public class App
{
    public static void main(String[] args)
    {
        //The below declare the types at compile time rather than runtime, therefore are known as Static Decorators.
        //ColouredShape is passed a lambda which takes no arguments and returns a new Square of side 20.
        ColouredShape<Square>                   objBlueSquare   = new ColouredShape<>   ( () -> new Square(20), "blue" );
        
        TransparentShape<ColouredShape<Circle>> objCircle       = new TransparentShape<>( () -> new ColouredShape<>( () -> new Circle(5), "green"), 50);
        
        System.out.println(objBlueSquare.info());
        System.out.println(objCircle.info());
    }
}
