/*
    Bridge:

        - Purpose is to help minimise the number of potential combination of class from a set of criteria.
        - Commonly know as a 'Cartesian Product Explosion' whereby have e.g. 3 versions of 4 types leading to x12 classes.
        - Connecting components through abstractions.
        - Bridge patterns avoids this entity explosion.
        - A mechanism that decouples an interface (hierarchy) from an implementation (hierarchy).
        - It prevents a large cumbersome class hierarchy from evolving via a combination of inheritance and aggregation.
        - Given the 'x2' dimensions of the Cartesian Product Explosion, a 'Bridge' design pattern sets one of the dimensions as an interface which is passed to the object when created
        - Presumably if the product matrix was x3 dimensional then x2 of those dimensions would be passed as interfaces.
        - Shape (Base Class) -> Circle, Square
        - Render (Bass Class) -> Vector, Raster

    - Vector:

        - The shape is represented via points, lines and mathematical formulae.
        - The shape can be easily scaled and does not lose resolution.

    - Raster:

        - The shape is converted into bitmap of the pixels.
        - The shape when scaled loses resolution/detail.

    - The above illustrates and 2x2 cartesian product explosion.
    - This could lead to such classes:

        VectorCircleRenderer, VectorSquareRenderer, RasterCircleRenderer, RasterSquareRenderer

    - It is possible to use a Bridge pattern by placing the 'renderer' within the 'shape'.
*/

interface Renderer
{
    void render(float fRadius);  
}
//x2 classes that represent the x2 possible methods of rendering.
//These implement Renderer interface such that they complete the process or rendering by either Vector and Raster.
class VectorRenderer implements Renderer
{
    @Override
    public void render(float fRadius) {
        System.out.println("Vector render a circle of radius: " + fRadius);
    }
}
class RasterRenderer implements Renderer
{
    @Override
    public void render(float fRadius) {
        System.out.println("Raster render a circle of radius: " + fRadius);
    }
}

//Shape forms the base class of all shapes that could be created in program
//It forces the programmer to specify which render method by having the constructor take a Renderer interface as a parameter
abstract class Shape
{
    protected Renderer infRenderer;

    public Shape(Renderer infRenderer) {
        this.infRenderer = infRenderer;
    }
    
    public abstract void draw();
    public abstract void resize(float fFactor);
}

//Implement the derived Circle and Square classes.
//Whereby the use of the interface infRenderer allows Circle to be rendered by whatever means as specified by what object is assigned to the passed interface.
class Circle extends Shape
{
    public float fRadius;
    
    public Circle(Renderer infRenderer) {
        super(infRenderer);
    }
    public Circle(Renderer infRenderer, float fRadius) {
        super(infRenderer);
        this.fRadius = fRadius;
    }
    
    @Override
    public void draw() {
        infRenderer.render(fRadius);
    }

    @Override
    public void resize(float fFactor) {
        infRenderer.render(fRadius * fFactor);
    }
}

public class App {

    public static void main(String[] args) {
        
        RasterRenderer objRasterRenderer = new RasterRenderer();
        VectorRenderer objVectorRenderer = new VectorRenderer();
        
        //Create a circle which is passed a VectorRenderer object which implements the interface Renderer.
        //This will cause the circle to be rendered using a vector.
        //When the circle is drawn the interface will delegate to the VectorRenderer implementation of render().
        Circle objCircle = new Circle(objVectorRenderer, 5);
        
        objCircle.draw();
        objCircle.resize(2);
        objCircle.draw();
    }
}
