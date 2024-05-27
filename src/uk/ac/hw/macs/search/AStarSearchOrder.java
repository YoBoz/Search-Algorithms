package uk.ac.hw.macs.search;

import java.util.List;
import java.util.Set;

/**
 * Class implementing A* search by implementing the SearchOrder interface
 */

public class AStarSearchOrder implements SearchOrder {
	@Override
    public void addToFringe(List<FringeNode> frontier, FringeNode parent, Set<ChildWithCost> children) {
        for (ChildWithCost child : children) {
            // add to the front of the frontier
            frontier.add(new FringeNode(child.node, parent, child.cost));
        }

        // Sorting the frontier with increasing values of f-value using insertion sort
        for (int i = 1; i < frontier.size(); i++) {
            FringeNode key = frontier.get(i);
            int j = i - 1;
            while (j >= 0 && frontier.get(j).getFValue() > key.getFValue()) {
                frontier.set(j + 1, frontier.get(j));
                j = j - 1;
            }
            frontier.set(j + 1, key);
        }
    }

}

