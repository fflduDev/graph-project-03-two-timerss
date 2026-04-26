
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
		
		    List<GraphNode> visited = new ArrayList<>();
		    List<GraphNode> stack = new ArrayList<>(); 

		    for (GraphNode start : nodeList) {
		    	
		        if (visited.contains(start)) {
		        	
		        	continue;
		        	
		        }

		        List<GraphNode> dfsStack = new ArrayList<>();
		        List<Integer> indexStack = new ArrayList<>();

		        dfsStack.add(start);
		        indexStack.add(0);

		        while (!dfsStack.isEmpty()) {
		        	
		            GraphNode current = dfsStack.get(dfsStack.size() - 1);
		            int idxS = indexStack.get(indexStack.size() - 1);

		            if (!visited.contains(current)) {
		            	
		                visited.add(current);
		                stack.add(current);
		                
		            }

		            List<GraphNode> neighbors = current.getNeighbors();

		            if (idxS < neighbors.size()) {
		                GraphNode neighbor = neighbors.get(idxS);

		                indexStack.set(indexStack.size() - 1, idxS + 1);

		                if (stack.contains(neighbor)) {
		                	
		                    return true; 
		                    
		                }

		                if (!visited.contains(neighbor)) {
		                	
		                    dfsStack.add(neighbor);
		                    indexStack.add(0);
		                    
		                }
		            } else {

		                stack.remove(current);
		                dfsStack.remove(dfsStack.size() - 1);
		                indexStack.remove(indexStack.size() - 1);
		                
		            }
		            
		        }
		        
		    }

		    return false;
		    
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
		GraphNode start = getNode(fromNode.getValue());
	    GraphNode end = getNode(toNode.getValue());

	    if (start == null || end == null) return -1;
	    if (start.equals(end)) return 0;

	    List<GraphNode> visited = new ArrayList<>();
	    List<GraphNode> queue = new ArrayList<>();
	    List<Integer> distance = new ArrayList<>();

	    queue.add(start);
	    distance.add(0);
	    visited.add(start);

	    while (!queue.isEmpty()) {
	    	
	        GraphNode current = queue.remove(0);
	        int dist = distance.remove(0);

	        for (GraphNode neighbor : current.getNeighbors()) {
	        	
	            if (!visited.contains(neighbor)) {
	            	
	                if (neighbor.equals(end)) {
	                	
	                    return dist + 1;
	                    
	                }
	                
	                queue.add(neighbor);
	                distance.add(dist + 1);
	                visited.add(neighbor);
	                
	            }
	            
	        }
	        
	    }

	    return -1;
	    
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode == null || targetToNode == null) {
			return -1;
		}
		
		Set <GraphNode> visitedNodes = new HashSet<>();
		Map <GraphNode, Integer> paths = new HashMap<>();
		
		for (GraphNode node: nodeList) {
			paths.put(node, Integer.MAX_VALUE);
		}
		
		paths.put(targetFromNode, 0);
		
		while (visitedNodes.size() < paths.size()) {
			GraphNode smallestNode = null;
			Integer smallestDistance = Integer.MAX_VALUE;
			
			for (GraphNode key: paths.keySet()) {
				if (!visitedNodes.contains(key)) {
					Integer distance = paths.get(key);
					if (distance < smallestDistance) {
						smallestDistance = distance;
						smallestNode = key;
					}
				}
			}
			
			if (smallestNode.equals(targetToNode)) {
				return smallestDistance;
			}
				else {
					visitedNodes.add(smallestNode);
					for (GraphNode neighbor: smallestNode.getNeighbors()) {
						Integer distance = smallestDistance + getEdgeValue(smallestNode, neighbor);
						if (distance < paths.get(neighbor)) {
							paths.put(neighbor, distance);
						}
					}
				}
			}
			
		
		
		return -1;
	}

 
	 
	
}
