/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg.card.maker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import static mtg.card.maker.MTGCardMaker.objectContained;
import org.imgscalr.Scalr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author Me
 */
public class CardPanel extends JPanel{
    final static Rectangle modern_frame = new Rectangle(25, 38, 208, 158);
    
    
    final static Point image_position_template = new Point(31, 60);
    
    final static String target_directory = "C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\cards";
    
    final int iconSize = 105;
    final static String[] icons = {
        "{0}", "{1}", "{2}", "{3}", "{4}", "{5}", "{6}", "{7}", "{8}", "{9}",
        "{10}", "{11}", "{12}", "{13}", "{14}", "{15}", "{16}", "{17}", "{18}", "{19}",
        "{20}", "{X}", "{Y}", "{Z}", "{W}", "{U}", "{B}", "{R}", "{G}", "{S}",
        "{W/U}", "{W/B}", "{U/B}", "{U/R}", "{B/R}", "{B/G}", "{R/W}", "{R/G}", "{G/W}", "{G/U}",
        "{2/W}", "{2/U}", "{2/B}", "{2/R}", "{2/G}", "{W/P}", "{U/P}", "{B/P}", "{R/P}", "{G/P}",
        "{T}", "{Q}"};
    BufferedImage template, image;
    String color;
    JLabel name, manacost, type, textbox, artist_credit;
    ImageIcon set, ptbox, stamp, artistarrow, wm;
    boolean creature = false, ultrarare = false, artist_ready = false, eldrazi = false, watermark = false;
    
    ImageIcon foilstamp = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\foil_stamp.png");
    
 /*   public CardPanel(BufferedImage template, BufferedImage image, JSONObject card_data, BufferedImage stamp) //For noncreatures
    {
        this.template = template;
        this.setLayout(null);
        Font mplantin = new Font("MPlantin", Font.PLAIN, 10);
        this.setFont(mplantin);
        
        String card_name = (String)card_data.get("name");
        String card_manaCost = (String)card_data.get("manaCost");
        String card_type = (String)card_data.get("type");
        String rarity = (String)card_data.get("rarity");
        String text = (String)card_data.get("text");
        String flavor = "<i>"+(String)card_data.get("flavor")+"</i>";
        String[] types = (String[])card_data.get("types");
        
        //Card image
        image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 313, 230, Scalr.OP_ANTIALIAS);
        this.image = image;
        
        //Card name
        name = new JLabel("<html>"+card_name+"</html>");
        name.setFont(new Font("Beleren", Font.PLAIN, 19));
        name.setLocation(30, 30);
        name.setForeground(Color.BLACK);
        name.setSize(new Dimension(316, 20));
        add(name);
        
        //Card mana cost
        manacost = new JLabel("<html>"+card_manaCost+"</html>");
        manacost.setFont(new Font("Beleren", Font.PLAIN, 19));
        manacost.setLocation(30, 30);
        manacost.setHorizontalAlignment(SwingConstants.RIGHT);
        manacost.setText(MTGCardMaker.parseManaBevel(manacost.getText()));
         manacost.setSize(new Dimension(316, 20));
        add(manacost);
        
        //Card type
        type = new JLabel("<html>"+card_type+"</html>");
        type.setFont(new Font("Beleren", Font.PLAIN, 17));
        type.setLocation(30, 297);
        type.setForeground(Color.BLACK);
        type.setText(MTGCardMaker.parseMana(type.getText()));
         type.setSize(new Dimension(316, 20));
        add(type);
      
        //Set emblem
        if (rarity.equals("Common"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_C.png");
        else if (rarity.equals("Uncommon"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_U.png");
        else if (rarity.equals("Rare") || rarity.equals("Special"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_R.png");
        else if (rarity.equals("Mythic Rare"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_M.png");
        else
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge\\customcards\\set symbols\\SET_C.png");
        
        //Card text
        FontMetrics font_metrics = this.getFontMetrics(mplantin);
        int fontSize = 14;
        int num_lines = MTGCardMaker.countLines(text+"\n\n"+flavor, font_metrics, 153);
       if (num_lines > 5)
        {
            if (num_lines > 8)
            {
                if (num_lines > 10)
                {
                    if (num_lines > 13)
                        fontSize = 7;
                    else
                        fontSize = 8;

                }
                else
                    fontSize = 9;
            }
            else
                fontSize = 11;
        }
        else
            fontSize = 13;
        
        
        textbox = new JLabel("<html><p style=\"font-family:'MPlantin'\" style=\"font-weight:400\"  style=\"font-size:"+fontSize+"px\">"+text+"<br><br></p><p style=\"font-family:'Times New Roman'\" style=\"font-weight:400\" style=\"font-size:"+fontSize+"px\">"+flavor+"</p></html>");
        textbox.setText(MTGCardMaker.parseMana(textbox.getText()));
        textbox.setVerticalAlignment(JLabel.TOP);
        textbox.setForeground(Color.BLACK);
        textbox.setFont(null);
        ;
        
     textbox.setLocation(30, 330);
     textbox.setSize(new Dimension(315, 153));
         add(textbox);
         
         //stamp
         if (rarity.equals("Rare")||rarity.equals("Mythic Rare")||rarity.equals("Special"))
         {
             ultrarare = true;
             this.stamp = new ImageIcon(stamp);
         }
                
        repaint();
        this.setPreferredSize(new Dimension(template.getWidth(), template.getHeight()));
    }*/
    
