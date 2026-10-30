package Core.Rendering.Export;
import Core.Math.Colour;

public interface Image
{
    int getImageWidth ();
    int getImageHeight ();

    void setPixelColour (Colour colour, int row, int column);
    Colour getPixelColour (int row, int column);
}
