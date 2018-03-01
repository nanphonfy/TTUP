package strings;//: strings/Replacing.java

import static net.mindview.util.Print.print;

public class Replacing {
    static String s = Splitting.knights;

    public static void main(String[] args) {
        //字母f开头，后面跟一个或多个字母（w是小写）
        print(s.replaceFirst("f\\w+", "located"));
        print(s.replaceAll("shrubbery|tree|herring", "banana"));
    }
}
/* Output:
Then, when you have located the shrubbery, you must cut down the mightiest tree in the forest... with... a herring!
Then, when you have found the banana, you must cut down the mightiest banana in the forest... with... a banana!
*///:~
