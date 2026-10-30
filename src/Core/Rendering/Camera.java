package Core.Rendering;

import java.util.Random;
import Core.Math.*;
public class Camera
{
    private Vector3D position;
    private int width, height, samplesPrePixel;
    private double aspectRatio, focalLength, viewportWidth, viewportHeight, pixelSampleScale;
    private Vector3D viewportU, viewportV, deltaU, deltaV,
            upperLeft, pixel00;
    private final Random random = new Random();

    public Camera ()
    {
        this.samplesPrePixel = 10;
        this.position = new Vector3D(0, 0, 0);
        this.width = 400;
        this.aspectRatio = 16.0/9.0;
        this.height = Math.max(((int) (this.width / aspectRatio)), 1);
        this.focalLength = 1.0;
        initCamera();

    }

    public Camera (Vector3D position, int width, double aspectRatio, double focalLength, int samplesPrePixel)
    {
        this.position = position;
        this.width = width;
        this.aspectRatio = aspectRatio;
        this.height = Math.max(((int) (this.width / aspectRatio)), 1);
        this.focalLength = focalLength;
        this.samplesPrePixel = samplesPrePixel;
        initCamera();
    }

    public Camera (Vector3D position, int width, int height, double focalLength)
    {
        this.position = position;
        this.width = width;
        this.height = height;
        this.focalLength = focalLength;
        initCamera();
    }

    private void initCamera ()
    {
        this.pixelSampleScale = 1.0/samplesPrePixel;
        this.viewportHeight = 2.0;
        this.viewportWidth = this.viewportHeight * ((double)(this.width)/this.height);
        this.viewportU = new Vector3D(this.viewportWidth, 0,0);
        this.viewportV = new Vector3D(0, -this.viewportHeight, 0);
        this.deltaU = Vector3D.divide(this.viewportU, this.width);
        this.deltaV = Vector3D.divide(this.viewportV, this.height);
        this.upperLeft = this.position.subtract(new Vector3D(0,0, this.focalLength)).subtract(this.viewportU.divide(2.0)).subtract(this.viewportV.divide(2.0));
        this.pixel00 = Vector3D.add(Vector3D.multiply(0.5, Vector3D.add(this.deltaU, this.deltaV)), this.upperLeft);
    }

    public Ray getRay (int pixelU, int pixelV)
    {

        Vector3D offset = sampleSquare();

        Vector3D pixelSample = pixel00.add(deltaU.multiply(pixelU + offset.getX())).add(deltaV.multiply(pixelV + offset.getY()));

        //Core.Math.Vector3D pixelCenter = this.pixel00.add(deltaU.multiply(pixelU)).add(deltaV.multiply(pixelV));

        Vector3D rayDirection = pixelSample.subtract(position);

        return new Ray(this.position, rayDirection);
    }

    private Vector3D sampleSquare ()
    {
        return new Vector3D(random.nextDouble() - 0.5, random.nextDouble() - 0.5, 0);
    }

    public Vector3D getPosition()
    {
        return position;
    }

    public void setPosition(Vector3D position)
    {
        this.position = position;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public double getAspectRatio()
    {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio)
    {
        this.aspectRatio = aspectRatio;
    }

    public double getFocalLength()
    {
        return focalLength;
    }

    public void setFocalLength(double focalLength)
    {
        this.focalLength = focalLength;
    }

    public Vector3D getPixel00()
    {
        return pixel00;
    }

    public void setPixel00(Vector3D pixel00)
    {
        this.pixel00 = pixel00;
    }

    public Vector3D getDeltaU()
    {
        return deltaU;
    }

    public void setDeltaU(Vector3D deltaU)
    {
        this.deltaU = deltaU;
    }

    public Vector3D getDeltaV()
    {
        return deltaV;
    }

    public void setDeltaV(Vector3D deltaV)
    {
        this.deltaV = deltaV;
    }

    public int getSamplesPrePixel()
    {
        return samplesPrePixel;
    }

    public void setSamplesPrePixel(int samplesPrePixel)
    {
        this.samplesPrePixel = samplesPrePixel;
        this.pixelSampleScale = 1.0/this.samplesPrePixel;
    }

    public double getPixelSampleScale()
    {
        return pixelSampleScale;
    }
}
