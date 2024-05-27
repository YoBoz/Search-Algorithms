package uk.ac.hw.macs.search;


public class Main {

    private static int goal_x;
    private static int goal_y;
    
    /**
     * Method to create nodes from the input grid
     * @param grid - Given grid
     * @param nodegrid - Grid of nodes
     */
    private static void createNode(String[][] grid ,Node[][]nodegrid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y].equals("goal")) {
                	nodegrid[x][y] = new Node(new Grid("[" + y + "," + x + "]", 1, Math.abs(goal_x - x) + Math.abs(goal_y - y), true));
                } else if (grid[x][y].equals("G")) {
                	nodegrid[x][y] = new Node(new Grid("[" + y + "," + x + "]", 3, Math.abs(goal_x - x) + Math.abs(goal_y - y), false));
                } else if (grid[x][y].equals("B")) {
                	nodegrid[x][y] = null;
                } else {
                	nodegrid[x][y] = new Node(new Grid("[" + y + "," + x + "]", 1, Math.abs(goal_x - x) + Math.abs(goal_y - y), false));
                	}
                }
            }
        }

    /**
     * Method to build child relations between nodes in the grid
     * @param nodegrid - Grid of nodes
     */
    private static void buildChildRelations(Node[][] nodegrid) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible directions: Left, Right, Up, Down
        
        for (int x = 0; x < nodegrid.length; x++) {
            for (int y = 0; y < nodegrid[0].length; y++) {
                Node parent = nodegrid[x][y];
                if (parent != null) {
                    for (int[] direction : directions) {
                        int newX = x + direction[0];
                        int newY = y + direction[1];
                        
                        // Check if the new position is within the grid boundaries and is not null
                        if (newX >= 0 && newX < nodegrid.length && newY >= 0 && newY < nodegrid[0].length && nodegrid[newX][newY] != null) {
                            Node child = nodegrid[newX][newY];
                            Grid grid = (Grid) child.getValue();
                            int weight;
                            if (grid.getCost() == 3) {
                              weight = 3;
                            } else {
                              weight = 1;
                            }
                            parent.addChild(child, weight);
                        	}
                    	}
                	}
            	}
        	}
    	}


    public static void main(String[] args) {
    	
    	System.out.println("\nGrid 1 :\n");
    	
    	// First Grid
        String[][] grid1 = {
                {"start", "G", "W", "W", "W", "W"},
                {"W", "G", "B", "W", "G", "W"},
                {"W", "W", "G", "B", "G", "W"},
                {"W", "W", "W", "B", "goal", "W"}
        };

        // Creating a node grid for grid1
        Node[][] nodes1 = new Node[grid1.length][grid1[0].length];

        goal_x = 4; goal_y = 3; // Goal Coordinates

        // Creating nodes within the nodes1
        createNode(grid1,nodes1);

        // Building child relations with all adjacent nodes for all nodes
        buildChildRelations(nodes1);
        
        // SearchOrder
        SearchOrder order = new AStarSearchOrder();

        // Search Problem on grid1 using A* Search
		SearchProblem problem = new SearchProblem(order);
		problem.doSearch(nodes1[0][0]);
        
        System.out.println("\nGrid 2 :\n");
        
        // Second Grid
        String[][] grid2 = {
                {"start", "W", "W", "W", "W"},
                {"G", "B", "B", "B", "W"},
                {"W", "W", "B", "W", "W"},
                {"B", "W", "G", "W", "G"},
                {"W", "W", "W", "W", "goal"}
        };
        
        // Creating a node grid for grid2
        Node[][] nodes2 = new Node[grid2.length][grid2[0].length];
   
        goal_x = 4; goal_y = 4; // Goal Coordinates

        // Creating nodes within the nodes2
        createNode(grid2,nodes2);

        // Building child relations with all adjacent nodes for all nodes
        buildChildRelations(nodes2);

        // Search Problem on grid2 using A* Search
		SearchProblem problem2 = new SearchProblem(order);
		problem2.doSearch(nodes2[0][0]);
        
    }
}
