package Application;

import Core.Object.*;
import Core.Rendering.*;
import Core.Math.*;
import Core.Rendering.Export.PPMFileWriter;

public class Main
{
    public static void main(String[] args)
    {
        Camera camera = new Camera();

        camera.setSamplesPrePixel(100);

        Renderer renderer = new Renderer(camera);

        renderer.setMaxDepth(50);

        HittableList world = new HittableList();

        world.addToList(new Sphere(new Vector3D(0,0,-1), 0.5));

        world.addToList(new Sphere(new Vector3D(0, -100.5, -1), 100));

        renderer.render(world);

        PPMFileWriter ppmFileWriter = new PPMFileWriter(renderer.getImageBuffer(),"./", "image");

        ppmFileWriter.writeImage();

        System.out.println(ppmFileWriter);
    }
}