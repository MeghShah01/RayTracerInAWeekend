package Core.Rendering.Export;
import Core.Math.*;
public class ImageBuffer implements Image
{
    private final int width, height, size;
    private final Colour[] buffer;

    public ImageBuffer (int width, int height)
    {
        this.width = width;
        this.height = height;
        this.size = width*height;
        this.buffer = new Colour[size];
    }

    @Override
    public int getImageWidth()
    {
        return this.width;
    }

    @Override
    public int getImageHeight()
    {
        return this.height;
    }

    public void setPixelColour (Colour colour, int row, int column)
    {
        this.buffer[row*this.width+column] = colour;
    }

    public Colour getPixelColour (int row, int column)
    {
        return this.buffer[row*this.width+column];
    }

    public int getSize()
    {
        return this.size;
    }
}
