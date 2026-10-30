package Core.Rendering.Export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Core.Math.*;

public class PPMFileWriter implements ImageWriter
{
    private File filePath;
    private String imageName;
    private Image image;
    public PPMFileWriter(Image image, String filePath, String imageName)
    {
        this.image = image;
        this.imageName = imageName;
        this.filePath = new File(filePath + imageName + ".ppm");
    }

    @Override
    public void writeImage ()
    {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath)))
        {
            bufferedWriter.write("P3\n" + this.image.getImageWidth() + " " + this.image.getImageHeight() + "\n255\n");
            for (int i = 0; i < this.image.getImageHeight(); i ++)
            {
                for (int j = 0; j < this.image.getImageWidth(); j ++)
                {
                    Colour colour = this.image.getPixelColour(i, j);

                    final Interval intensity = new Interval(0.000, 0.999);
                    double r = colour.getX();
                    double g = colour.getY();
                    double b = colour.getZ();

                    r = Colour.linearToGamma(r);
                    g = Colour.linearToGamma(g);
                    b = Colour.linearToGamma(b);


                    int rByte = (int) (256*intensity.clamp(r));
                    int gByte = (int) (256*intensity.clamp(g));
                    int bByte = (int) (256*intensity.clamp(b));


                    //System.out.println(rByte + " " + gByte + " " + bByte + "\n");

                    bufferedWriter.write(rByte + " " + gByte + " " + bByte + "\n");
                }
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Error writing ppm image!");
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString ()
    {
        return "File Type: PPM\nCore.Rendering.Export.Image Name: " + this.imageName + "\nCore.Rendering.Export.Image Width:" +
                this.image.getImageWidth() + "\nCore.Rendering.Export.Image Height: " + this.image.getImageHeight()
                + "\nSaved to: '" + this.filePath + "'\n";
    }

}
