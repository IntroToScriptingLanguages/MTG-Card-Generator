/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg.card.maker;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

;


/**
 *
 * @author Me
 */
public class MTGCardMaker {
    final static String whitespace_chars =  ""       /* dummy empty string for homogeneity */
                        + "\\u0009" // CHARACTER TABULATION
                        + "\\u000A" // LINE FEED (LF)
                        + "\\u000B" // LINE TABULATION
                        + "\\u000C" // FORM FEED (FF)
                        + "\\u000D" // CARRIAGE RETURN (CR)
                        + "\\u0020" // SPACE
                        + "\\u0085" // NEXT LINE (NEL) 
                        + "\\u00A0" // NO-BREAK SPACE
                        + "\\u1680" // OGHAM SPACE MARK
                        + "\\u180E" // MONGOLIAN VOWEL SEPARATOR
                        + "\\u2000" // EN QUAD 
                        + "\\u2001" // EM QUAD 
                        + "\\u2002" // EN SPACE
                        + "\\u2003" // EM SPACE
                        + "\\u2004" // THREE-PER-EM SPACE
                        + "\\u2005" // FOUR-PER-EM SPACE
                        + "\\u2006" // SIX-PER-EM SPACE
                        + "\\u2007" // FIGURE SPACE
                        + "\\u2008" // PUNCTUATION SPACE
                        + "\\u2009" // THIN SPACE
                        + "\\u200A" // HAIR SPACE
                        + "\\u2028" // LINE SEPARATOR
                        + "\\u2029" // PARAGRAPH SEPARATOR
                        + "\\u202F" // NARROW NO-BREAK SPACE
                        + "\\u205F" // MEDIUM MATHEMATICAL SPACE
                        + "\\u3000" // IDEOGRAPHIC SPACE
    ;

    final static Rectangle modern_frame = new Rectangle(25, 38, 208, 158);
    
    
    final static Point image_position_template = new Point(31, 60);
    
    static String target_directory = "C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards";
    
