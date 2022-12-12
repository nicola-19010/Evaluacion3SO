package VersionFinal;

import java.awt.*;

public class Herramienta {


    public static String obtenerColorDelPixel(int x, int y){
        Color color = null;

        try {
            color = (new Robot()).getPixelColor(x, y);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        //return String.valueOf(color.getRGB());
        return "Rojo=" + color.getRed() + " Verde=" + color.getGreen()+" Azul=" + color.getBlue()+" "+color.getRGB();
    }

    /*public static Color getPointerColor() throws AWTException {
        Point coordinates = MouseInfo.getPointerInfo().getLocation();
        Robot robot = new Robot();
        return robot.getPixelColor((int) coordinates.getX(), (int) coordinates.getX());
    }*/

}