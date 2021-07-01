package server;


import algorithms.Bfs;

import java.util.*;

/**
 * This abstract class is the superclass of all classes representing
 * a matrix comprised of a two-dimensional integer array.
 *
 * It provides skeletal implementations of one IMatrix
 *  * operation and common data fields for all implementing matrices
 */
public abstract class AbstractMatrix implements Imatrix , Igraph<Index>{

    protected int[][] primitiveMatrix;

    // Default constructor
    public AbstractMatrix(){
        this(5,5);
    }

    public AbstractMatrix(int[][] oArray) {
        primitiveMatrix = Arrays
                .stream(oArray)
                .map(row -> row.clone())
                .toArray(value -> new int[value][]);
    }


    /**
     *
     * @param oArray given a matrix of type Integer, turn it into int using Integer.getValue
     *               and init the permitive matrix member.
     */
    public AbstractMatrix(Integer[][] oArray) {

        int[][] primitive = new int[oArray.length][oArray[0].length];
        for (int i = 0; i < oArray.length; i++) {
            for (int j = 0; j <oArray[0].length ; j++) {
                primitive[i][j] = oArray[i][j].intValue();
            }
        }
        primitiveMatrix = Arrays
                .stream(primitive)
                .map(row -> row.clone())
                .toArray(value -> new int[value][]);


    }

    /**
     * Constructor for invocation by subclass constructors, typically implicit
     * @param nRows number of rows
     * @param nColumn number of columns
     */
    public AbstractMatrix(int nRows, int nColumn){
        Random generator = new Random();
        primitiveMatrix = new int[nRows][nColumn];
        for(int i =0; i < primitiveMatrix.length; i++){
            for(int j=0; j < primitiveMatrix[0].length; j++){
                primitiveMatrix[i][j] =  generator.nextInt(2);
            }
        }
    }

    /**
     * @param index to be queried
     * @return the value retrieved for the specified index
     */
    @Override
    public Integer getValue(final Index index) {
        assert(index != null);
        return (Integer) primitiveMatrix[index.getRow()][index.getColumn()];
    }

    /**
     * A subclass must provide an implementation of this method.
     * @param index to be queried
     * @return neighboring indices of the given index;
     */
    public abstract List<Index> getAdjacentIndices(final Index index);

    /**
     * This method prints the Matrix object to the standard output
     * in a row-by-row fashion
     */
    public void printMatrix() {
        // print by rows, not by indices
        for(int[] row : primitiveMatrix){ // each row contains integers
            // convert each integer row to string
            String rowString = Arrays.toString(row);
            System.out.println(rowString);
        }
        System.out.println("\n");
    }

    /**
     * This method returns a string representation of the object.
     * It uses a StringBuilder for optimization. For large enough inputs, Heap space will be depleted
     * due to the immutability of String objects in Java
     * @return a string containing each row in the encapsulated 2 dimensional array
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int[] row : primitiveMatrix){
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public List<Index> getAllNodes() {
        List<Index> allNodes = new LinkedList<>();

        for (int i = 0; i <this.primitiveMatrix.length ; i++)
        {
            for (int j = 0; j <this.primitiveMatrix[0].length ; j++) {
                if (this.primitiveMatrix[i][j] == 1) {
                    allNodes.add(new Index(i, j));
                }

            }

        }
        return allNodes;
    }

    public abstract Collection<Index> getReachables(Index index);

    public Boolean isSizeValid(int maximum) {
        if (this.primitiveMatrix.length > maximum
                || this.primitiveMatrix[0].length > maximum) {
            return false;
        }
        return true;

    }

    public final int[][] getPrimitiveMatrix() {
        return primitiveMatrix;
    }

    /**
     * @param ObjectMatrix matrix of type integer
     * @return matrix of int (the same values)
     */
    public int[][] getPremitiveMatrix(Integer[][] ObjectMatrix) {

        int[][] primitive = new int[ObjectMatrix.length][ObjectMatrix[0].length];
        for (int i = 0; i < ObjectMatrix.length; i++) {
            for (int j = 0; j <ObjectMatrix[0].length ; j++) {
                primitive[i][j] = ObjectMatrix[i][j].intValue();
            }
        }


        return primitive;
    }


}