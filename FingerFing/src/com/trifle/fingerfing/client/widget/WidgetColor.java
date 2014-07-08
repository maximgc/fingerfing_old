package com.trifle.fingerfing.client.widget;

public class WidgetColor {
	
	private int red, green, blue;
	
	private String str;

	public WidgetColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.str = new StringBuilder("rgb(").append(red).append(",").append(green)
				.append(",").append(blue).append(")").toString(); 
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public String toStringRgbFomat() {
		return str;
	}
}
