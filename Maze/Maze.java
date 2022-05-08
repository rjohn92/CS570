import java.util.ArrayList;
import java.util.Stack;

public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		// (0, 0) is the start point.
		return findAllMazePaths();
	}

	// wrapper for findAllmazePaths
	public boolean findAllMazePaths() {
		ArrayList<ArrayList<PairInt>> path = findAllMazePaths(0, 0);
		if (path.size() <= 0) {
			System.out.println("No paths found.");
			return false;
		} else {

			System.out.println(path);
			return true;
		}
	}

	/*
	 * x value will represent the number of columns while the y value represents the
	 * number of rows. In the same vein as a x,y graph grid
	 */
	public boolean findMazePath(int x, int y) {
		/*
		 * if x OR y coordinate is less than 0 OR the x value is greater than the number
		 * of columns OR the y value is greater than the number of rows we will return
		 * false.
		 */
		if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
			return false;

		}
		/*
		 * If the current cell being analyzed does not have NON_BACKGROUND, then false
		 * should be returned since there is no possible path through that cell.
		 */
		if (maze.getColor(x, y) != (NON_BACKGROUND)) {
			return false;
		}
		/*
		 * if the x and y value reaches the end of the square we have completed the
		 * maze. (The maze's exit is located at the last square.)
		 */
		if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, PATH);
			return true;
			// otherwise we will recolor the path
		} else {
			maze.recolor(x, y, PATH);

		}
		// this is our recursive step.
		if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
			return true;
		} else {
			maze.recolor(x, y, TEMPORARY);
			return false;
		}

	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		return result;
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}

	// helper method for problem 2
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		/*
		 * if the selected x or y value is less than 0 (impossible in our grid) or
		 * greater than the last grid value on our maze we will have to end our method
		 */
		if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1) {
			return;
		}
		if (maze.getColor(x, y) != NON_BACKGROUND) {
			return;
		}
		/*
		 * if we reach the exit of the maze (the last square is mazeSize-1) we will
		 * create an ArrayListt object of PairInts.
		 */
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			trace.push(new PairInt(x, y));
			/*
			 * create our path arrayList which we will return with a list of traversed
			 * coordinates.
			 */
			ArrayList<PairInt> path = new ArrayList<PairInt>();
			path.addAll(trace);
			// add the path to our result lists
			result.add(path);

			/*
			 * once we have the value added we will just pop it off since it will be
			 * unneeded in the future
			 */
			trace.pop();
			// recolor the path into our temp path color
			maze.recolor(x, y, NON_BACKGROUND);
			return;

		} else {
			/*
			 * if the next value is not within one (going right or down) of the previous
			 * PairInt given then we pop that value off and not use it
			 */
			if (trace.isEmpty() && x != 0 & y != 0) {
				trace.push((new PairInt(x, y)));

			}
			// add the pair to the trace
			trace.push(new PairInt(x, y));
			// recolor the maze the color of the path
			maze.recolor(x, y, PATH);
			// continue to recursively work through the maze
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x - 1, y, result, trace);
			findMazePathStackBased(x, y + 1, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			maze.recolor(x, y, NON_BACKGROUND);

			if (!trace.isEmpty()) {
				trace.pop();
			}

			return;
		}
	}

	// ADD METHOD FOR PROBLEM 3 HERE
	/*
	 * here we will find the index of the shortest path and print it. To do this we
	 * have to find all the maze paths first. Then we will count the elements in
	 * each ArrayList<PairInt>
	 */
	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		int i = 1;
		// create a copy of all mazepaths
		ArrayList<ArrayList<PairInt>> bestPath = findAllMazePaths(x, y);
		if (bestPath.size() > 0) {
			// assign a shortest PairInt that equals best path for 0
			ArrayList<PairInt> shortest = bestPath.get(0);

			// find the size of the smallest list of PairInts
			int minLength = shortest.size();
			
			/*
			 * while i is smaller than the bestPath we will keep iterating over i to find
			 * when/if the current path ever becomes longer than the bestPath if it does not
			 * we have a new min path found
			 */
			while (i < bestPath.size()) {
				/*
				 * these paths are stored in bestPaths. We will find the size of them. If a path
				 * is greater than or equal to the path we got before. We will just stick to the
				 * current path we have on record.
				 */
				if (minLength >= bestPath.get(i).size()) {
					shortest = bestPath.get(i);
					minLength = shortest.size();
					i++;
				}

			}

			return shortest;
			//if there is no path return blank PairInt
		} else {
			return new ArrayList<PairInt>();

		}

	}
}
