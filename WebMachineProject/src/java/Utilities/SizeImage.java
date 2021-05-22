package Utilities;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;


public class SizeImage
{

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    public void writeFile() throws IOException, URISyntaxException
    {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        URL url = facesContext.getClass().getResource("resources/images/photos_original/iPhone11.png");
        ExternalContext externalContext = facesContext.getExternalContext();
        Path path
                = Paths.get(((ServletContext) externalContext.getContext())
                        .getRealPath("/resources/images/photos-resized/iPhone11.png"));
        
        BasicFileAttributes attrs = Files.readAttributes(path,
                BasicFileAttributes.class);

        externalContext.responseReset();
        externalContext.setResponseContentType("image/png");
        externalContext.setResponseContentLength((int) attrs.size());
        externalContext.setResponseHeader("Content-Disposition",
                "attachment; filename=\"" + "iPhone11.png" + "\"");
        
        
   
        InputStream inStream = externalContext.
                getResourceAsStream("/resources/images/photos/iPhone.png");
        
        BufferedImage originalImage = ImageIO.read( inStream);
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
         BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
         

        
        try (OutputStream os = externalContext.getResponseOutputStream())
          {
         ImageIO.write(resizeImageHintJpg, "jpg", os);

          }
        facesContext.responseComplete();
    }

    
    public  void read( )
    {

        try
          {
            InputStream is = this.getClass().getResourceAsStream("/resources/photos_riginal/iPhone.png");
            BufferedImage originalImage = ImageIO.read( is );
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            
            BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
            ImageIO.write(resizeImageHintJpg, "jpg", new File("c:\\image\\mkyong_hint_jpg.jpg"));

            BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
            ImageIO.write(resizeImageHintPng, "png", new File("c:\\image\\mkyong_hint_png.jpg"));

          }
        catch (IOException e)
          {
            System.out.println(e.getMessage());
          }

    }



    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type)
    {

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
}
