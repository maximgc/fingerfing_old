package com.trifle.fingerfing.client.widget;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trifle.fingerfing.client.old.effect.EColor;
import com.trifle.fingerfing.client.old.effect.IllegalEColor;

public class EffectColorTest {

	static private EColor ec; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ec = new EColor(10, 20, 30);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToStringRgbFomat() {
		assertEquals("rgb(10, 20, 30)", ec.toStringRgbFomat());
	}
	
	@Test(expected = IllegalEColor.class)
	public void testToStringRgbFomatException() {
		new EColor(10, 20, 300);
	}

}
