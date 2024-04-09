package votes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
* @author GarganGerard - 40061139
*/
public class VoteProcessor {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		//read in the votes and create a map of site objects
		Map<String, Site> sites = readIn("votes.csv");
		
		//display menu options to user
		displayMenu(sites);
		
		//close resources
		sc.close();

	}

	private static void displayMenu(Map<String, Site> sites) {
		
		int userChoice = -1;
		do {
			System.out.println("Please select an option from the menu: ");
			System.out.println("1. Print all site details");
			System.out.println("2. Print league table (all sites)");
			System.out.println("3. Print league table (top 5 sites)");
			System.out.println("4. Write results to file");
			System.out.println("5. Quit");
			try {
				userChoice = sc.nextInt();
				switch(userChoice) {
				case 1:
					printSiteDetails(sites);
					System.out.println();
					break;
				case 2:
					printResults(sites);
					System.out.println();
					break;
				case 3:
					System.out.println("Printing top 5 sites by total points..");
					printTop5(sites);
					break;
				case 4:
					System.out.println("Writing results to file in a separate thread...");
					writeToFile("Results.csv", sites);
					break;
				case 5:
					System.out.println("Quitting...");
					break;
					default:
						System.out.println("Invalid menu option!");
				}
			} catch (Exception e) {
				sc.nextLine(); //clear token from the scanner
				System.out.println("Error try again, enter a number");
				//e.printStackTrace();
			}
		} while (userChoice != 5);
		
		
	}

	/**
	 * Prints a league table of results, in desc order by total points
	 * @param sites
	 */
	private static void printResults(Map<String, Site> sites) {
		if(sites==null || sites.size()==0) {
			System.out.println("No votes yet, cant display leaderboard");
		} else {
			//convert to array list for sorting
			ArrayList<Site> siteList = new ArrayList<Site>(sites.values());
			Collections.sort(siteList, new CompareSiteByPoints());
			//System.out.printf("%-30s%-30d%-10d%-10d%-10d%-10d%-10d%n",name,getFirstPlaceVotes(),getSecondPlaceVotes(),getThirdPlaceVotes(),getFourthPlaceVotes(),getFifthPlaceVotes(),getTotalPoints());

			System.out.printf("%-15s%-30s%-15s%-15s%-15s%-15s%-15s%-15s%n","Place","Site name","First votes", "Second votes", "Third votes", "Fourth votes", "Fifth votes","Total points");
			int i = 1;
			for(Site site : siteList) {
				System.out.printf("%-15d",i);
				site.printLeaderBoardDetails();
				i++;
			}
		}
		
	}
	
	/**
	 * Prints all details about the sites and votes
	 * @param sites
	 */
	private static void printSiteDetails(Map<String, Site> sites) {
		if(sites==null || sites.size()==0) {
			System.out.println("No votes yet, cant display leaderboard");
		} else {
			//convert to array list for sorting
			ArrayList<Site> siteList = new ArrayList<Site>(sites.values());
			Collections.sort(siteList, new CompareSiteByPoints());
			
			for(Site site : siteList) {
				site.printDetails();
				System.out.println();
			}
		}
		
	}
	
	/**
	 * Prints the top 5 sites by total points
	 * @param sites
	 */
	private static void printTop5(Map<String, Site> sites) {
		if(sites == null || sites.size()==0) {
			System.out.println("No sites in the list..");
		} else {
			ArrayList<Site> siteList = new ArrayList<Site>(sites.values());
			Collections.sort(siteList, new CompareSiteByPoints());
			int toLoop = sites.size()>=5 ? 5 : sites.size();
			System.out.printf("%-15s%-30s%-15s%-15s%-15s%-15s%-15s%-15s%n","Place","Site name","First votes", "Second votes", "Third votes", "Fourth votes", "Fifth votes","Total points");
			for(int i=0; i<toLoop; i++) {
				System.out.printf("%-15d",(i+1));
				siteList.get(i).printLeaderBoardDetails();
			}
			System.out.println();
		}
	}

	/**
	 * Reads a file and creates site objects, returned in a TreeMap
	 * @param string
	 * @return Key Site name, Value Site object
	 */
	private static Map<String, Site> readIn(String fileName) {
		Map<String, Site> sites = new TreeMap<String, Site>();
		
		File file = new File(fileName);
		
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
			
			String[] parts; //to hold the split cols
			String line = br.readLine(); //throw away headers
			line = br.readLine(); //read first line of data
			
			//loop while line is not null
			while(line!=null) {
				//split
				parts = line.split(",");
				//[5] = site name voting, [7] 1st, [8] 2nd, [9] 3rd, [10] 4th, [11] 5th
				String nameVoting = parts[5];
				String firstVote = parts[7];
				String secondVote = parts[8];
				String thirdVote = parts[9];
				String fourthVote = parts[10];
				String fifthVote = parts[11];
				
				//check if site already exists in the Map for each one which was voted for
				if(!sites.containsKey(firstVote)) {
					sites.put(firstVote, new Site(firstVote));
				}
				
				if(!sites.containsKey(secondVote)) {
					sites.put(secondVote, new Site(secondVote));
				}
				
				if(!sites.containsKey(thirdVote)) {
					sites.put(thirdVote, new Site(thirdVote));
				}
				
				if(!sites.containsKey(fourthVote)) {
					sites.put(fourthVote, new Site(fourthVote));
				}
				
				if(!sites.containsKey(fifthVote)) {
					sites.put(fifthVote, new Site(fifthVote));
				}
				
				//sites now guaranteed to exist, now increment their votes accordingly
				
				sites.get(firstVote).incrementFirstPlaceVotes();
				sites.get(secondVote).incrementSecondPlaceVotes();
				sites.get(thirdVote).incrementThirdPlaceVotes();
				sites.get(fourthVote).incrementFourthPlaceVotes();
				sites.get(fifthVote).incrementFifthPlaceVotes();
				
				//read next line
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Oops something went wrong..");
			e.printStackTrace();
		}
		
		return sites;
	}
	
	private static void writeToFile(String fileName, Map<String, Site> sites) {
		ArrayList<Site> siteList = new ArrayList<Site>(sites.values());
		Collections.sort(siteList, new CompareSiteByPoints());
		ResultWriter rw = new ResultWriter(siteList, fileName);
		Thread th = new Thread(rw);
		th.start();
		System.out.println("Writing finished. Thread terminated");
	}



}
