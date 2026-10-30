package Core.Rendering;

import Core.Object.*;
import Core.Math.*;
import Core.Rendering.Export.ImageBuffer;

public class Renderer
{
    private final Camera camera;
    private ImageBuffer imageBuffer;
    private int maxDepth;

    public Renderer(Camera camera)
    {
        this.maxDepth = 10;
        this.camera = camera;
        this.imageBuffer = new ImageBuffer(camera.getWidth(), camera.getHeight());
    }

    public void render (Hittable world)
    {
        for (int j = 0; j < this.camera.getHeight(); j ++)
        {
            for (int i = 0; i < this.camera.getWidth(); i++)
            {
                Colour pixelColour = new Colour(0,0,0);
                for (int sample = 0; sample < this.camera.getSamplesPrePixel(); sample++)
                {
                    Ray ray = this.camera.getRay(i, j);

                    pixelColour = pixelColour.add(rayColour(ray, this.maxDepth, world));
                }

                pixelColour = pixelColour.multiply(this.camera.getPixelSampleScale());

                this.imageBuffer.setPixelColour(pixelColour, j, i);
            }
        }
    }

    private Colour rayColour (final Ray ray, int depth, final Hittable world)
    {
        if (depth <= 0)
        {
            return new Colour(0,0,0);
        }

        HitRecord hitRecord = new HitRecord();

        if (world.hit(ray,new Interval(0.001, Double.POSITIVE_INFINITY), hitRecord))
        {
            Vector3D direction = hitRecord.getNormalVec().add(Vector3D.getRandomUnitVector());

            return rayColour(new Ray(hitRecord.getPositionVec(), direction), depth-1, world).multiply(0.5);
        }

        Vector3D unitDirection = Vector3D.getUnitVector(ray.direction());

        double a = 0.5*(unitDirection.getY() + 1.0);

        Vector3D colourVector = Vector3D.multiply(1.0-a, new Vector3D(1.0, 1.0, 1.0)).add(new Vector3D(0.5, 0.7, 1.0).multiply(a));

        return new Colour(colourVector);
    }

    public ImageBuffer getImageBuffer ()
    {
        return this.imageBuffer;
    }

    public int getMaxDepth ()
    {
        return this.maxDepth;
    }

    public void setMaxDepth (int maxDepth)
    {
        this.maxDepth = maxDepth;
    }
}
