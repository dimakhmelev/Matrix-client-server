package algorithms;

import server.AbstractMatrix;
import server.Imatrix;
import server.Index;
import server.Matrix;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MatrixAlgo {

    Imatrix imatrix;

    public MatrixAlgo(Imatrix concreteMatrix) {
        this.imatrix = concreteMatrix;
    }

    /**
     * given a set of indices, check if those indices are a valid subMarine
     * a subMarine is a set of indices in matrix which all values are 1.
     * example     {1,1,1}, <----
     *             {1,1,1},
     *             {0,0,0}
     * the upper indices set is a square=subMarine
     * @param listToCheck set of indices (probably be a connected components)
     * @return true if the set is subMarine(square)
     */
    public Boolean isSquare(Set<Index> listToCheck) {

        Integer left, right, top, down;
        List<Integer> laftIndeces  = new LinkedList<>();
        List<Integer> rightIndeces = new LinkedList<>();

        for (Index i : listToCheck) {
            laftIndeces.add(i.getRow());
            rightIndeces .add(i.getColumn());
        }

        left = Collections.min(rightIndeces);
        right = Collections.max(rightIndeces);
        top = Collections.min(laftIndeces);
        down = Collections.max(laftIndeces);

        for (int i = top; i <= down; i++) {
            for (int j = left; j <= right; j++) {
                if (this.imatrix.getValue(new Index(i, j)) == 0) {
                    return false;
                }
            }
        }


        return true;
    }

    /**
     * check the number of valid subMarines in given list of sets to check
     * @param allSccList list of sets to check
     * @return number of valid subMarines
     */
    public Integer validSubmarines(List<Set<Index>> allSccList) {
            Integer countValidSubmarines = 0;

        for (Set<Index> i : allSccList) {
            if (this.isSquare(i) && i.size()>=2) {
                countValidSubmarines++;
            }
        }
        return countValidSubmarines;
    }



}
