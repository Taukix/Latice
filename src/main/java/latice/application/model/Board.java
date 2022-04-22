package latice.application.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {
	public ArrayList<ArrayList<Cell>> cells;
	
	private Board() {
		//TODO
		// Need to find the right formula to find bonus cells
		// First guess would be : i + j % 2 && i != 3 && i !=5 && j != 3 && j !=5 && i + j != 6
		// but need confirmation
	}
}
