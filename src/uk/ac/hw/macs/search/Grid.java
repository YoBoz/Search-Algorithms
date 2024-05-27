package uk.ac.hw.macs.search;

/**
 * Class that implements State interface  
 */

public class Grid implements State {

    private final String coord;
    private final int heuristic;
    private final boolean goal;
    private final int cost;

    public Grid(String value, int cost, int heuristic, boolean goal) {
        this.coord = value;
        this.heuristic = heuristic;
        this.cost = cost;
        this.goal = goal;
    }

    /**
     * @return goal or not
     */
    @Override
    public boolean isGoal(){
    	return this.goal; 
    	}

    /**
     * @return heuristic values
     */
    @Override
    public int getHeuristic(){ 
    	return this.heuristic; 
    	}

    /**
     * @return cost of the block
     */
    public int getCost(){ 
    	return this.cost; 
    	}

    /**
     * @return string value of the grid
     */
    @Override
    public String toString() {
        return "Grid[Coordinates = " + coord + " , Cost = " + cost + " , Heuristic = " + heuristic + " , isGoal = " + goal + "]";
    }
}
