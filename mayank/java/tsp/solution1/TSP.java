package java.tsp.solution1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * This is a solution of Traveling sales person problem, by nearest neighbor algorithm. So first
 * find the nearest node and then find the remaining nodes.
 * 
 * @author mayanka
 *
 */
public class TSP {

    public static void main(String[] args) {

        System.out.println("Enter number of nodes : ");
        Scanner scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine();
            int numberOfNodes = Integer.parseInt(input);
            int[][] nodeMatrix = new int[numberOfNodes][numberOfNodes];
            List<Integer> listOfNodes = new ArrayList<Integer>();            
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = i + 1; j < numberOfNodes; j++) {
                    System.out.println("Enter distance between node " + (i + 1) + " and " + (j + 1) + " : ");
                    nodeMatrix[i][j] = Integer.parseInt(scanner.nextLine());
                    nodeMatrix[j][i] = nodeMatrix[i][j];
                }
                listOfNodes.add(i);
            }            
            // choose a node
            Random r = new Random();
            int randomIndex = r.nextInt(numberOfNodes);
            int currentNode = listOfNodes.get(randomIndex);
            System.out.println("\n Randomly picking " + currentNode + " as start node");
            System.out.print("Route is : " + currentNode);
            solveTSP(currentNode, listOfNodes, nodeMatrix);            
        } catch(NoSuchElementException e) {
            System.err.println("There is no line to read. Program is exiting.");
        } catch(NumberFormatException e) {
            System.err.println("Invalid number entered. Program is exiting");
        } finally {
            scanner.close();
        }
        
    }

    private static void solveTSP(int currentNode, List<Integer> listOfNodes, int[][] nodeMatrix) {
        if (listOfNodes.size() == 1) {
            return;
        }
        // get unvisited nodes
        List<Integer> unVisitedNodes = getUnVisitedNodes(currentNode, listOfNodes);

        // find nearest neighbor
        currentNode = getNearestNode(currentNode, unVisitedNodes, nodeMatrix);
        System.out.print("-->" + currentNode);
        solveTSP(currentNode, listOfNodes, nodeMatrix);
    }

    private static List<Integer> getUnVisitedNodes(final int currentNode, List<Integer> listOfNodes) {
        listOfNodes.removeIf(i -> i == currentNode);
        return listOfNodes;
    }

    private static int getNearestNode(int currentNode, List<Integer> unVisitedNodes, int[][] nodeMatrix) {
        int intermediateValue = nodeMatrix[currentNode][unVisitedNodes.get(0)];
        int index = 0;
        for (int i = 1; i < unVisitedNodes.size(); i++) {
            if (intermediateValue > nodeMatrix[currentNode][unVisitedNodes.get(i)]) {
                intermediateValue = nodeMatrix[currentNode][unVisitedNodes.get(i)];
                index = i;
            }
        }
        return unVisitedNodes.get(index);
    }
}
