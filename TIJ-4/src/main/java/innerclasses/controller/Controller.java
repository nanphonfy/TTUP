//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
package innerclasses.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含了一个用来管理并触发事件的实际控制框架
 */
public class Controller {
    // A class from java.util to hold Event objects:
    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event c) {
        eventList.add(c);
    }

    public void run() {
        while (eventList.size() > 0)
        // Make a copy so you're not modifying the list
        // while you're selecting the elements in it:
        {
            for (Event e : new ArrayList<>(eventList)) {
                if (e.ready()) {
                    //innerclasses.T10$1@6aa8ceb6
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
} ///:~
