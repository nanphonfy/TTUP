package strings;//: strings/IntegerMatch.java

public class IntegerMatch {
    /**
     * 【正则表达式】
     * Unix工具集：sed和awk
     * 正则表达式是一种强大而灵活的文本处理工具。
     * （基础）
     * 一个负号可能在最前面：-?
     * 一或多位数字：\d
     * java对反斜杠\有不同的处理
     * 可能一负号&一位或多位数字：-?\\d+
     * 检查String是否匹配描述正则表达式
     */
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        //可能以一个"-"号开头，也可能没有
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        //可能一一个加号或减号开头
        System.out.println("+911".matches("(-|\\+)?\\d+"));
    }
} /* Output:
true
true
false
true
*///:~
