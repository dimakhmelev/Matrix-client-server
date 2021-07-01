package uuid;

import com.sun.istack.internal.NotNull;
import java.util.UUID;


/**
 *
 * This class represent a key and its UUID.
 */
  public final class MyUUID implements Comparable<MyUUID> {

    final private String key;
    final private UUID uuid;


    public  String getStringUUID() {
        return this.key;
    }
    public  UUID getUuid() {
        return uuid;
    }


    /**
     * the UUID must based on a provided key
     * @param key key is String type
     */
    public MyUUID(@NotNull String key) {
        this.key = key;
        this.uuid = encoder(key);
    }

    /**
     * given a key, encode to UUID
     * @param key string key
     * @return UUID based on the key
     */
    public static UUID encoder(@NotNull final String key) {

        byte[] byteKey = key.getBytes();
        return UUID.nameUUIDFromBytes(byteKey);
    }

    /**
     *
     * @param myUUID UUID to compute its string
     * @return string based on the UUID
     */
    public static String decoder(@NotNull final MyUUID myUUID) {
        return myUUID.getStringUUID();
    }


    /**
     *
     * @param o other MyUUID to compare
     * @return  The method returns -1, 0 or 1 depending on whether the uuid is less than, equal to or greater than the o UUID.
     */
    @Override
    public int compareTo(MyUUID o) {
        return this.uuid.compareTo(o.getUuid());
    }


    /**
     *
     * This method returns a string representation of the MyUUID. (key)
     * @return representation of the MyUUID
     */
    @Override
    public String toString() {
        return key;
    }


    public static void main(String[] args) {
        MyUUID one = new MyUUID("or");
        MyUUID two = new MyUUID("or");

        System.out.println(two);



    }



}

