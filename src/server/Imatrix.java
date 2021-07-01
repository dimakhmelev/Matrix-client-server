package server;

import java.util.Collection;
import java.util.List;

public interface Imatrix {

        /**
         * @param index to be queried
         * @return the actual value it encapsulates
         */
        public Integer getValue(Index index);

        /**
         * @param index
         * @return a list of its neighboring indices.
         * Adjacency logic is delegated to implementing classes
         */
        public List<Index> getAdjacentIndices(Index index);

        /**
         * @param index to return all his rachables indices(neighbors & 1 value)
         * @return collection of all the reachables
         */
        public Collection<Index> getReachables(Index index);

}

