//Utilise an interface to allow concatenation of properties.
interface Shape
{
  String info();
}
/*
    - Base interface 'Shape'.
    - Rather than extend into a Cartesian Product Explosion of all of the possible combinations of Shape, Colour and Transparency.
    - We just place the base classes (Circle/Square) as a private aggregate variable within a new class (Decorator) which has the required additional functionality e.g. Colour/Transparency.
    - We do not affect the original base classes.
    - It is classed as 'Dynamic' as you can always build up the decorators at runtime.
*/
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

class ColoredShape implements Shape
{
  private Shape     objShape;
  private String    sColor;

  public ColoredShape(Shape objShape, String sColor)
  {
    this.objShape = objShape;
    this.sColor = sColor;
  }

  @Override
  public String info()
  {
    return objShape.info() + " has the color " + sColor;
  }
}

class TransparentShape implements Shape
{
  private Shape     objShape;
  private int       iTransparency;

  public TransparentShape(Shape objShape, int iTransparency)
  {
    this.objShape = objShape;
    this.iTransparency = iTransparency;
  }

  @Override
  public String info()
  {
    return objShape.info() + " has " + iTransparency + "% transparency";
  }
}

public class App
{
    public static void main(String[] args)
    {
        Circle objCircle = new Circle(10);
        System.out.println(objCircle.info());

        //Given that all objects utilise the base interface 'Shape' allows for multi-property objects to be created without the need to create
        //separate, discrete classes for each combination.
        ColoredShape objBlueSquare = new ColoredShape(new Square(20), "blue");
        System.out.println(objBlueSquare.info());

        //The shared interface allows multiple shapes to be 'layered' on top of one another.
        //The constructors of the classes simply do not care about the objects passed as arguments as long as they have the interface 'Shape'.
        TransparentShape objMyCircle = new TransparentShape(new ColoredShape(new Circle(5), "green"), 50);
        System.out.println(objMyCircle.info());
    }
}
