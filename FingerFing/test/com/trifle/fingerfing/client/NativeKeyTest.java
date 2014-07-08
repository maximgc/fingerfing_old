package com.trifle.fingerfing.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trifle.fingerfing.client.IllegalNativeCode;
import com.trifle.fingerfing.client.NativeKey;

public class NativeKeyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsModificator() {
		NativeKey nk1 = NativeKey.KEY_SHIFT;
		assertTrue(nk1.isModificator());
	}

	@Test
	public void testGetByNativeCode() {
		assertEquals(NativeKey.KEY_B, NativeKey.getByNativeCode(66));
	}
	
	@Test(expected = IllegalNativeCode.class)
	public void testGetByNativeCodeInvalid() {
		NativeKey.getByNativeCode(-1);
	}

}
