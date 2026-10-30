package Core.Object;
import Core.Math.*;

public class Sphere implements Hittable
{
    private Vector3D center;

    private double radius;

    public Sphere (final Vector3D center, double radius)
    {
        this.center = center;
        this.radius = Math.max(0, radius);
    }

    @Override
    public boolean hit(Ray ray, Interval rayT, HitRecord hitRecord)
    {
        Vector3D rayToSphereDistance = Vector3D.subtract(center, ray.origin());

        double a = ray.direction().getMagnitudeSquared();

        double h = Vector3D.dotProduct(ray.direction(), rayToSphereDistance);

        double c = rayToSphereDistance.getMagnitudeSquared() - radius*radius;

        double discriminant = h*h - a*c;

        if (discriminant < 0)
        {
            return false;
        }

        double sqrtDiscriminant = Math.sqrt(discriminant);

        double root = (h - sqrtDiscriminant)/a;

        if (root <= rayT.min() || rayT.max() <= root)
        {
            root = (h + sqrtDiscriminant)/a;

            if (root <= rayT.min() || rayT.max() <= root)
            {
                return false;
            }
        }

        hitRecord.setT(root);
        hitRecord.setPositionVec(ray.at(hitRecord.getT()));
        Vector3D outwardNormal = (hitRecord.getPositionVec().subtract(center)).divide(radius);
        hitRecord.setFaceNormal(ray, outwardNormal);

        return true;
    }

    public Vector3D getCenter()
    {
        return center;
    }

    public void setCenter(Vector3D center)
    {
        this.center = center;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }
}
