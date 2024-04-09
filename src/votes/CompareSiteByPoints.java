package votes;

import java.util.Comparator;

/**
* @author GarganGerard - 40061139
*/
public class CompareSiteByPoints implements Comparator<Site> {

	@Override
	public int compare(Site o1, Site o2) {
		// TODO Auto-generated method stub
		return o2.getTotalPoints() - o1.getTotalPoints();
	}
	

}
