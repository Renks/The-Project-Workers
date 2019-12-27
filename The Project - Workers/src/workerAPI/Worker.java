package workerAPI;

import java.util.ArrayList;

import gui.views.searchview.SearchResultLbl;
import javafx.application.Platform;

public class Worker {

	private int id;
	private String name;
	private String address;
	private String city;
	private int hoursWorked;
	private double hourlyRate;

	ArrayList<String> currentEntry = new ArrayList<String>();
	WorkerFile workerFile = new WorkerFile();

	public Worker() {
		try {
			currentEntry = workerFile.DATABASE.get(0);
			this.id = Integer.parseInt(currentEntry.get(0));
			this.name = currentEntry.get(1);
			this.address = currentEntry.get(2);
			this.city = currentEntry.get(3);
			this.hoursWorked = Integer.parseInt(currentEntry.get(4));
			this.hourlyRate = Double.parseDouble(currentEntry.get(5));
		} catch (IndexOutOfBoundsException e) {
			//System.err.println("Worker-Constructor-Error");
			Platform.exit();
			System.out.flush();
			System.exit(1);
		}
	}

	private int entryCount = 0;

	/**
	 * Get a particular entry, Used in SearchView when label is clicked
	 * @return
	 */
	public void getEntry(int pos) {
		
		if(pos!=-1 && pos<workerFile.DATABASE.size()) {
			// If not invalid position
			entryCount = pos;
			update();
		}
		
	}
	
	public boolean getFirstEntry() {

		if (entryCount == 0) {
			return false;
		} else {
			entryCount = 0;
			update();
			return true;
		}

	}

	public boolean getNextEntry() {
		try {
			entryCount++;
			update();
			return true;
		} catch (IndexOutOfBoundsException e) {
			if (entryCount >= workerFile.DATABASE.size()) {
				entryCount = workerFile.DATABASE.size() - 1;
			}
			return false;
		}
	}

	public boolean getPreviousEntry() {
		try {
			entryCount--;
			update();
			return true;
		} catch (IndexOutOfBoundsException e) {
			if (entryCount < 0) {
				entryCount = 0;
			}
			return false;
		}
	}

	public boolean getLastEntry() {

		if (entryCount == workerFile.DATABASE.size() - 1) {
			return false;
		} else {
			entryCount = workerFile.DATABASE.size() - 1;
			update();
			return true;
		}

	}

	private void update() {

		currentEntry = workerFile.DATABASE.get(entryCount);
		this.id = Integer.parseInt(currentEntry.get(0));
		this.name = currentEntry.get(1);
		this.address = currentEntry.get(2);
		this.city = currentEntry.get(3);
		this.hoursWorked = Integer.parseInt(currentEntry.get(4));
		this.hourlyRate = Double.parseDouble(currentEntry.get(5));

	}

	public int add(String name, String address, String city, String hoursWorked, String hourlyRate) {
		int id = Integer.parseInt(workerFile.DATABASE.get(workerFile.DATABASE.size() - 1).get(0)) + 1; // Get the last id and plus one
		
		String line = id + "," + name + "," + address + "," + city + "," + hoursWorked + "," + hourlyRate;
		
		if (workerFile.write(line)) {
			// Clear the database before loading
			workerFile.DATABASE.clear();
			// Now load
			workerFile.load();
			// update the currentEntry
			update();
			return id;
		} else {
			return -1; // return id as -1 if could not write to file.
		}
	}

	public void updateDb(String id, String name, String address, String city, String hoursWorked, String hourlyRate) {

		workerFile.DATABASE.get(entryCount).set(0, id);
		workerFile.DATABASE.get(entryCount).set(1, name);
		workerFile.DATABASE.get(entryCount).set(2, address);
		workerFile.DATABASE.get(entryCount).set(3, city);
		workerFile.DATABASE.get(entryCount).set(4, hoursWorked);
		workerFile.DATABASE.get(entryCount).set(5, hourlyRate);
		workerFile.updateFile();
	}

	public void deleteDb() {
		workerFile.DATABASE.remove(entryCount);
		// Actually updating the file with DATABASE as input
		workerFile.updateFile();
		// Set the counter to last
		entryCount = workerFile.DATABASE.size() - 1;
		// Clear the database before loading
		workerFile.DATABASE.clear();
		// Now load
		workerFile.load();
		// update the currentEntry
		update();
	}

	public ArrayList<SearchResultLbl> search(String searchBy, String searchTxt) {
		// searchBy Values: "Id","Name","City","Hours Worked"
		int searchIndex = -1;
		if (searchBy.equals("Id")) {
			searchIndex = 0;
		}else if(searchBy.equals("Name")) {
			searchIndex = 1;
		}else if(searchBy.equals("City")) {
			searchIndex = 3;
		}else if(searchBy.equals("Hours Worked")) {
			searchIndex = 4;
		}else {
			// Invalid searchBy string, setting searchIndex back to 0
			// This else block should never run unless user has messed up the application
			gui.AlertBox.alertError("Invalid search", "You provided an invalid search by string.");
			searchIndex = 0;
		}
		
		ArrayList<SearchResultLbl> results = new ArrayList<SearchResultLbl>();
		
		for(int i=0; i<workerFile.DATABASE.size(); i++) {
			if(workerFile.DATABASE.get(i).get(searchIndex).equals(searchTxt)) {
				SearchResultLbl searchResultLbl = new SearchResultLbl(workerFile.DATABASE.get(i).get(1)); // Always get the name
				searchResultLbl.setPos(i);
				results.add(searchResultLbl);
			}
		}
		
		return results;
		
	}
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getWeeklyWage() {
		return (this.hoursWorked * this.hourlyRate);
	}
}
