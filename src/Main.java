
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("IDS Search Algorithm");
		Node startNode = new Node(STATE.BEFORE_BRIDGE);
		
		int depthLimit = 1;
		IterativeDeepeningSearch ids = new IterativeDeepeningSearch(startNode, depthLimit);
		
		long startTimer = System.currentTimeMillis();
		long stopTimer,totalTimer;
		
		while(!ids.compute()) {
			depthLimit++;
			ids = new IterativeDeepeningSearch(startNode,depthLimit);
		}
		
		stopTimer = System.currentTimeMillis();
		totalTimer = stopTimer - startTimer;
		System.out.println("Runtime Timer: " + totalTimer + " milliseconds");
	}

}
