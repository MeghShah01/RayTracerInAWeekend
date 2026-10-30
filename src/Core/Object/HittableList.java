package Core.Object;

import java.util.ArrayList;
import Core.Math.*;

public class HittableList implements Hittable
{
    ArrayList<Hittable> objects;

    public HittableList ()
    {
        this.objects = new ArrayList<>();
    }

    public HittableList (Hittable object)
    {
        this.objects = new ArrayList<>();

        this.objects.add(object);
    }

    public void clearList ()
    {
        this.objects.clear();
    }

    public void addToList (Hittable object)
    {
        this.objects.add(object);
    }


    @Override
    public boolean hit(Ray ray, Interval rayT, HitRecord hitRecord)
    {
        HitRecord tempHitRecord = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = rayT.max();

        for (Hittable object: objects)
        {
            if (object.hit(ray, new Interval(rayT.min(), closestSoFar), tempHitRecord))
            {
                hitAnything = true;
                closestSoFar = tempHitRecord.getT();
                hitRecord.copy(tempHitRecord);
            }
        }

        return hitAnything;
    }
}
