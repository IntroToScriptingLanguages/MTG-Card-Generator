/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg.planeswalker.maker;

import java.awt.Point;
import java.awt.Rectangle;
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
/**
 *
 * @author Me
 */
public class MTGPlaneswalkerMaker {
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
    public static void main(String[] args) throws Exception {
        createSet("LRW");
        createSet("M10");
        createSet("M11");
        createSet("M12");
        createSet("M13");
        createSet("M14");
        createSet("DDC");
        createSet("DDD");
        createSet("DDE");
        createSet("DDF");
        createSet("DDG");
        createSet("DDH");
        createSet("DDI");
        createSet("DDJ");
        createSet("DDK");
        createSet("DDL");
        createSet("DDM");
        createSet("MOR");
        createSet("JOU");
        createSet("BNG");
        createSet("THS");
        createSet("CNS");
        createSet("RTR");
        createSet("GTC");
        createSet("DGM");
        createSet("SHM");
        createSet("EVE");
        createSet("ALA");
        createSet("ARB");
        createSet("ZEN");
        createSet("WWK");
        createSet("ROE");
        createSet("SOM");
        createSet("MBS");
        createSet("NPH");
        createSet("ISD");
        createSet("DKA");
        createSet("AVR");
        createSet("MMA");
        createSet("CMD");
    }
    
        public static void createSet(String set_name) throws Exception
    {
        System.out.println("Printing set: "+set_name);
            FileReader reader = new FileReader(new File("C:\\Users\\Me\\Desktop\\Forge\\AllCards\\AllSets.json"));
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
                 
                 JSONArray types = (JSONArray)card.get("types"); 
                 if (types.contains("Planeswalker"))
                 {
                    createCard(card, set);
                        
                    System.out.println((i+1)+" cards complete!");
                 }
                 else
                 {
                     System.out.println("Not a planeswalker!  Skipping...");
                 }
             }
    }
    
    public static void createSet(String set_name, int index) throws Exception
    {
        System.out.println("Printing set: "+set_name);
            FileReader reader = new FileReader(new File("C:\\Users\\Me\\Desktop\\Forge\\AllCards\\AllSets.json"));
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
                 
                 JSONArray types = (JSONArray)card.get("types"); 
                 if (types.contains("Planeswalker"))
                 {
                    createCard(card, set);
                        
                    System.out.println((i+1)+" cards complete!");
                 }
                 else
                 {
                     System.out.println("Not a planeswalker!  Skipping...");
                 }
             }
    }
    
    public static void createCard(JSONObject card, JSONObject set) throws Exception
    {
        String name = (String)card.get("name");
        String set_name = (String)set.get("code");
        BufferedImage that_card = ImageIO.read(new File("C:\\Users\\Me\\Desktop\\Forge\\customcards\\cards\\"+set_name+"\\"+name+".full.jpg"));
        target_directory = "C:\\Users\\Me\\AppData\\Local\\Forge\\Cache\\pics\\cards\\"+set_name;
        ImageIO.write(that_card, "jpg", new File(target_directory+"\\"+name+".full.jpg"));
    }
    
}
