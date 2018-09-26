package com.singhsude.suggestor;

import java.io.*;

public class Suggestor {

    public String keys = "";
    public String concrete = "";
    public String nouns = "";
    public String match = "";
    public String result2 = "";
    public String[] files = {"adjectives/1syllableadjectives.txt", "adjectives/2syllableadjectives.txt", "adjectives/3syllableadjectives.txt", "adjectives/4syllableadjectives.txt", "adjectives/28K adjectives.txt", "adverbs/1syllableadverbs.txt", "adverbs/2syllableadverbs.txt", "adverbs/3syllableadverbs.txt", "adverbs/4syllableadverbs.txt", "adverbs/6K adverbs.txt", "nouns/1syllablenouns.txt", "nouns/2syllablenouns.txt", "nouns/3syllablenouns.txt", "nouns/4syllablenouns.txt", "nouns/91K nouns.txt", "verbs/1syllableverbs.txt", "verbs/2syllableverbs.txt", "verbs/3syllableverbs.txt", "verbs/4syllableverbs.txt", "verbs/31K verbs.txt"};
    public String Result(String args) {
        String[] words = args.toLowerCase().replace("?", "").replace(".", "").replace(";", "")/*.replace("'", " ")*/.replace("\"", " ").replace("@", " ").replace("#", "").replace("ing", "e").replace("es", "").split(" ");//strLine.split(" ");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];//"car";
	    
            for (int j = 0; j < files.length; j++) {
		String file = "/dictionary/" + files[j];
		if(readFile(file, word))
			break;
	    }
	    //InputStream in = new InputStreamReader(FileLoader.class.getResourceAsStream("/dictionary") );

            //final File folder = new File("C://dictionary");
            //listFilesForFolder(folder, word);
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

        result2 += "concrete: " + key + "\n";
        result2 += "age: " + result + "\n";
        
        String strLine = "";
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        try {
		// Open the file that is the first 
            // command line parameter
            //fstream = new FileInputStream("C://dictionary/match.txt");
		//InputStream in1 = new InputStreamReader(FileLoader.class.getResourceAsStream("match.txt") );
            // Get the object of DataInputStream
            //in = new DataInputStream(in1);//fstream);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream("foo.properties");

            br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("match.txt")));//in));
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                if (strLine.contains(key)) {
                    //System.out.println("do you want to go for: " + strLine + " yes or no?");
                    //System.out.println("taking yes!");
                    
                    result2 += "do you want to go for: " + strLine + " yes or no?" + "\n";
                    result2 += "taking yes!" + "\n";

                    try {
				// Open the file that is the first 
                        // command line parameter
                        //fstream = new FileInputStream("C://dictionary/vendors.txt");
			//InputStream in2 = new InputStreamReader(FileLoader.class.getResourceAsStream("vendors.txt") );
                        // Get the object of DataInputStream
                        //in = new DataInputStream(in2);//fstream);
		        br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("vendors.txt")));
                        //Read File Line By Line
                        while ((strLine = br.readLine()) != null) {
                            // Print the content on the console
                            if (strLine.contains(key)) {
                                //System.out.println("would you like to go for: " + strLine);
                                
                                result2 += "would you like to go for: " + strLine;
                                break;
                            }

                        }
                        //Close the input stream
                        //in.close();
                    } catch (Exception e) {//Catch exception if any
                        //System.err.println("Error: " + e.getMessage());
                    }

                    //System.out.println (strLine);
                }

                break;

            }
            //Close the input stream
            //in.close();
        } catch (Exception e) {//Catch exception if any
            //System.err.println("Error: " + e.getMessage());
        }

        return result2;
        //System.out.println (strLine);
    }

    public void listFilesForFolder(final File folder, String word) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, word);
            } else {
//       	            System.out.println(fileEntry.getPath());
                //System.out.println (readFile(fileEntry.getPath(), word));
                if (readFile(fileEntry.getPath(), word)) {
                    return;
                }
            }
        }
    }

    public boolean readFile(String fileName, String word) {
        String strLine;
        FileInputStream fstream;
        DataInputStream in;
        BufferedReader br;
        try {
		// Open the file that is the first 
            // command line parameter
            //fstream = new FileInputStream(fileName);
		//InputStream in3 = new InputStreamReader(FileLoader.class.getResourceAsStream(fileName) );
            // Get the object of DataInputStream
            //in = new DataInputStream(in3);//fstream);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		InputStream input = classLoader.getResourceAsStream("foo.properties");
            br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(fileName)));
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
            //in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return false;

    }
}
