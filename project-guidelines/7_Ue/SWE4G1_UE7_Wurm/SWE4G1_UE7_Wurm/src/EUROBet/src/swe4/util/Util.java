package swe4.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {

	static long startTime = System.currentTimeMillis();
	  
	public static Object currTime() {
		return (System.currentTimeMillis() - startTime) / 1000.0;
	}

	  
	public static String getHostPortArg(String[] args) {
		if (args == null ||  args.length == 0) {
			System.out.println("WARNING: host[:port] missing (default is localhost:1099)");
		    return "localhost:1099";
		}
		return args[0];
	}
	  
	public static int getPort(String host_port) {
		int port = 1099;
	    int colonIdx = host_port.lastIndexOf(':');
	    if (colonIdx >= 0)
	      port = Integer.valueOf(host_port.substring(colonIdx + 1));
	    return port;
	}
	  
	public static void unsafeSleep(int millis) {
	    try {
	      Thread.sleep(millis);
	    } catch (InterruptedException ex) {}
	}

	public static void unsafeWait(Object obj) {
	    try {
	      obj.wait();
	    } catch (InterruptedException e) { }
	}

	public static String readline() {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    try {
	      return reader.readLine();
	    } catch (IOException e) { }
	    return null;
	}

}

