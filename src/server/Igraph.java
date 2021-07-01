package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public interface Igraph <T>  {

    public Collection<T> getReachables(T node);
    public Object getValue(T node);
    public Collection<T> getAllNodes();

    }


