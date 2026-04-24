
package graph_template;

import java.util.*;
 

public class ListBasedDiGraph implements DiGraph {
	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		if (nodeList.contains(node)) {
			return false;
		}
		nodeList.add(node);
		return true;
	}

	@Override
	public Boolean removeNode(GraphNode node) {
		// TODO Auto-generated method stub
		return nodeList.remove(node);
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		if (!nodeList.contains(node)) {
			return false;
		}
		node.setValue(newNodeValue);
		return true;
	}

	@Override
	public String getNodeValue(GraphNode node) {
		return node.getValue();
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		
		//GOOD
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
	 	
		if (targetFromNode == null || targetToNode == null) {
			return null;
		}
		
		
		targetFromNode.addNeighbor(targetToNode, weight);
		return true;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		return fromNode.removeNeighbor(toNode);
		// TODO Auto-generated method stub
	
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode == null || targetToNode == null) {
			return false;
		}
		
		return targetFromNode.setEdgeWeight(targetToNode, newWeight);
	
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode == null || targetToNode == null) {
			return null;
		}
		
		return targetFromNode.getDistanceToNeighbor(targetToNode);
		
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		if (node == null|| node.getNeighbors() == null) {
			return new ArrayList<>();
		}
		
		return node.getNeighbors();
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode == null || targetToNode == null) {
			return false;
		}
		
		return targetFromNode.getNeighbors() != null && 
			   targetFromNode.getNeighbors().contains(targetToNode);
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode == null || targetToNode == null) {
			return false;
		}
		
		Queue <GraphNode> queue = new LinkedList<>();
		Set <GraphNode> visitedNodes = new HashSet<>();
		
		queue.add(targetFromNode);
		visitedNodes.add(targetFromNode);
		
		while (!queue.isEmpty()) {
			GraphNode current = queue.remove();
			if (current.equals(targetToNode)) {
				return true;
			}
			
			for (GraphNode neighbor: current.getNeighbors()) {
				if (!visitedNodes.contains(neighbor)) {
					visitedNodes.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
		
		return false;
	}

	@Override
	public Boolean hasCycles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GraphNode> getNodes() {
		return nodeList;
	}

	@Override
	 
	public GraphNode getNode(String nodeValue) {
		for (GraphNode thisNode : nodeList) {
			if (thisNode.getValue().equals(nodeValue))
				return thisNode;
		}
		return null;
	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

 
	 
	
}
