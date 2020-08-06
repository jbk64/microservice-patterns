package services;

import java.io.*;


/**
lit dans un fichier text où un compteur est incrémenté ,

*/
public class Database {

  public String read (String fileName) {
    try {
	    InputStream is = Database.class.getClassLoader().getResource("cpt.txt").openStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is)); 
	    String line = reader.readLine();
      return line.toString();
    }
    catch (FileNotFoundException e0) {e0.printStackTrace(); return "9";}
    catch (IOException e1) {e1.printStackTrace(); return "9";}
    catch(NullPointerException e) {
    	System.out.println("nullll");
    	return "9";}
  } // read

    
  public void initialiser () {
	    try {
	    	
		  String resourceT = Database.class.getClassLoader().getResource("cpt.txt").getPath().toString();
			File x = new File(resourceT);
		    //System.out.println(x.getAbsolutePath());
		    	PrintWriter pw=new PrintWriter(x);
		    	pw.println("0");
		    	pw.flush();
		    	pw.close();

	    }
	    catch (FileNotFoundException e0) {e0.printStackTrace(); }
	    catch (IOException e1) {e1.printStackTrace(); }
	  } // inc

  
  public  int incRead(){
	   try {
		  String resourceT = Database.class.getClassLoader().getResource("cpt.txt").getPath().toString();
			File x = new File(resourceT);
		   // System.out.println(x.getAbsolutePath());
		    	Integer cpt=Integer.parseInt(read("cpt.txt").trim());		    	
		    	PrintWriter pw=new PrintWriter(x);
		    	pw.println((++cpt).toString());
		    	pw.flush();
		    	pw.close();
		    	
		    	return cpt;
	    }
	    catch (FileNotFoundException e0) {e0.printStackTrace(); }
	    catch (IOException e1) {e1.printStackTrace(); 
	    }
	return 15;
  }

} // ReadFile