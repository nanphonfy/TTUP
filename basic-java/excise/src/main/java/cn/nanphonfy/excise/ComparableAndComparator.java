package cn.nanphonfy.excise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

/**
 * http://www.cnblogs.com/gnuhpc/archive/2012/12/17/2822251.html
 * Comparable接口：java.lang包，强行对实现它的每个类的对象进行整体排序，compareTo方法被称为自然比较（从小到大）方法，该对象和指定对象关系：`>`(负数)、`<`（正数）、`=`（零）
 * Comparator接口：java.util包，若类没考虑到比较问题而没实现Comparable接口，可通过Comparator来实现比较算法，并为不同排序做准备
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/4
 */
public class ComparableAndComparator {
    public static void main(String[] args) {
        comparableTest();

        System.out.println("/////////////////////////////////////");

        comparatorTest();
    }

    private static void comparableTest() {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry", 30000);
        staff[1] = new Employee("Cracke", 7500);
        staff[2] = new Employee("Tony", 8000);
        //sort方法可以实现对象数组排序，但必须实现Comparable接口
        Arrays.sort(staff);
        for (Employee e : staff) {
            System.out.println(e);
        }
    }

    private static void comparatorTest() {
        TreeSet<Student> set = new TreeSet<Student>(new Comparator<Student>() {
            @Override public int compare(Student o1, Student o2) {
                return (int) (o1.getScore() - o2.getScore());
            }
        });
        set.add(new Student("Agamemnon", 300));
        set.add(new Student("Cato", 400));
        set.add(new Student("Plato", 100));
        set.add(new Student("Zeno", 200));
        set.add(new Student("Archimedes", 500));
        for (Student student : set) {
            System.out.println(student);
        }
    }
}

class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        Random ID = new Random();
        id = ID.nextInt(10000000);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override public int compareTo(Employee other) {
        if (id < other.id) {
            return -1;
        }
        if (id > other.id) {
            return 1;
        }
        return 0;
    }

    @Override public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class Student {
    private int id;
    private String name;
    private double score;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
        Random ID = new Random();
        id = ID.nextInt(10000000);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
