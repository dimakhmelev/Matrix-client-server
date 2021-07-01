package algorithms;

import server.DiagonalMatrix;
import server.Igraph;
import server.Index;
import server.Matrix;

import java.util.*;

public class Dfs<T extends Igraph<R>, R> {

    T graph;

    public Dfs(T graph) {
        this.graph = graph;
    }

    /**
     * given a node in the graph(type R), return all neighbors which this node
     * belong to.
     * @param node node in the graph
     * @return all the connected nodes to the given node
     */
    public Set<R> getSccByNode(R node) {

        /**
         * algorithm explanation: Classic Dfs:
         * 1. enter "start" to stack
         * 2.while stack is not empty do:
         * 3.push all the curr node to stack and add to visited.
         * 4.return visited.
         */

        Set<R> visited = new HashSet<>(); //every node we visit will be entered, help to avoid double check
        Stack<R> stack = new Stack<>();

        stack.add(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            R curr = stack.pop();

            LinkedList<R> currNeighbors = (LinkedList<R>) this.graph.getReachables(curr);
            if (currNeighbors.isEmpty()) {
                continue;
            } else {
                for (R i : currNeighbors) {
                    if (visited.contains(i)) {
                        continue;
                    }
                    stack.add(i);
                    visited.add(i);
                }
            }

        }
        return visited;
    }

    /**
     * return the connected component in the graph
     * @return list of sets of nodes
     */
    public List<Set<R>> getAllScc() {

        /**
         * 1. get all nodes.
         * 2.start exploring scc randomly (getSccByNode) and add to allScc. each list is subtracted from all nodes
         * 3. return allScc
         */

        List<Set<R>> allScc = new LinkedList<>();
        List<R> allNodes= (LinkedList<R>) this.graph.getAllNodes();

        while (!allNodes.isEmpty()) {

            R curr = ((LinkedList<R>) allNodes).getFirst();
            Set<R> listToPush = this.getSccByNode(curr);

            for (R i : listToPush) {
                allNodes.remove(i);

            }
            allScc.add(listToPush);
        }

        allScc.sort((Comparator.comparingInt(Set::size))); //sorting by size (high to low)
        Collections.reverse(allScc);
        return allScc;

    }




}
