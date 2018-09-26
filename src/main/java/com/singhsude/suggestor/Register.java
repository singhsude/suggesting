package com.singhsude.suggestor;

import java.io.*;

public class Register {
	public String Result(String store) {
	        try {   
                        //ClassLoader classLoader = Register.class.getClassLoader();
			//Writer outputStreamWriter = new OutputStreamWriter(new File(/*classLoader.getResourceAsStream(*/"store.txt"/*)*/));//outputStream);
                        PrintWriter p = new PrintWriter(process.env.OPENSHIFT_DATA_DIR + registered.txt");
			p.write(store + "\n\n");
			p.close();
        	} catch (Exception e) {
			return "false";
	        }

		return "true";
	}
}