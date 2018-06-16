import java.util.UUID;

/**
 * Created by Martha on 3/1/2017.
 */
public class DataUtils {
    private static Integer counter = 0;

    public static final String VALID_USERNAME = "a@a.com";

    public static final String VALID_PASSWORD = "password";

    public static final String COMAPANY = "COMPANY";

    public static final String INVALID = "INVALID";

    static String nextKey(){
        return UUID.randomUUID().toString();
    }

    static String nextName(){
        return "Name" + (++counter);
    }

    static String nextEmail(){
        int temp = ++counter;
        return temp + "@" + temp + ".com";
    }
}
