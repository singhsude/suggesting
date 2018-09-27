package com.singhsude.suggestor;

import java.io.*;

public class Register {
	public String Result(String store) {
	        try {   
		        String absolutePath = System.getenv("OPENSHIFT_DATA_DIR");
                        PrintWriter p = new PrintWriter(absolutePath + "register.txt");
			p.write(store + "\n\n");
			p.close();
        	} catch (Exception e) {
			return "false";
	        }

		return "true";
	}
}