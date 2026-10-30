package Core.Math;

public class Colour implements Vector
{
    private final double r;
    private final double g;
    private final double b;

    public Colour()
    {
        this(0.0, 0.0, 0.0);
    }

    public Colour(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Colour (Colour colour)
    {
        this.r = colour.getX();
        this.g = colour.getY();
        this.b = colour.getZ();
    }

    public Colour (Vector vector)
    {
        this.r = vector.getX();
        this.g = vector.getY();
        this.b = vector.getZ();
    }

    public Colour add(Vector other)
    {
        return new Colour(
                r + other.getX(),
                g + other.getY(),
                b + other.getZ()
        );
    }

    public Colour subtract(Vector other)
    {
        return new Colour(
                r - other.getX(),
                g - other.getY(),
                b - other.getZ()
        );
    }

    public Colour multiply(double t)
    {
        return new Colour(
                r * t,
                g * t,
                b * t
        );
    }

    public Colour divide(double t)
    {
        return multiply(1.0 / t);
    }

    public static double linearToGamma (double linearComponent)
    {
        return linearComponent > 0 ? Math.sqrt(linearComponent) : 0;
    }

    @Override
    public double getX()
    {
        return r;
    }

    @Override
    public double getY()
    {
        return g;
    }

    @Override
    public double getZ()
    {
        return b;
    }

    @Override
    public String toString()
    {
        return "Core.Math.Colour:{" + r + ", " + g + ", " + b + "}";
    }
}