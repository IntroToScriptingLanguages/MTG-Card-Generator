/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manasymbols;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author Me
 */
public class ManaSymbols {
    final static int x = 105, y = 105;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
      /*  BufferedImage map = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\template\\mana\\mana_symbols.png"));
        int count = 0;
        BufferedImage image = null;
        //Rows 1-5
        for (int i=0; i<6; i++)
        {
            for (int j=0; j<10; j++)
            {
                count++;
                image = map.getSubimage(x*j, y*i, x, y);
                ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\template\\mana\\symbol_"+count+".png"));
            }
        }
        
        for (int i=0; i<7; i++)
        {
            count++;
            image = map.getSubimage(x*i, y*6, x, y);
            ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\template\\mana\\symbol_"+count+".png"));
        }
    }
    */
        
   // for (int i=1; i<=52; i++)
    //{
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\ORI_C.png"));
        Image temp = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_C.png"));
        
        image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\ORI_U.png"));
        temp = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);

        g2d = image.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_U.png"));
        
        image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\ORI_R.png"));
        temp = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);

        g2d = image.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_R.png"));
        
        image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\ORI_M.png"));
        temp = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);

        g2d = image.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        ImageIO.write(image, "png", new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_M.png"));
   // }
}
    
}
