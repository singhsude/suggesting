package com.singhsude.suggestor;

import java.io.*;

public class Register {
	public String Result(String store) {
	        try {   
			if(store.split("\n")[0].length() == 0 || store.split("\n")[1].length() == 0 || store.split("\n")[2].length() == 0 || store.split("\n")[3].length() == 0 || store.split("\n")[4].length() == 0  || store.split("\n")[5].length() == 0)
			{
				return "false";
			}
		        String absolutePath = System.getenv("OPENSHIFT_DATA_DIR");
                        PrintWriter p = new PrintWriter(new FileOutputStream(absolutePath + "register.txt", true));
			p.write(store + "\n\n");
			p.close();
        	} catch (Exception e) {
			return "false";
	        }

		return "true";
	}
}
