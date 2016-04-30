package ltssh.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {

        Field f1 = new Field("tony", 11);
        Field f2 = new Field("jack", 11);
        Field f3 = new Field("tom", 11);
        Field f4 = new Field("jason", 44);

        List<Field> list = new ArrayList<Field>();
        list.add(f1);
        list.add(f3);
        list.add(f4);
        list.add(f2);
        Collections.sort(list);

        for (Field o : list) {
            System.out.println(o.getAge() + "-->" + o.getName());
        }
    }
}