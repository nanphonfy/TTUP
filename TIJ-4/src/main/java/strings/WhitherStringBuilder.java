package strings;//: strings/WhitherStringBuilder.java

public class WhitherStringBuilder {
    public static String implicit(String[] fields) {
        String result = "";
        //注意：StringBuilder在循环内构造，每循环一次，就会创建一个新的StringBuilder对象。
        for (int i = 0; i < fields.length; i++) {
            result += fields[i];
        }
        return result;
    }

    /**
     * 循环部分代码更简短、更简单，且只生成一个StringBuilder对象。
     *
     * @param fields
     * @return
     */
    public static String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            result.append(fields[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] fields = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t".split(",");

        /**
         *  如果字符串操作较简单，可信赖编译器；如果要在toString方法中使用循环，最好自己创建一个StringBuilder对象，用来构造最终结果。
         */
        long start = System.nanoTime();
        explicit(fields);
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        implicit(fields);
        System.out.println(System.nanoTime() - start);
    }
} ///:~
