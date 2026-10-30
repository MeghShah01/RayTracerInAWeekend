package Core.Math;

public record Ray(Vector3D origin, Vector3D direction)
{
    public Vector3D at(double t)
    {
        return origin.add(direction.multiply(t));
    }

    @Override
    public Vector3D origin()
    {
        return new Vector3D(this.origin);
    }

    @Override
    public Vector3D direction()
    {
        return new Vector3D(this.direction);
    }
}