    final int iconSize = 105;
    final static String[] icons = {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
        "20", "X", "Y", "Z", "W", "U", "B", "R", "G", "S",
        "W/U", "W/B", "U/B", "U/R", "B/R", "B/G", "R/W", "R/G", "G/W", "G/U",
        "2/W", "2/U", "2/B", "2/R", "2/G", "W/P", "U/P", "B/P", "R/P", "G/P",
        "T", "Q"
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

             createSet("LEA");
             createSet("LEB");
             createSet("2ED");

    }
       public static void createSet(String set_name) throws Exception
    {
        System.out.println("Printing set: "+set_name);
            FileReader reader = new FileReader(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\AllCards\\AllSets.json"));
            JSONParser parser = new JSONParser();
             JSONObject magic_file = (JSONObject) parser.parse(reader);
             
             JSONObject set = (JSONObject) magic_file.get(set_name); //Change set here
             JSONArray cards = (JSONArray) set.get("cards");
             int num_cards = cards.size();
             System.out.println(num_cards+" cards in set.");
             new File("C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name).mkdir();
             target_directory = "C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name;
             
             System.out.println("Setup complete.  Initializing card printing...");
             JSONObject card;
             for (int i=0; i<num_cards; i++)
             {
                 card = (JSONObject) cards.get(i); //Change card here- test!
                 createCard(card, set);
                        
                 System.out.println((i+1)+" cards complete!");
             }
    }
    
    public static void createSet(String set_name, int index) throws Exception
    {
        System.out.println("Printing set: "+set_name);
            FileReader reader = new FileReader(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\AllCards\\AllSets.json"));
            JSONParser parser = new JSONParser();
             JSONObject magic_file = (JSONObject) parser.parse(reader);
             
             JSONObject set = (JSONObject) magic_file.get(set_name); //Change set here
             JSONArray cards = (JSONArray) set.get("cards");
             int num_cards = cards.size();
             System.out.println(num_cards+" cards in set.");
             new File("C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name).mkdir();
             target_directory = "C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name;
             
             System.out.println("Setup complete.  Initializing card printing...");
             JSONObject card;
             for (int i=index; i<num_cards; i++)
             {
                 card = (JSONObject) cards.get(i); //Change card here- test!
                 createCard(card, set);
                 System.out.println((i+1)+" cards complete!");
             }
    }
    
    public static void createCard(JSONObject card, JSONObject set) throws Exception
    {
        JSONArray supertypes = (JSONArray)card.get("supertypes");
            JSONArray subtypes = (JSONArray)card.get("subtypes");
            JSONArray types = (JSONArray)card.get("types");
            BufferedImage image = null, template = null, pt = null, stamp = null;
             
             //Getting card color identity
             String color = "c";
             if (color == null )
                 color = "c";
             else
                try
                {
                   JSONArray JSONcolors = (JSONArray) card.get("colors");
                   if (JSONcolors == null)
                   {
                       if (objectContained(types, "Artifact"))
                           color = "a";
                       else
                           color = "c";
                   }
                   else
                   {
                        Object[] objectArray = JSONcolors.toArray();
                        String[] colors = Arrays.copyOf(objectArray, objectArray.length, String[].class);
                        if (colors.length >= 2)
                            color = "m"; //multicolor
                        else
                        {

                            String loneColor = colors[0];
                            if (loneColor.equals("Blue"))
                                color = "u";
                            else if (loneColor.equals("Green"))
                                color = "g";
                            else if (loneColor.equals("Red"))
                                color = "r";
                            else if (loneColor.equals("White"))
                                color = "w";
                            else if (loneColor.equals("Black"))
                                color = "b";
                            else 
                            {
                                System.out.println("Using default frame.");
                                color = "c";
                            }
                        }
                   }
                }
                catch (Exception e)
                {
                    System.out.println("Using default frame.");
                    e.printStackTrace();
                    color = "c"; //Colorless
                }
             
             String layout = (String)card.get("layout");
             
             String name = (String)card.get("name");
             String set_name;

             boolean skip = false, basicLand = false;
             
             if (objectContained(supertypes, "Basic"))
                 basicLand = true;
             
              
                
               set_name = (String)set.get("code");
               if (set_name.equals("CON"))
               {
                   set_name = "CFX";
                   target_directory = "C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name;
               }
             
             if (layout.equals("normal"))
             {
                 if (types.contains("Enchantment") && ( types.contains("Artifact") || types.contains("Creature") ))
                     template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-nyx.mse-style\\"+color+"card.jpg"));
                 else if (types.contains("Land"))
                 {
                     if (subtypes != null)
                     {
                      Object[] subtypeArray = subtypes.toArray();
                       if (subtypeArray.length > 1)
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\mlcard.jpg"));
                       else if (objectContained(subtypes, "Forest"))
                       {
                          template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\glcard.jpg"));
                       }
                       else if (objectContained(subtypes, "Swamp"))
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\blcard.jpg"));
                       else if (objectContained(subtypes, "Island"))
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\ulcard.jpg"));
                       else if (objectContained(subtypes, "Mountain"))
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\rlcard.jpg"));
                       else if (objectContained(subtypes, "Plains"))
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\wlcard.jpg"));
                        else
                           template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\clcard.jpg"));
                     }
                     else
                     {
                          template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\clcard.jpg"));
                     }
                  }
                 else
                 {
                    if (!(color.equals("c")))
                        template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"card.jpg"));
                    else
                        template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"card.png"));
                 }
              }
             else
             {
                     skip = true;
              }
             
             if (skip == false && basicLand == false)
             {
                 System.out.println("Working on: "+name);
                System.out.println("From set: "+set_name);
                String imageName = name;
                imageName = imageName.replaceAll("[\u00E5\u00E0\u00E2\u00E1\u00E3\u00E4]", "a");
                imageName = imageName.replaceAll("\u00C6", "Ae");
                imageName = imageName.replaceAll("\u00E6", "ae");
                imageName = imageName.replaceAll("[\u00E8\u00E9\u00EA\u00EB]", "e");
                imageName = imageName.replaceAll("[\u00EC\u00ED\u00EE\u00EF]", "e");
                imageName = imageName.replaceAll("[\u00F0\u00F2\u00F3\u00F4\u00F5\u00F6]", "o");
                imageName = imageName.replaceAll("[\u00F9\u00FA\u00FB\u00FC]", "u");
                imageName = imageName.replaceAll("[^,.!a-zA-Z0-9-"+whitespace_chars+"']", "");
                
                
                System.out.println("Searching for cropped image with name: "+imageName);
                    try
                    {
                       try
                       {
                           if (set_name.equals("BNG") || set_name.equals("JOU"))
                               image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+".jpg"));
                           else
                                image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+".crop.jpg"));
                       }
                       catch (IIOException e)
                               {
                                    image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+"1.crop.jpg"));
                               }
                    }
                    catch (IIOException e)
                    {
                        image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+"2.crop.jpg"));
                    }
                
                
                    pt = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"pt.png"));
                    stamp = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"stamp.jpg"));

                    System.out.println("Image load successful!  Creating card...");
              template = createCard(template, image, card, stamp, pt);
                    System.out.println("Card successfully created!  Exporting to file...");       
              ImageIO.write(template, "jpg", new File(target_directory+"\\"+imageName+".full.jpg"));
             }
             else if (basicLand)
             {
                File f = new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name);
                File[] matchingFiles = f.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String nombre) {
                        return nombre.startsWith(name) && nombre.endsWith(".crop.jpg") && nombre.length() == ("1.crop.jpg".length()+name.length()) ;
                    }
                });
                 
                System.out.println("Working on basic land: "+name);
                System.out.println("From set: "+set_name);
                for (int i=1; i<= matchingFiles.length; i++)
                {
                    String imageName = name.replaceAll("[^a-zA-Z0-9-"+whitespace_chars+"']", "");
                    System.out.println("Searching for cropped image with name: "+imageName);
                          try
                          {
                              image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+i+".crop.jpg"));
                           }
                           catch (IIOException e)
                                   {
                                        image = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\crops\\"+set_name+"\\"+imageName+".crop.jpg"));
                                   }
                        pt = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"pt.png"));
                        stamp = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\"+color+"stamp.jpg"));

                        System.out.println("Image load successful!  Creating card...");
                    template = createCard(template, image, card, stamp, pt);
                        System.out.println("Card successfully created!  Exporting to file...");       
                     ImageIO.write(template, "jpg", new File(target_directory+"\\"+imageName+i+".full.jpg"));
                }
             }
             else
             {
                 System.out.println(name+" has an invalid card layout.  Proceeding to next card...");
                 skip = false;
             }
             System.out.println();
    }
    
