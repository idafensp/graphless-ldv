package oeg.upm.isantana.ldvserver.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorManager {
	
	//TODO mirar esta escala de colores, con los 8 que tiene debería bastar 
	//http://img.microsiervos.com/images2018/style-dictionary-colors-600.jpg
	
	public static List<Color> getColorBands(Color color, int bands) {

	    List<Color> colorBands = new ArrayList<Color>(bands);
	    for (int index = 0; index < bands; index++) {
	        colorBands.add(brighten(color, (double) index / (double) bands));
	    }
	    return colorBands;

	}

	public static Color darken(Color color, double fraction) {

	    int red = (int) Math.round(Math.max(0, color.getRed() - 255 * fraction));
	    int green = (int) Math.round(Math.max(0, color.getGreen() - 255 * fraction));
	    int blue = (int) Math.round(Math.max(0, color.getBlue() - 255 * fraction));

	    int alpha = color.getAlpha();

	    return new Color(red, green, blue, alpha);

	}

	public static Color brighten(Color color, double fraction) {

	    int red = (int) Math.round(Math.min(255, color.getRed() + 255 * fraction));
	    int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * fraction));
	    int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * fraction));

	    int alpha = color.getAlpha();

	    return new Color(red, green, blue, alpha);

	}

}
