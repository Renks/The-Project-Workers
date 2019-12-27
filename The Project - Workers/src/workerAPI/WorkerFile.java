package workerAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import gui.AlertBox;


public class WorkerFile {
	private static final File FILE = new File("src/database.txt");
	public final ArrayList<ArrayList<String>> DATABASE = new ArrayList<ArrayList<String>>();
	
	public WorkerFile() {
		load();
	}
	
	protected void load() {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)))) {
			bf.lines().forEach(line->{
				ArrayList<String> lineArr = new ArrayList<String>(Arrays.asList(line.split(",")));
				DATABASE.add(lineArr);
			});
			
		} catch (IOException e) {
			AlertBox.alertError("Error", "Error loading database file.\n"+e.getMessage());
		} catch(Exception e) {
			AlertBox.alertError("Error", "Some unknown error occurred.");
		}
	}
	protected boolean write(String line) {
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE,true)))){
			bw.append("\n"+line);
			return true;
		} catch (FileNotFoundException e) {
			AlertBox.alertError("Error", "Database file not found."+e.getMessage());
			return false;
		} catch (IOException e) {
			AlertBox.alertError("Error", "Error reading database file."+e.getMessage());
			return false;
		}
	}
	
	protected boolean updateFile() {
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE)))){
			String line = "";
			for(int i=0; i<DATABASE.size(); i++) {
				for(int j=0;j<DATABASE.get(i).size(); j++) {
					if(j == DATABASE.get(i).size()-1) {
						line +=DATABASE.get(i).get(j)+"\n";
					}else {
						line +=DATABASE.get(i).get(j)+",";
					}
				}
			}
			bw.write(line);
			return true;
		} catch (FileNotFoundException e) {
			AlertBox.alertError("Error", "Database file not found."+e.getMessage());
			return false;
		} catch (IOException e) {
			AlertBox.alertError("Error", "Error reading database file."+e.getMessage());
			return false;
		}
	}
	
	

}
