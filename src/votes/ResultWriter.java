package votes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
* @author GarganGerard - 40061139
*/
public class ResultWriter implements Runnable {
	
	private ArrayList<Site> sites;
	private String filename;

	/**
	 * @param sites
	 */
	public ResultWriter(ArrayList<Site> sites, String filename) {
		setSites(sites);
		setFilename(filename);
	}


	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}


	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}


	/**
	 * @return the sites
	 */
	public ArrayList<Site> getSites() {
		return sites;
	}


	/**
	 * @param sites the sites to set
	 */
	public void setSites(ArrayList<Site> sites) {
		this.sites = sites;
	}


	@Override
	public void run() {
		File file = new File(filename);
		
		try(FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("Place,Site name, First votes, Second votes, Third votes, Fourth votes, Fifth votes, Total points");
			bw.newLine();
			
			String toWrite;
			int place = 1;
			for(Site site : sites) {
				toWrite = String.format("%d,%s,%d,%d,%d,%d,%d,%d", place,site.getName(),site.getFirstPlaceVotes(),site.getSecondPlaceVotes(),site.getThirdPlaceVotes(),site.getFourthPlaceVotes(),site.getFifthPlaceVotes(),site.getTotalPoints());
				bw.write(toWrite);
				bw.newLine();
				place++;
			}
			System.out.println("File writing complete..");
		} catch (IOException e) {
			System.out.println("Problem when writing!");
			e.printStackTrace();
		}
		
	}

}
