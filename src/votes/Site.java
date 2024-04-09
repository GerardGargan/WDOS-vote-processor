package votes;
/**
* @author GarganGerard - 40061139
*/
public class Site {
	private String name;
	private int firstPlaceVotes;
	private int secondPlaceVotes;
	private int thirdPlaceVotes;
	private int fourthPlaceVotes;
	private int fifthPlaceVotes;
	
	
	/**
	 * @param name
	 */
	public Site(String name) {
		this.name = name;
		firstPlaceVotes = 0;
		secondPlaceVotes = 0;
		thirdPlaceVotes = 0;
		fourthPlaceVotes = 0;
		fifthPlaceVotes = 0;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the firstPlaceVotes
	 */
	public int getFirstPlaceVotes() {
		return firstPlaceVotes;
	}
	/**
	 * @param firstPlaceVotes the firstPlaceVotes to set
	 */
	public void incrementFirstPlaceVotes() {
		this.firstPlaceVotes++;
	}
	/**
	 * @return the secondPlaceVotes
	 */
	public int getSecondPlaceVotes() {
		return secondPlaceVotes;
	}
	/**
	 * @param secondPlaceVotes the secondPlaceVotes to set
	 */
	public void incrementSecondPlaceVotes() {
		this.secondPlaceVotes++;
	}
	/**
	 * @return the thirdPlaceVotes
	 */
	public int getThirdPlaceVotes() {
		return thirdPlaceVotes;
	}
	/**
	 * @param thirdPlaceVotes the thirdPlaceVotes to set
	 */
	public void incrementThirdPlaceVotes() {
		this.thirdPlaceVotes++;
	}
	/**
	 * @return the fourthPlaceVotes
	 */
	public int getFourthPlaceVotes() {
		return fourthPlaceVotes;
	}
	/**
	 * @param fourthPlaceVotes the fourthPlaceVotes to set
	 */
	public void incrementFourthPlaceVotes() {
		this.fourthPlaceVotes++;
	}
	/**
	 * @return the fifthPlaceVotes
	 */
	public int getFifthPlaceVotes() {
		return fifthPlaceVotes;
	}
	/**
	 * @param fifthPlaceVotes the fifthPlaceVotes to set
	 */
	public void incrementFifthPlaceVotes() {
		this.fifthPlaceVotes++;
	}
	
	/**
	 * Calculates the total points
	 * @return the total number of points
	 */
	public int getTotalPoints() {
		int firstPoints = getFirstPlaceVotes()*5;
		int secondPoints = getSecondPlaceVotes()*4;
		int thirdPoints = getThirdPlaceVotes()*3;
		int fourthPoints = getFourthPlaceVotes()*2;
		int fifthPoints = getFifthPlaceVotes()*1;
		
		return firstPoints+secondPoints+thirdPoints+fourthPoints+fifthPoints;
	}
	
	/**
	 * Prints the site name and number of each votes
	 */
	public void printDetails() {
		System.out.println("Site: "+getName());
		System.out.println("First place votes: "+getFirstPlaceVotes());
		System.out.println("Second place votes: "+getSecondPlaceVotes());
		System.out.println("Third place votes: "+ getThirdPlaceVotes());
		System.out.println("Fourth place votes: "+getFourthPlaceVotes());
		System.out.println("Fifth place votes: "+getFifthPlaceVotes());
	}
	
	public void printLeaderBoardDetails() {
		System.out.printf("%-30s%-15d%-15d%-15d%-15d%-15d%-15d%n",name,getFirstPlaceVotes(),getSecondPlaceVotes(),getThirdPlaceVotes(),getFourthPlaceVotes(),getFifthPlaceVotes(),getTotalPoints());
	}
	
	
}
