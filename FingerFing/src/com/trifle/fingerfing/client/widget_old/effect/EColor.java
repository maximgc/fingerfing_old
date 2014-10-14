package com.trifle.fingerfing.client.widget_old.effect;

public class EColor {
	
	private int red, green, blue;
	
	private String str;

	public EColor(int red, int green, int blue) {
		if (red>255||green>255||blue>255){
			throw new IllegalEColor();
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.str = "rgb("+red+", "+green+", "+blue+")";
				//String.format("rgb(%d, %d, %d)", red, green, blue); - doesn't emulate
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
