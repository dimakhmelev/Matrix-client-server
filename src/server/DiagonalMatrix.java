package server;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class DiagonalMatrix extends AbstractMatrix implements Serializable, Igraph<Index> {

    public DiagonalMatrix(int[][] oArray) {
       super(oArray);

    }

    public DiagonalMatrix(Integer[][] oArray) {
        super(oArray);
    }

    @Override
    public List<Index> getAdjacentIndices(Index index) {
        List<Index> list = new ArrayList<>();
        int extracted;
        try{
            extracted = primitiveMatrix[index.getRow() - 1][index.getColumn()-1];
            list.add(new Index(index.getRow() - 1,index.getColumn()-1));
        } catch (ArrayIndexOutOfBoundsException outOfBounds){}

        try{
            extracted = primitiveMatrix[index.getRow() + 1][index.getColumn()+1];
            list.add(new Index(index.getRow() +1 ,index.getColumn()+1));
        } catch (ArrayIndexOutOfBoundsException outOfBounds){}

        try{
            extracted = primitiveMatrix[index.getRow()+1][index.getColumn()-1];
            list.add(new Index(index.getRow()+1 ,index.getColumn()-1));
        } catch (ArrayIndexOutOfBoundsException outOfBounds){}

        try{
            extracted = primitiveMatrix[index.getRow()-1][index.getColumn()+1];
            list.add(new Index(index.getRow()-1 ,index.getColumn()+1));
        } catch (ArrayIndexOutOfBoundsException outOfBounds){}
        try {
            extracted = primitiveMatrix[index.row + 1][index.column];
            list.add(new Index(index.row + 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column + 1];
            list.add(new Index(index.row, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row - 1][index.column];
            list.add(new Index(index.row - 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column - 1];
            list.add(new Index(index.row, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return list;
    }

    public Collection<Index> getReachables(Index index) {
        LinkedList<Index> filteredIndices = new LinkedList<>();
        this.getAdjacentIndices(index).stream().filter(i -> getValue(i) == 1)
                .map(neighbor -> filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }



}
