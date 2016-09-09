package wm.test;

import org.junit.Test;

import wm.lib.WmHelper;

import static org.junit.Assert.*;

public class WmLogicTest {
	
	@Test
	public void testMethod() {
		assertTrue(true);
	}
	
	@Test
	public void testMD5() throws Exception{
		String hashvalue = WmHelper.getHash("Windows");
		assertEquals(hashvalue, "[B@5f4da5c3");
	}
}
