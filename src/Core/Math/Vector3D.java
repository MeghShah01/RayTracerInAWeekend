package Core.Math;

import java.util.Random;

public class Vector3D implements Vector
{
    private final double[] values;
    private static final Random RANDOM = new Random();

    public Vector3D()
    {
        values = new double[]{0.0, 0.0, 0.0};
    }

    public Vector3D(double value1, double value2, double value3)
    {
        values = new double[]{value1, value2, value3};
    }

    public Vector3D(Vector vector)
    {
        this.values = new double[]{
                vector.getX(),
                vector.getY(),
                vector.getZ()
        };
    }

    public Vector3D negate()
    {
        return new Vector3D(
                -this.values[0],
                -this.values[1],
                -this.values[2]
        );
    }

    public Vector3D multiply(double t)
    {
        return new Vector3D(
                this.values[0] * t,
                this.values[1] * t,
                this.values[2] * t
        );
    }

    public Vector3D add(Vector vector)
    {
        return new Vector3D(
                this.values[0] + vector.getX(),
                this.values[1] + vector.getY(),
                this.values[2] + vector.getZ()
        );
    }

    public Vector3D subtract(Vector vector)
    {
        return new Vector3D(
                this.values[0] - vector.getX(),
                this.values[1] - vector.getY(),
                this.values[2] - vector.getZ()
        );
    }

    public Vector3D divide(double t)
    {
        return this.multiply(1.0 / t);
    }

    public double getMagnitude()
    {
        return Math.sqrt(getMagnitudeSquared());
    }

    public double getMagnitudeSquared()
    {
        return this.values[0] * this.values[0] +
                this.values[1] * this.values[1] +
                this.values[2] * this.values[2];
    }

    public Vector3D normalize()
    {
        double magnitude = getMagnitude();

        return magnitude == 0
                ? new Vector3D()
                : this.divide(magnitude);
    }

    public static Vector3D add(Vector u, Vector v)
    {
        return new Vector3D(
                u.getX() + v.getX(),
                u.getY() + v.getY(),
                u.getZ() + v.getZ()
        );
    }

    public static Vector3D subtract(Vector u, Vector v)
    {
        return new Vector3D(
                u.getX() - v.getX(),
                u.getY() - v.getY(),
                u.getZ() - v.getZ()
        );
    }

    public static Vector3D multiply(Vector u, Vector v)
    {
        return new Vector3D(
                u.getX() * v.getX(),
                u.getY() * v.getY(),
                u.getZ() * v.getZ()
        );
    }

    public static Vector3D multiply(double t, Vector v)
    {
        return new Vector3D(
                t * v.getX(),
                t * v.getY(),
                t * v.getZ()
        );
    }

    public static Vector3D divide(Vector v, double t)
    {
        return Vector3D.multiply(1.0 / t, v);
    }

    public static double dotProduct(Vector u, Vector v)
    {
        return u.getX() * v.getX() +
                u.getY() * v.getY() +
                u.getZ() * v.getZ();
    }

    public static Vector3D crossProduct(Vector u, Vector v)
    {
        return new Vector3D(
                u.getY() * v.getZ() - u.getZ() * v.getY(),
                u.getZ() * v.getX() - u.getX() * v.getZ(),
                u.getX() * v.getY() - u.getY() * v.getX()
        );
    }

    public static Vector3D random ()
    {
        return new Vector3D(RANDOM.nextDouble(), RANDOM.nextDouble(), RANDOM.nextDouble());
    }

    public static Vector3D random (double min, double max)
    {
        double  value1 = (RANDOM.nextDouble()*(max-min))+min,
                value2 = (RANDOM.nextDouble()*(max-min))+min,
                value3 = (RANDOM.nextDouble()*(max-min))+min;

        return new Vector3D(value1, value2, value3);
    }

    public static Vector3D getUnitVector(Vector v)
    {
        return new Vector3D(v).normalize();
    }

    public static Vector3D getRandomUnitVector ()
    {
        while (true)
        {
            Vector3D point = Vector3D.random(-1, 1);
            double magSquared = point.getMagnitudeSquared();
            if (1e-160 <= magSquared && magSquared <= 1)
            {
                return point.divide(point.getMagnitude());
            }
        }
    }

    public static Vector3D getRandomVectorOnHemisphere (final Vector3D normal)
    {
        Vector3D onUnitSphere = getRandomUnitVector();
        if (dotProduct(onUnitSphere, normal) > 0.0)
        {
            return onUnitSphere;
        }
        else
        {
            return onUnitSphere.negate();
        }
    }

    @Override
    public double getX()
    {
        return this.values[0];
    }

    @Override
    public double getY()
    {
        return this.values[1];
    }

    @Override
    public double getZ()
    {
        return this.values[2];
    }

    public double getValue(int index)
    {
        if (index >= 0 && index < this.values.length)
        {
            return this.values[index];
        }

        throw new IndexOutOfBoundsException(
                "Error:\nIndex: " + index +
                        " is either less than 0 or greater than " +
                        (this.values.length - 1)
        );
    }

    @Override
    public String toString()
    {
        return "Core.Math.Vector3D:{" +
                this.values[0] + ", " +
                this.values[1] + ", " +
                this.values[2] +
                "}";
    }
}