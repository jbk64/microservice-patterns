package business;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.*;
import java.util.ArrayList;

public class FileStorage {

	
	//append the data to the end of the file
	public void appendMenu(String data, String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		byte[] bytes = (data+"\r\n").getBytes();
		try {
			if(!Files.exists(path)) {rewrite(fullPath);System.out.println("=========>file created ");}
			Files.write(path, bytes, StandardOpenOption.APPEND);}
		catch (IOException e) {System.out.println(e);}
		}
	
	public void appendService(String data, String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		Path path = Paths.get(fullPath.substring(1, fullPath.length()));
		byte[] bytes = (data+"\r\n").getBytes();
		try {
			int endDataIndex= data.indexOf("-");
			String id=data.substring(0, endDataIndex);
			if(!Files.exists(path)) rewrite(fullPath);
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
				int endTopicIndex = line.indexOf("-");
				if(line.substring(0,endTopicIndex).equals(id) ){
					return;
				}
				line = br.readLine();
			}
			br.close();
			Files.write(path, bytes, StandardOpenOption.APPEND);}
		catch (IOException e) {System.out.println(e);}
		}
	
	// return the first line of the file
	public String read(String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
					return line;
			}
			br.close();
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	//find the latest line whose id is given
	public String read(String id,String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
				int startIdIndex = line.indexOf("<id>");
				int endIdIndex = line.indexOf("<",startIdIndex+4);
				if(line.substring(startIdIndex+4,endIdIndex).equals(id) ){
					br.close();
					return line;
				}
				line = br.readLine();
			}
			br.close();
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
		
	//return the list of url for the specific topic
	public ArrayList<String> readAllok(String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
			String line = br.readLine();
			while(line != null){
				int endTopicIndex = line.indexOf("-");
				if(line.substring(0,endTopicIndex).equals("ok") ){
					result.add(line.substring(endTopicIndex+1,line.length()));
				}
				line = br.readLine();
			}
			br.close();
			return result;
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	//return the list of url for the specific topic
		public ArrayList<String> readAll(String topic,String fileName) throws UnsupportedEncodingException{
			String fullPath = URLDecoder.decode(fileName, "UTF-8");
			ArrayList<String> result = new ArrayList<String>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(fullPath));
				String line = br.readLine();
				while(line != null){
					int endTopicIndex = line.indexOf("-");
					if(line.substring(0,endTopicIndex).equals(topic) ){
						result.add(line.substring(endTopicIndex+1,line.length()));
					}
					line = br.readLine();
				}
				br.close();
				return result;
			}catch (Exception e) {System.out.println(e);}
			return null;
		}
			
		//return the list of Services
		public ArrayList<Service> readAllServices(String fileName) throws UnsupportedEncodingException{
			String fullPath = URLDecoder.decode(fileName, "UTF-8");
			ArrayList<Service> result = new ArrayList<Service>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(fullPath));
				String line = br.readLine();
				while(line != null){
					int endTopicIndex = line.indexOf("-");
						result.add(new Service(line.substring(0,endTopicIndex),line.substring(endTopicIndex+1,line.length())));
					line = br.readLine();
				}
				br.close();
				return result;
			}catch (Exception e) {System.out.println(e);}
			return null;
		}
			
	//creates the file f non existent or clears it if existent
	public void rewrite(String fileName) throws UnsupportedEncodingException{
		String fullPath = URLDecoder.decode(fileName, "UTF-8");
        try {

			Path path = Paths.get(fullPath.substring(1, fullPath.length()));
			Files.write(path, new byte[0]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
        

	
	//returns the ID from an event in XML format
		public String getId(String event){
			int startIdIndex = event.indexOf("<id>");
			int endIdIndex = event.indexOf("<",startIdIndex+4);
			return event.substring(startIdIndex+4,endIdIndex);
		}

}
