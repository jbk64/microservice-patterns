package business;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.*;

public class FileStorage {

	
	//append the data to the end of the file
	public void append(String data, String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		byte[] bytes = (data+"\r\n").getBytes();
		try {
			if(!Files.exists(path)) {rewrite(fullPath);}

			byte[] data2 = Files.readAllBytes(path);
			Files.write(path, new byte[0]);
			Files.write(path, add(bytes,data2),StandardOpenOption.APPEND);
							}
		catch (IOException e) {System.out.println(e);}
		}
	
	//find the latest line whose id is given
	public String read(String id,String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
				int startIdIndex = line.indexOf("<idCommande>");
				int endIdIndex = line.indexOf("<",startIdIndex+12);
				if(line.substring(startIdIndex+12,endIdIndex).equals(id) ){
					br.close();
					return line;
				}
				line = br.readLine();
			}
			br.close();
			return line;

		}catch (Exception e) {System.out.println(e);}
		return null;
	}
		
		
	//creates the file f non existent or clears it if existent
	public void rewrite(String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		try {Files.write(path, new byte[0]);}
		catch (IOException e) {System.out.println(e);}
		}
	
	//returns the ID from an event in XML format
		public String getId(String event){
			int startIdIndex = event.indexOf("<id>");
			int endIdIndex = event.indexOf("<",startIdIndex+4);
			return event.substring(startIdIndex+4,endIdIndex);
		}

		
		public byte[] add(byte[] tab1, byte[] tab2) {
			int length = tab1.length < tab2.length ? tab1.length : tab2.length;
			byte[] combined = new byte[tab1.length + tab2.length];

			for (int i = 0; i < combined.length; ++i)
			{
			    combined[i] = i < tab1.length ? tab1[i] : tab2[i - tab1.length];
			}
			
			return combined; 
		}


}
