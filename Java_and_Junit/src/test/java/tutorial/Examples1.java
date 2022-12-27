package tutorial;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Examples1 {

    @Test
    public void conditionalStatement() {
        // if ve switch
        // if - else => if - else if - else

        int x = 0;
        int y = 1;
        String a = "A";
        String b = "a";

        if (x == y) {
            System.out.println("x == y");
        } else {
            System.out.println("x != y");
        }

        if (x == y) {
            System.out.println("x == y");
        } else if (a.equalsIgnoreCase(b)) {
            System.out.println("a.equals(b)");
        } else if (!a.equals(b)) {
            System.out.println("!a.equals(b)");
        } else {
            System.out.println("x != y");
        }

        int text = 0;
        switch (text) {
            case 1:
                System.out.println("Lenova");
                break;
            case 0:
                System.out.println("Monster");
                break;
            case 2:
                System.out.println("Samsung");
                break;
            default:
                System.out.println("eslesmedi");
        }
    }

    @Test
    public void loop() {

        List<String> list = new ArrayList<>();
        list.add("Samsung tv");
        list.add("Samsung tablet");
        list.add("Samsung cm");
        list.add("Lenova pc");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            System.out.println(list.get(i).contains("Samsung"));
        }

        //IndexOutOfBoundsException

        for (String urun : list) {
            System.out.println(urun);
            System.out.println(urun.contains("Samsung"));
        }


        int x = 0;
        int y = 1;
        while (x + y <= 10) {
            System.out.println(x);
            x++;
        }
    }

}
