package com.singhsude.suggestor;

import java.io.*;

public class Register {
	public static String Result(String store) {
	        try {   
                        //ClassLoader classLoader = Register.class.getClassLoader();
			//Writer outputStreamWriter = new OutputStreamWriter(new File(/*classLoader.getResourceAsStream(*/"store.txt"/*)*/));//outputStream);
		        String absolutePath = System.getenv("OPENSHIFT_DATA_DIR");
                        PrintWriter p = new PrintWriter(absolutePath + "registered.txt");
			p.write(store + "\n\n");
			p.close();
        	} catch (Exception e) {
			return "false";
	        }

		return "true";
	}
}