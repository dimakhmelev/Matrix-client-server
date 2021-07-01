package uuid;

import com.sun.istack.internal.NotNull;

import java.util.Collection;

public interface Node extends HasUUID{

    /**
     * @param classType must to extends HasUUID
     * @return collection of Node
     */
    Collection<Node> getCollection(@NotNull final Class<? extends HasUUID> classType);

}
