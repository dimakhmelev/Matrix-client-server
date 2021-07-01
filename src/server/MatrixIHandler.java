package server;

import algorithms.Bfs;
import algorithms.Dfs;
import algorithms.MatrixAlgo;
import sun.swing.BakedArrayList;

import java.io.*;
import java.util.*;

public class MatrixIHandler implements IHandler {

    private DiagonalMatrix matrix;
    private Index start, end;

    public MatrixIHandler() {
        this.resetParams();
    }

    private void resetParams() {
        this.matrix = null;
        this.start = null;
        this.end = null;
    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        this.resetParams();

        boolean dowork = true;
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop": {
                    dowork = false;
                    break;
                }
                case "matrix": {
                    Object object = objectInputStream.readObject();
                    if (object instanceof Integer[][] ) {
                        this.matrix = new DiagonalMatrix((Integer[][]) object);
                    }else {
                        this.matrix = new DiagonalMatrix((int[][]) object);
                    }
                  this.matrix.printMatrix();

                    break;
               }
                case "start index": {
                    this.start = (Index) objectInputStream.readObject();
                    break;
                }
                case "end index": {
                    this.end = (Index) objectInputStream.readObject();
                    break;
                }
                case "shortest paths": {

                    List<List<Index>> paths = new LinkedList<>();
                    if (!this.matrix.isSizeValid(50)) {
                        objectOutputStream.writeObject(paths);
                      // throw new Exception("Matrix is Over 50X50, please try a new matrix"); // this line is optional : kill the clients' thread

                    } else {
                        Bfs<DiagonalMatrix, Index> bfs = new Bfs<DiagonalMatrix, Index>(this.matrix);
                        paths = bfs.findShortestPaths(this.start, this.end);
                        objectOutputStream.writeObject(paths);

                    }

                    break;
                }

                case "all scc": {

                    Dfs<DiagonalMatrix, Index> dfs = new Dfs<DiagonalMatrix, Index>( this.matrix);

                    List<Set<Index>> allScc = new LinkedList<>();
                    allScc = dfs.getAllScc();
                    objectOutputStream.writeObject(allScc);


                    break;
                }
                case "submarines": {

                    MatrixAlgo matrixAlgo = new MatrixAlgo(matrix);
                    Dfs<DiagonalMatrix, Index> dfs = new Dfs<DiagonalMatrix, Index>( this.matrix);
                    Integer NumberOfValidSubmarines = matrixAlgo.validSubmarines(dfs.getAllScc());

                    objectOutputStream.writeObject(NumberOfValidSubmarines);


                    break;
                }
                case "all paths": {



                    Bfs<DiagonalMatrix, Index> bfs = new Bfs<DiagonalMatrix, Index>(this.matrix);

                    List<List<Index>> paths = new LinkedList<>();
                    paths = bfs.findAllPaths(this.start, this.end);
                    objectOutputStream.writeObject(paths);


                    break;
                }
            }
        }
    }
}