package Core.Object;
import Core.Math.*;
public class HitRecord
{
    private Vector3D positionVec, normalVec;

    private double t;

    private boolean frontFace;

    public HitRecord ()
    {
        this.positionVec = new Vector3D();

        this.normalVec = new Vector3D();

        this.t = 0.0;

        this.frontFace = false;
    }

    public void setFaceNormal (final Ray ray, final Vector3D outwardNormal)
    {
        this.frontFace = Vector3D.dotProduct(ray.direction(), outwardNormal) < 0;

        this.normalVec = frontFace ? outwardNormal : outwardNormal.negate();
    }

    public void copy (HitRecord hitRecord)
    {
        this.positionVec = hitRecord.getPositionVec();
        this.normalVec = hitRecord.getNormalVec();
        this.t = hitRecord.getT();
        this.frontFace = hitRecord.isFrontFace();
    }

    public Vector3D getPositionVec()
    {
        return positionVec;
    }

    public void setPositionVec(Vector3D positionVec)
    {
        this.positionVec = positionVec;
    }

    public Vector3D getNormalVec()
    {
        return normalVec;
    }

    public void setNormalVec(Vector3D normalVec)
    {
        this.normalVec = normalVec;
    }

    public double getT()
    {
        return t;
    }

    public void setT(double t)
    {
        this.t = t;
    }

    public boolean isFrontFace()
    {
        return frontFace;
    }
}
