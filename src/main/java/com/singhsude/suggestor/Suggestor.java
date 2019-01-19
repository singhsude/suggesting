package com.singhsude.suggestor;

import java.io.*;

public class Suggestor {

    public String keys = "";
    public String concrete = "";
    public String nouns = "";
    public String match = "";
    public String result2 = "";
    public String[] files = {"adjectives/1syllableadjectives.txt", "adjectives/2syllableadjectives.txt", "adjectives/3syllableadjectives.txt", "adjectives/4syllableadjectives.txt", "adjectives/28K adjectives.txt", "adverbs/1syllableadverbs.txt", "adverbs/2syllableadverbs.txt", "adverbs/3syllableadverbs.txt", "adverbs/4syllableadverbs.txt", "adverbs/6K adverbs.txt", "nouns/1syllablenouns.txt", "nouns/2syllablenouns.txt", "nouns/3syllablenouns.txt", "nouns/4syllablenouns.txt", "nouns/91K nouns.txt", "verbs/1syllableverbs.txt", "verbs/2syllableverbs.txt", "verbs/3syllableverbs.txt", "verbs/4syllableverbs.txt", "verbs/31K verbs.txt"};
    
    public String Result(String arg) {
	    
        String[] words = arg.toLowerCase().replace("?", "").replace(".", "").replace(";", "")/*.replace("'", " ")*/.replace("\"", " ").replace("@", " ").replace("#", "").replace("ing", "e").replace("es", "").split(" ");//strLine.split(" ");
	
	boolean isTrue = false;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];//"car";
	    
            for (int j = 0; j < files.length; j++) {
		String file = "/dictionary/" + files[j];
		if((isTrue = readFile(file, word)))
			break;/*
		for (int j = 0; j < testfiles.length; j++) {
		String file = testfiles[j];
		if(readFile(file, word))
			break;*/
	    }
        }
	
        String result = "";
        String[] tokens = keys.split(" ");
        int noun = 0;
        int adj = 0;
        int verb = 0;
        int adve = 0;
        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i].contains("adjective")) {
                adj++;
            } else if (tokens[i].contains("adverb")) {
                adve++;
            } else if (tokens[i].contains("verb")) {
                verb++;
            } else if (tokens[i].contains("noun")) {
                noun++;
            }

        }

        //System.out.println(adve);

        if (adj == 1 || adve >= 2 || verb >= 3)//2 adverbs makes 't further in, but/as old and child has many common similarities..
        {
            result = "child";
        } else if (adve >= 1) {
            result = "old";
        } else if (verb >= 1) {
            result = "elder";
        } else if (noun >= 1) {
            result = "youth";
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];//"car";
            if (!match.contains(word))// && word.length() > 2)
            {
                concrete += word + " ";
            }
        }

        //System.out.println ("object: " + concrete);
        String[] keys2 = nouns.split(" ");
        String key = "";
        int len = 0;
        for (int i = 0; i < keys2.length; i++) {
            if (keys2[i].length() >= len) {
                key = keys2[i];
                len = keys2[i].length();
            }
        }
			//else

        //System.out.println("concrete: " + key);
        //System.out.println("age: " + result);

        result2 += "concrete: " + key + "<br>";
        result2 += "age: " + result + "<br>";
        
	    if(isTrue)
		    result2 += "would you like to go for:";
		else
			return result2;
	    
        String strLine = "";
        FileInputStream fstream;
        InputStream in;
        BufferedReader br;
        try {
	    String absolutePath = System.getenv("OPENSHIFT_DATA_DIR");
            fstream = new FileInputStream(absolutePath + "register.txt");
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
		//ClassLoader cl = this.getClass().getClassLoader();
		//in = cl.getResourceAsStream("register.txt");
		//br = new BufferedReader(new InputStreamReader(in));
		//fstream = new FileInputStream("register.txt");
            	//in = new DataInputStream(fstream);
            	//br = new BufferedReader(new InputStreamReader(in));
			String[] strLines = new String[6];
			int counter = 0;
			boolean objectMatch = false;
			boolean ageMatch = false;
                        while ((strLine = br.readLine()) != null) {
			//for(int i = 0; i < Util.getSize(); i++){
			
				//String[] tkns = Util.getString(i).split("\n");
			//for(int j = 0; j < tkns.length; j++){
			
				//strLine = tkns[j];
			    if(strLine.length() > 2)
			    {
				if(counter < 6)
					strLines[counter++] = strLine;
				if(counter >= 6)
					counter = 0;
			    }

                            // Print the content on the console
			 for(int k = 0; k < keys2.length; k++) {
				    
                            if (strLine.trim().toLowerCase().equals(result.trim().toLowerCase())){
                                ageMatch = true;
                            }
			    if (strLine.trim().toLowerCase().contains(keys2[k].trim().toLowerCase())) {
                                objectMatch = true;				
                            }
				    
			    if(strLine.trim().toLowerCase().contains("http:"))
				counter = 0;
			    if(objectMatch && ageMatch)
	         	    {
				result2 += "<br>" + strLines[0] + "\n" + strLines[5] + "<br><br>";//getStmt(strLines);
				ageMatch = false;
				objectMatch = false;
				    break;
			    }
            		//}
			 }
				if(objectMatch && ageMatch)
	         	    	{
					ageMatch = false;
					objectMatch = false;
				    	break;
			    	}
			}
            //Close the input stream
            in.close();
        } catch (Exception e) {
	    return "false";
        }

        return result2;
        //System.out.println (strLine);
    }

    public void listFilesForFolder(final File folder, String word) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, word);
            } else {
                if (readFile(fileEntry.getPath(), word)) {
                    return;
                }
            }
        }
    }
	
    public boolean readFile(String fileName, String word) {
        String strLine;
        BufferedReader br;
        try {
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//InputStream in = Suggestor.class.getResourceAsStream("com/singhsude/suggestor" + fileName);
            //br = new BufferedReader(new InputStreamReader(in));
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream in = cl.getResourceAsStream("com/singhsude/suggestor" + fileName);
		br = new BufferedReader(new InputStreamReader(in));
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                if (strLine.trim().equals(word))//strLine.trim().contains(word))
                {
                    if (fileName.contains("noun")) {
                        nouns += word + " ";
                    }
                    //System.out.println(fileName + " " + word);
                    keys += fileName + " ";
                    match += word + " ";
                    return true;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            //System.err.println("Error: " + e.getMessage());
        }

        return false;

    }

    public String getStmt(String[] stmts)
    {
        String stmt = "";
        int len = 0;
        for (int i = 0; i < stmts.length; i++) {
            if (stmts[i] != null && stmts[i].length() >= len) {
                stmt = stmts[i];
                len = stmts[i].length();
            }
        }
	return stmt;
    }
}