     public CardPanel(BufferedImage template, BufferedImage image, JSONObject card_data, BufferedImage stamp, BufferedImage pt) //For Creatures and (maybe) Planeswalkers
    {
        this.template = template;
        this.setLayout(null);
        Font mplantin = new Font("MPlantin", Font.PLAIN, 10);
        this.setFont(mplantin);
        
        
        
        
        String card_name = (String)card_data.get("name");
        String card_manaCost = (String)card_data.get("manaCost");
        String card_type = (String)card_data.get("type");
        String rarity = (String)card_data.get("rarity");
        String text = (String)card_data.get("text");
        String flavor = "<i>"+(String)card_data.get("flavor")+"</i>";
        JSONArray types = (JSONArray)card_data.get("types");
        String artist = (String)card_data.get("artist");
        JSONArray colors = (JSONArray)card_data.get("colors");
        JSONArray supertypes = (JSONArray)card_data.get("supertypes");
        JSONArray subtypes = (JSONArray)card_data.get("subtypes");
        
        //Card image
        if (colors == null && !(objectContained(types, "Artifact")) && !(objectContained(types, "Land"))) //Eldrazi!
        {
            image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 351, 479, Scalr.OP_ANTIALIAS);
            eldrazi = true;
        }
        else
            image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, 313, 230, Scalr.OP_ANTIALIAS);
        this.image = image;
        
        //Card name
        name = new JLabel("<html>"+card_name+"</html>");
        name.setFont(new Font("Beleren", Font.PLAIN, 18));
        name.setLocation(30, 30);
        name.setForeground(Color.BLACK);
        name.setSize(new Dimension(316, 20));
        add(name);
        
        //Card mana cost
        if (card_manaCost == null)
            card_manaCost = "";
        manacost = new JLabel("<html>"+card_manaCost+"</html>");
        manacost.setFont(new Font("Beleren", Font.PLAIN, 19));
        manacost.setLocation(30, 30);
        manacost.setHorizontalAlignment(SwingConstants.RIGHT);
        manacost.setText(MTGCardMaker.parseManaBevel(manacost.getText()));
         manacost.setSize(new Dimension(316, 20));
        add(manacost);
        
        //Card type
        type = new JLabel("<html>"+card_type+"</html>");
        type.setFont(new Font("Beleren", Font.PLAIN, 16));
        type.setLocation(30, 297);
        type.setForeground(Color.BLACK);
        type.setText(MTGCardMaker.parseMana(type.getText()));
         type.setSize(new Dimension(316, 20));
        add(type);
      
        //Set emblem
        if (rarity.equals("Common"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\set symbols\\SET_C.png");
        else if (rarity.equals("Uncommon"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\set symbols\\SET_U.png");
        else if (rarity.equals("Rare") || rarity.equals("Special"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\set symbols\\SET_R.png");
        else if (rarity.equals("Mythic Rare"))
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\set symbols\\SET_M.png");
        else
            set = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\set symbols\\SET_C.png");
        
        //Card text
        if (objectContained(supertypes, "Basic"))
        {
            watermark = true;
            if (objectContained(subtypes, "Forest"))
            {
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_g.png");
            }
            else if (objectContained(subtypes, "Swamp"))
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_b.png");
            else if (objectContained(subtypes, "Island"))
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_u.png");
            else if (objectContained(subtypes, "Mountain"))
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_r.png");
            else if (objectContained(subtypes, "Plains"))
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_w.png");
            else
            {
                System.out.println("Using default basic land watermark.");
                wm = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15-watermarks\\watermark_w.png");
            }
        }
        else
        {
            FontMetrics font_metrics = this.getFontMetrics(mplantin);
            int fontSize = 14;
            String printedText;
            boolean noFlavor = false, noText = false;
            if ((String)card_data.get("flavor") == null)
            {
                printedText = text;
                noFlavor = true;
            }
            else if ((String)card_data.get("text") == null)
            {
                printedText = flavor;
                noText = true;
            }
            else
                printedText = text+"\n\n"+flavor;
            int num_lines = MTGCardMaker.countLines(printedText, font_metrics, 153);
            if (num_lines > 6)
            {
                if (num_lines > 8)
                {
                    if (num_lines > 10)
                    {
                        if (num_lines > 12)
                        {
                           fontSize = 8;
                        }
                        else
                        {
                            fontSize = 9;
                        }

                    }
                    else
                        fontSize = 10;
                }
                else
                    fontSize = 12;
            }
            if (text != null)
                text = text.replaceAll("\n", "<br>");
            
            
            if (noFlavor == true)
                textbox = new JLabel("<html><p style=\"font-family:'MPlantin'\" style=\"font-weight:400\"  style=\"font-size:"+fontSize+"px\">"+text+"</p></html>");
            else if (noText == true)
                textbox = new JLabel("<html><p style=\"font-family:'Times New Roman'\" style=\"font-weight:400\" style=\"font-size:"+fontSize+"px\">"+flavor+"</p></html>");
            else
                textbox = new JLabel("<html><p style=\"font-family:'MPlantin'\" style=\"font-weight:400\"  style=\"font-size:"+fontSize+"px\">"+text+"<br><br></p><p style=\"font-family:'Times New Roman'\" style=\"font-weight:400\" style=\"font-size:"+fontSize+"px\">"+flavor+"</p></html>");

            textbox.setText(MTGCardMaker.parseMana(textbox.getText()));
            textbox.setVerticalAlignment(JLabel.TOP);
            textbox.setForeground(Color.BLACK);
            textbox.setFont(null);


         textbox.setLocation(30, 330);
         textbox.setSize(new Dimension(315, 153));
             add(textbox);
                }
         //Power/Toughness (creature only)
         if (MTGCardMaker.objectContained(types, "Creature"))
         {
             creature = true;
             String power = (String)card_data.get("power");
             String toughness = (String)card_data.get("toughness");
             int power_size = 24;
             int slash_length = 10;
             if (power.length() > 1 || toughness.length() > 1)
             {
                 power_size = 18;
                 slash_length = 8;
             }
             Font power_tough = new Font("Beleren", Font.BOLD, power_size);
             ptbox = new ImageIcon(pt);
             
             JLabel pow = new JLabel(power);
             pow.setFont(power_tough);
             pow.setLocation(295, 474);
             pow.setForeground(Color.BLACK);
             pow.setSize(22, 23);
             pow.setHorizontalAlignment(JLabel.RIGHT);
             
             JLabel slash = new JLabel("/");
             slash.setFont(power_tough);
             slash.setLocation(317, 474);
             slash.setSize(slash_length, 23);
             slash.setForeground(Color.BLACK);
             
             JLabel tough = new JLabel(toughness);
             tough.setFont(power_tough);
             tough.setLocation(317+slash_length, 474);
             tough.setSize(22, 23);
             tough.setHorizontalAlignment(JLabel.LEFT);
             tough.setForeground(Color.BLACK);
             
             add(pow);
             add(slash);
             add(tough);
         }
         
         //stamp
         if (rarity.equals("Rare")||rarity.equals("Mythic Rare")||rarity.equals("Special"))
         {
             if (eldrazi)
             {
                 try
                 {
                    this.template = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\ccard2.png"));
                 }
                 catch (Exception e)
                 {
                     this.template = template;
                 }        
             }
             else
             {
                ultrarare = true;
                this.stamp = new ImageIcon(stamp);
             }
         }
         
         //artist
         artist_credit = new JLabel(artist);
         artist_credit.setFont(new Font("Beleren Small Caps", Font.PLAIN, 12));
         artist_credit.setLocation(46, 494);
         artist_credit.setForeground(Color.WHITE);
         artist_credit.setSize(280, 20);
         add(artist_credit);
         
         artistarrow = new ImageIcon("C:\\Users\\Me\\Desktop\\Forge Editions\\Forge\\customcards\\template\\magic-m15.mse-style\\artist_arrow.png");
         
         //watermark
                //Coming later...
        repaint();
        this.setPreferredSize(new Dimension(template.getWidth(), template.getHeight()));
    }
    
    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (eldrazi)
        {
            g.drawImage(image, 13, 13, this);
        }
        g.drawImage(template, 0, 0, this);
        if (eldrazi == false)
        {
            g.drawImage(image, (int)image_position_template.getX(), (int)image_position_template.getY(), this);
        }
        set.paintIcon(this, g, 325, 297);
        if (creature)
        {
            ptbox.paintIcon(this, g, 286, 469);
        }
        if (ultrarare)
        {
            stamp.paintIcon(this, g, 166, 472);
            foilstamp.paintIcon(this, g, 166, 472);
        }
        if (watermark)
        {
            wm.paintIcon(this, g, 125, 340);
        }
        artistarrow.paintIcon(this, g, 24, 498);
    }
}
