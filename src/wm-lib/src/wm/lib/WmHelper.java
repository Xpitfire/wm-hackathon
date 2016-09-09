package wm.lib;

import java.nio.charset.StandardCharsets;
import java.security.*;

public final class WmHelper {
	
	public static String getHash(String input) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = input.getBytes("UTF-8");
		byte[] result = md.digest(bytes);
		return result.toString();
	}
}
