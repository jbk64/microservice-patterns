package business;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class FileStorage {

	
	public void append(String data, String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		byte[] bytes = (data+"\r\n").getBytes();
		try {
			if(!Files.exists(path)) rewrite(fullPath);
			byte[] data2 = Files.readAllBytes(path);
			Files.write(path, new byte[0]);
			Files.write(path, add(bytes,data2),StandardOpenOption.APPEND);			
			}
		catch (IOException e) {System.out.println(e);}
		}
	
	//find the first line (where the last version is stored)
	public String read(String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			if(line != null){
					return line;				
			}
			br.close();
			return line;

		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	//find the first line whose id is given
	public String read(String id,String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
				int startIdIndex = line.indexOf("<id>");
				//car dans mon Atom il y'a 2 ids
				int startIdIndex2emId = line.indexOf("<id>",startIdIndex+4);
				int endIdIndex = line.indexOf("<",startIdIndex2emId+4);
				if(line.substring(startIdIndex2emId+4,endIdIndex).equals(id) ){
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
		
	//remplace the oldLine with the newLine
	public void replace(String id,String fileName,String newLine) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String finalTxt=new String();
			String line = br.readLine();
			if(line!=null) {
				int startIdIndex = line.indexOf("<id>");
				int endIdIndex = line.indexOf("<",startIdIndex+4);
				if(line.substring(startIdIndex+4,endIdIndex).equals(id) ){
					line=line.replace(line, newLine);
				}
				finalTxt+=line;
				line = br.readLine();
			}
			while(line != null){
				int startIdIndex = line.indexOf("<id>");
				int endIdIndex = line.indexOf("<",startIdIndex+4);
				if(line.substring(startIdIndex+4,endIdIndex).equals(id) ){
					line=line.replace(line, newLine);
				}
				finalTxt+="\r\n"+line;
				line = br.readLine();
			}
			Files.write(path, new byte[0]);
			byte[] bytes =(finalTxt).getBytes();
			Files.write(path, bytes);
			br.close();

		}catch (Exception e) {System.out.println(e);}
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

		public void save(String filePathActualD, String filePathUndo) throws IOException {
			String fullPathActualD = URLDecoder.decode(filePathActualD, "UTF-8");
			Path pathActualD = Paths.get(fullPathActualD.substring(1, fullPathActualD.length()));	
			String fullPathUndo = URLDecoder.decode(filePathUndo, "UTF-8");
			Path pathUndo = Paths.get(fullPathUndo.substring(1, fullPathUndo.length()));	

			byte[] bytesActalD = Files.readAllBytes(pathActualD);

			Files.write(pathUndo, new byte[0]);
			Files.write(pathUndo, bytesActalD);
		}
		


}
