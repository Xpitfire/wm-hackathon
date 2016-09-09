package wm.test;

import java.util.UUID;

public final class TestHelper {

	public static String getRandomString(int length) {
		String randomStr = UUID.randomUUID().toString();
		while (randomStr.length() < length) {
			randomStr += UUID.randomUUID().toString();
		}
		return randomStr.substring(0, length);
	}

}
