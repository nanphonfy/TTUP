package typeinfo;
//: typeinfo/Shapes.java

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    /**
     * draw在所有派生类都会被覆盖，并且是动态绑定的
     */
    void draw() {
        System.out.println(this + ".draw()");
    }

    /**
     * 强制继承者覆盖该方法
     * @return
     */
    @Override abstract public String toString();
}

class Circle extends Shape {
    @Override public String toString() {
        return "Circle";
    }
}

class Square extends Shape {
    @Override public String toString() {
        return "Square";
    }
}

class Triangle extends Shape {
    @Override public String toString() {
        return "Triangle";
    }
}

public class Shapes {
    public static void main(String[] args) {
        //从数组中取出元素时，容器实际将所有事物都当做object持有（会自动将结果转型回Shape）
        List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
