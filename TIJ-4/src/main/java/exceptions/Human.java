package exceptions;
//: exceptions/Human.java
// Catching exception hierarchies.

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

/**
 * 抛出异常，会按照代码顺序找出最近的处理程序，匹配后认为异常得到处理
 */
public class Human {
    public static void main(String[] args) {
        // Catch the exact type:
        try {
            throw new Sneeze();
        } catch (Sneeze s) {
            //Caught Sneeze
            //Sneeze被第一个匹配的catch子句捕获
            System.out.println("Caught Sneeze");
        } catch (Annoyance a) {
            //Caught Annoyance
            System.out.println("Caught Annoyance");
        }
        // Catch the base type:
        try {
            throw new Sneeze();
        } catch (Annoyance a) {
            //只留下Annoyance，该程序仍然能运行，因为捕获的是Sneeze的基类
            //可以捕获Annoyance及它派生的基类(只要捕获的是异常的基类，就不用频繁更改代码)
            System.out.println("Caught Annoyance");
        }
    }
}
/* Output:
Caught Sneeze
Caught Annoyance
*///:~
