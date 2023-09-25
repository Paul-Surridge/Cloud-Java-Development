import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Composite:

        - The purpose is to enable both aggregate/composite objects and individual/scalar objects in the same way uniformly.
        - Composite uses and implements 'Compound Objects' which are simply objects composed of other objects.
*/
class GraphicObject
{
    public      List<GraphicObject> arrChildren = new ArrayList<>();
    
    public      String sName    = "Group";
    protected   String sColour;

    //No constructor provided so will be created using the standard default public constructor

    public String getName() {
        return sName;
    }
    public void setName(String sName) {
        this.sName = sName;
    }
    
    private void print(StringBuilder objStringBuilder, int iDepth)
    {
        objStringBuilder.append(String.join("", Collections.nCopies(iDepth, "*")))
        .append(iDepth > 0 ? " " : "")
        .append((sColour == null || sColour.isEmpty()) ? "" : sColour + " ")
        .append(getName())
        .append(System.lineSeparator());
        
        for (GraphicObject child : arrChildren)
            child.print(objStringBuilder,  iDepth+1);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject
{
  public Circle(String sColour)
  {
    sName = "Circle";
    this.sColour = sColour;
  }
}

class Square extends GraphicObject
{
  public Square(String sColour)
  {
    sName = "Square";
    this.sColour = sColour;
  }
}

public class App
{
    public static void main(String[] args)
    {
        //GraphicObject is a container as well as a single/scalar object.
        //GraphicObject can be placed within other GraphicObjects.
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.arrChildren.add(new Square("Red"));
        drawing.arrChildren.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.arrChildren.add(new Circle("Blue"));
        group.arrChildren.add(new Square("Blue"));
        drawing.arrChildren.add(group);

        System.out.println(drawing);
    }
}