/*  public static BufferedImage createCard(BufferedImage template, BufferedImage image, JSONObject card_data) //All the action's over here!
    {
        Graphics2D g = template.createGraphics();
        String name = (String)card_data.get("name");
        String manaCost = (String)card_data.get("manaCost");
        //Creating card image
       image = crop(image, modern_frame);
       
       image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 313, 230, Scalr.OP_ANTIALIAS);
       
       g.drawImage(image, null, (int)image_position_template.getX(), (int)image_position_template.getY());
       
       //Creating title
       g.setFont(new Font("Beleren", Font.PLAIN, 19));
       g.setColor(Color.BLACK);
       g.drawString(name, 30, 47);
       
       //Creating cmc
       FontMetrics fm = g.getFontMetrics(g.getFont());
       int width = fm.stringWidth(manaCost);
       
       g.drawString(manaCost, 344-width, 47);
       
       //Make sure image is right size.
       template = Scalr.resize(template, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 210, 300, Scalr.OP_ANTIALIAS);
       return template;
    }
   */
    
    public static boolean objectContained(JSONArray arr, Object targetValue) {
        if (arr == null)
            return false;
        
           Object[] objs = (Object[])arr.toArray();
       for(Object s: objs){
		if(s.equals(targetValue))
			return true;
	}
	return false;
        
        
}
    
  /* public static BufferedImage createCard(BufferedImage template, BufferedImage image, JSONObject card_data, BufferedImage stamp)
    {
        JPanel panel = new CardPanel(template, image, card_data, stamp);
        panel.setSize(panel.getPreferredSize());
        BufferedImage bi = new BufferedImage(template.getWidth(), template.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        
        String card_name = (String)card_data.get("name");
        String card_manaCost = (String)card_data.get("manaCost");
        
        
        
        return bi;
    }*/
   
      public static BufferedImage createCard(BufferedImage template, BufferedImage image, JSONObject card_data, BufferedImage stamp, BufferedImage pt)
    {
        JPanel panel = new CardPanel(template, image, card_data, stamp, pt);
        panel.setSize(panel.getPreferredSize());
        BufferedImage bi = new BufferedImage(template.getWidth(), template.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        
        String card_name = (String)card_data.get("name");
        String card_manaCost = (String)card_data.get("manaCost");
        
        
        
        return bi;
    }
    
   public static int countLines(String str, FontMetrics metrics, int boxWidth){
       if (str == null)
        return 0;
       
       String[] lines = str.split(Pattern.quote("\n"));
       int num_lines = 0;
       for (String line : lines)
       {
           int width = metrics.stringWidth(line);
           if (width > 0)
           {
             num_lines += Math.ceil(width/boxWidth)+1;
           }
           else
             num_lines++;
       }
       return num_lines;
   }
   
    public static String parseMana(String text)
    {
         String newText = text;
         for (int i=0; i<52; i++)
         {
            newText = newText.replaceAll(Pattern.quote("{"+icons[i]+"}"), "<img src='file:C:/Users/Me/Desktop/Forge Editions/Forge/customcards/template/mana/minisymbol_"+(i+1)+".png'/>");
         }
         newText = newText.replaceAll(Pattern.quote("\n"), "<br>");
         newText = newText.replaceAll(Pattern.quote("("), "<i>(");
         newText = newText.replaceAll(Pattern.quote(")"), ")</i>");
         return newText;
    }
    
        public static String parseManaBevel(String text)
    {
         String newText = text;
         for (int i=0; i<52; i++)
         {
            newText = newText.replaceAll(Pattern.quote("{"+icons[i]+"}"), "<img src='file:C:/Users/Me/Desktop/Forge Editions/Forge/customcards/template/beveled_mana/minisymbol_"+(i+1)+".png'/>");
         }
         return newText;
    }
    
    public static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
    FontMetrics m = g.getFontMetrics();
    if(m.stringWidth(text) < lineWidth) {
        g.drawString(text, x, y);
    } else {
        String[] words = text.split(" ");
        String currentLine = words[0];
        for(int i = 1; i < words.length; i++) {
            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                currentLine += " "+words[i];
            } else {
                g.drawString(currentLine, x, y);
                y += m.getHeight();
                currentLine = words[i];
            }
        }
        if(currentLine.trim().length() > 0) {
            g.drawString(currentLine, x, y);
        }
    }
}
    
    public static BufferedImage crop(BufferedImage image, Rectangle rect)
    {
        BufferedImage newImage = image.getSubimage((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        return newImage;
    }
}
