package Core.Object;
import Core.Math.*;

public interface Hittable
{
    boolean hit(final Ray ray, Interval rayT, HitRecord hitRecord);
}
