import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IterativeDeepeningSearch {
	
	private Node startNode;
	private int depthLimit;
	private static int totalExploredNodes = 0;
	private int currentTotalNodesExplored = 0;
	
	
	public IterativeDeepeningSearch(Node start,int depthLimit) {
		this.startNode = start;
		this.depthLimit = depthLimit;
	}
	
	public boolean compute() {
		
		
		
		if(startNode.getBeforeList().isEmpty()) {
			System.out.println("Starting Node is empty");
			return true;
		}
		
		Stack<Node> stack = new Stack<Node>();
		stack.push(this.startNode);
		
		LinkedList<LinkedList<Integer>> closedList = new LinkedList<LinkedList<Integer>>();
		
		while(!stack.isEmpty() ) {
		
			Node current = stack.pop();
			totalExploredNodes++;
			currentTotalNodesExplored++;
			
			if(isExplored(current,closedList)) {
				continue;
			}
			else {
				closedList.add(current.getBeforeList());
			}
			
			if(current.getBeforeList().isEmpty()) {
				
				System.out.println("Finished");
				System.out.println("Initial Node-> " + startNode.getStringNode());
				System.out.println(current.traceNodeHistory());
				System.out.println("Total Time of Walking: " + current.getTotalTimeOfWalking() + " minutes");
				System.out.println("Total Nodes explored in the current Tree: " + currentTotalNodesExplored);
				System.out.println("Total Nodes explored: " + totalExploredNodes);
				System.out.println("Total Nodes created: " + startNode.getTotalCreatedNodes());
				return true;
			}
			else if(current.getDepth() <= depthLimit){
				
				stack.addAll(current.getChildren());
				
			}
			
		}
		
		return false;
	}//end of compute()

	public boolean isExplored(Node node, LinkedList<LinkedList<Integer>> closedList) {
		
		LinkedList<Integer> one =  node.getBeforeList();
		LinkedList<Integer> two;
		
		for(int i = 0 ; i < closedList.size(); i++) {
			two = closedList.get(i);
			
			 if (one == null && two == null){
			        return true;
			    }

			    if((one == null && two != null) 
			      || one != null && two == null
			      || one.size() != two.size()){
			        return false;
			    }

			    //to avoid messing the order of the lists we will use a copy
			    //as noted in comments by A. R. S.
			    one = new LinkedList<Integer>(one); 
			    two = new LinkedList<Integer>(two);   

			    Collections.sort(one);
			    Collections.sort(two);   
			    
			    if( one.equals(two)) {
			    	return true;
			    }
			
		}
		return false;
	}//end of isExplored
}
