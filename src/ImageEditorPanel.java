import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class ImageEditorPanel extends JPanel implements KeyListener{
    Color[][] pixels;

    public ImageEditorPanel() {
        BufferedImage imageIn = null;
        try {
            imageIn = ImageIO.read(new File("funnydog.jpg"));
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        pixels = makeColorArray(imageIn);
        setPreferredSize(new Dimension(pixels[0].length, pixels.length));
        setBackground(Color.BLACK);
        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                g.setColor(pixels[row][col]);
                g.fillRect(col, row, 1, 1);
            }
        }
    }

    public void run() {
        repaint();
    }

    public static Color[][] posterize(Color [][] arr){
        Color [][] newArr = new Color [arr.length][arr[0].length];
        Color red = new Color(255, 0 , 0);
        Color green = new Color(0,255,0);
        Color blue = new Color(0, 0, 255);
        Color yellow = new Color(255, 255, 0);
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                Color temp = arr[r][c];
                int redDiff = getRedDiff(red, temp);
                int greenDiff = getGreenDiff(green, temp);
                int blueDiff = getBlueDiff(blue, temp);
                int yellowDiff = getYellowDiff(yellow, temp);
                if (redDiff < greenDiff && redDiff < blueDiff && redDiff < yellowDiff){
                    newArr[r][c] = red;
                } else if (greenDiff < redDiff && greenDiff < blueDiff && greenDiff < yellowDiff){
                    newArr[r][c] = green;
                } else if (blueDiff < redDiff && blueDiff < greenDiff && blueDiff < yellowDiff){
                    newArr[r][c] = blue;
                } else {
                    newArr[r][c] = yellow;
                }
            } 
        }
        return newArr;
    }

    public static int getYellowDiff(Color yellow, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((yellow.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((yellow.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((yellow.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getBlueDiff(Color blue, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((blue.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((blue.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((blue.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getGreenDiff(Color green, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((green.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((green.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((green.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getRedDiff(Color red, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((red.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((red.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((red.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    
    public static Color[][] blur(Color[][] arr){
        Color [][] newArr = new Color[arr.length][arr[0].length];
        int radius = 8;
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {     
                int redAmount = 0;
                int greenAmount = 0;
                int blueAmount = 0;
                int numNeibors = 0;
                for (int i = r - radius; i < r + radius; i++) {
                    for (int j = c - radius; j <= c + radius; j++) {
                        if (i >= 0 && j >= 0 && i < arr.length && j < arr[0].length){
                            Color temp = arr[i][j];
                            redAmount += temp.getRed();
                            greenAmount += temp.getGreen();
                            blueAmount += temp.getBlue();
                            numNeibors++;
                        }
                        
                    }
                }
                int redAverage = redAmount / numNeibors;
                int greenAverage = greenAmount / numNeibors;
                int blueAverage = blueAmount / numNeibors;
                Color newCol = new Color (redAverage, greenAverage, blueAverage);
                newArr[r][c] = newCol;
            }
        }
        return newArr;
    }

    public static Color[][] groovyRetro(Color[][] arr){
        Color [][] newArr = new Color[arr.length][arr[0].length];
        Color harvestOrange = new Color(219, 107, 48);
        Color carissimaPink = new Color (230, 134, 154);
        Color waxWay = new Color (210, 180, 103);
        Color fairyPrincess = new Color(245, 218, 223);
        Color woodBrown = new Color(98, 52, 18);
        Color harmoniousGold = new Color(223, 205, 165);
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                Color temp = arr[r][c];
                int orangeDiff = getOrangeDiff(harvestOrange, temp);
                int pinkDiff = getPinkDiff(carissimaPink, temp);
                int waxDiff = getWaxDiff(waxWay, temp);
                int fairyDiff = getFairyDiff(fairyPrincess, temp);
                int brownDiff = getBrownDiff(woodBrown, temp);
                int goldDiff = getGoldDiff(harmoniousGold, temp);
                if (orangeDiff < pinkDiff && orangeDiff < waxDiff && orangeDiff < fairyDiff && orangeDiff < brownDiff && orangeDiff < goldDiff ){
                    newArr[r][c] = harvestOrange; 
                } else if (pinkDiff < orangeDiff && pinkDiff < waxDiff && pinkDiff < fairyDiff && pinkDiff < brownDiff && pinkDiff < goldDiff){
                    newArr[r][c] = carissimaPink;
                } else if (waxDiff < orangeDiff && waxDiff < pinkDiff && waxDiff < fairyDiff && waxDiff < brownDiff && waxDiff < goldDiff){
                    newArr[r][c] = waxWay;
                } else if (fairyDiff < orangeDiff && fairyDiff < pinkDiff && fairyDiff < waxDiff && fairyDiff < brownDiff && fairyDiff < goldDiff){
                    newArr[r][c] = fairyPrincess;
                } else if (brownDiff < orangeDiff && brownDiff < pinkDiff && brownDiff < waxDiff && brownDiff < fairyDiff && brownDiff < goldDiff){
                    newArr[r][c] = woodBrown;
                } else {
                    newArr [r][c] = harmoniousGold;
                }
                
            }
            
        }
        return newArr;
    }

    public static int getGoldDiff(Color harmoniousGold, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((harmoniousGold.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((harmoniousGold.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((harmoniousGold.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getBrownDiff(Color woodBrown, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((woodBrown.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((woodBrown.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((woodBrown.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getFairyDiff(Color fairyPrincess, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((fairyPrincess.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((fairyPrincess.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((fairyPrincess.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getWaxDiff(Color waxDiff, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((waxDiff.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((waxDiff.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((waxDiff.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getPinkDiff(Color carismaPink, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((carismaPink.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((carismaPink.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((carismaPink.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static int getOrangeDiff(Color harvestOrange, Color temp){
        final int SQUARE_EXPONENT = 2;
        double redDiff = Math.pow((harvestOrange.getRed() - temp.getRed()), SQUARE_EXPONENT);
        double greenDiff = Math.pow((harvestOrange.getGreen() - temp.getGreen()), SQUARE_EXPONENT);
        double blueDiff = Math.pow((harvestOrange.getBlue() - temp.getBlue()), SQUARE_EXPONENT);
        int distance = (int)(Math.sqrt(redDiff + greenDiff + blueDiff));
        return distance;
    }

    public static Color[][] darkUp(Color[][] arr){
        final int MAX_DARKNESS = 0;
        Color [][] newArr = new Color[arr.length][arr[0].length];
        int colorChange = 5;
        int maxDark = MAX_DARKNESS + colorChange;
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) { 
                Color tempColor = arr[r][c];
                if (tempColor.getRed() > maxDark && tempColor.getGreen() > maxDark && tempColor.getBlue() > maxDark){
                    Color newColor = new Color(tempColor.getRed() - colorChange, tempColor.getGreen() - colorChange, tempColor.getBlue() - colorChange);
                    newArr[r][c] = newColor;
                } else {
                    Color dark = new Color(maxDark, maxDark, maxDark);
                    newArr[r][c] = dark;
                }
            }
        }
        return newArr;
    }

    public static Color[][] brightUp(Color[][] arr){
        final int MAX_BRIGHTNESS = 255;
        Color [][] newArr = new Color[arr.length][arr[0].length];
        int colorChange = 5;
        int maxColor = MAX_BRIGHTNESS - colorChange;
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                Color tempColor = arr[r][c];
                if (tempColor.getRed() < maxColor && tempColor.getGreen() < maxColor && tempColor.getBlue() < maxColor){
                    Color newColor = new Color(tempColor.getRed() + colorChange, tempColor.getGreen() + colorChange, tempColor.getBlue() + colorChange);
                    newArr[r][c] = newColor;
                } else {
                    Color maxBright = new Color(MAX_BRIGHTNESS, MAX_BRIGHTNESS, MAX_BRIGHTNESS);
                    newArr[r][c] = maxBright;
                }
                
            }
        }
        return newArr;
    }

    public static Color [][] grayscale(Color[][] arr){
        final int NUM_COLORS = 3;
        Color[][] newArr = new Color[arr.length][arr[0].length];
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                Color tempColor = arr[r][c];
                int tempVal = (tempColor.getRed() + tempColor.getGreen() + tempColor.getBlue()) / NUM_COLORS;
                Color newColor = new Color(tempVal, tempVal, tempVal);
                newArr[r][c] = newColor;
            }
        }
        return newArr;
    }

    public static Color[][] flipHorizontal(Color[][] arr){
        Color[][] newArr = new Color[arr.length][arr[0].length];
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                newArr[r][c] = arr[r][arr[0].length - 1 - c];
            }
            
        }
        return newArr;

    }
    public static Color[][] flipVerticle(Color[][] arr){
        Color[][] newArr = new Color[arr.length][arr[0].length];
        for (int r = 0; r < newArr.length; r++) {
            for (int c = 0; c < newArr[0].length; c++) {
                newArr[r][c] = arr[arr.length - r - 1][c];
            }
            
        }
        return newArr;

    }

    public Color[][] makeColorArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color[][] result = new Color[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col, row), true);
                result[row][col] = c;
            }
        }
        return result;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'h'){
            pixels = flipHorizontal(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'v'){
            pixels = flipVerticle(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'g'){
            pixels = grayscale(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'u'){
            pixels = brightUp(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'd'){
            pixels = darkUp(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'r'){
            pixels = groovyRetro(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'b'){
            pixels = blur(pixels);
            repaint();
        }
        if (e.getKeyChar() == 'p'){
            pixels = posterize(pixels);
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        }
    

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
