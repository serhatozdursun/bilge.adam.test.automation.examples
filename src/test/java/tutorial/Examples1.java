package tutorial;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Examples1 {

    @Test
    public void dataTypes() {
        // sadece ture/false degerlenrini alir null deger icermez.
        boolean trueFalse = false;
        System.out.println(trueFalse);
        // tek bir karakteri temsileder
        char c = 'A';
        System.out.println(c);
        //kusratsiz temsileder
        int integer = 0;
        System.out.println(integer);
        //metinlerle ilgili islem yapabilmek icin olusturulmus bir java klasidir char dizi olarak kullanilir
        String text = "Serhat";
        System.out.println(text);
        //parasal islemlerde float veya double kullanilir
        float f = 0.0f;
        double d = 0.0d;
        System.out.println(f);
        System.out.println(d);
    }

    @Test
    public void operators() {
       /*
       && (ve) || (veya)
       ==, != <, >, <=, >=
       */

        //boolean sadece false ve true degeri alabilir (Dogru/Yanlis)
        boolean bool1 = false;
        boolean bool2 = true;
        boolean bResult = bool1 && bool2;
        System.out.println(bResult);
        boolean bResult2 = bool1 || bool2;
        System.out.println(bResult2);

        // int tam sayi tipi
        int x = 6;
        int y = 2;

        //  =, +, -, *,/ dort islem
        int t = x + y;
        System.out.println(t);
        int c = x - y;
        System.out.println(c);
        int b = x / y;
        System.out.println(b);
        int m = x * y;


        System.out.println(m);
        String metin1 = new String("B");
        String metin2 = "b";
        //icerik ayni da olsa asagida ki dogrulama false yanlis donecek
        // == operatoru objenin bilgisayarhafizasinda ki yeri ayni mi diye kontrol eder
        // string degerlerin icini kiyaslamak icin .equals() methodu kullanilmali.
        boolean esitMi = metin1 == metin2;
        boolean sEsitMi = metin1.equals(metin2);
        boolean sEsitMi2 = metin1.equalsIgnoreCase(metin2);
        System.out.println(esitMi);
        System.out.println(sEsitMi);
        System.out.println(sEsitMi2);
        int j = 0;
        int k = 1;
        esitMi = j == k;
        boolean esitMi2 = j != k;

        sEsitMi2 = metin1.equalsIgnoreCase(metin2);
        System.out.println(sEsitMi2);

        boolean buyuktur = 0 > 1;
        System.out.println(buyuktur);
        boolean kucuktur = 0 < 1;
        System.out.println(kucuktur);
        boolean buyukturEsit = 0 <= 0;
        System.out.println(buyukturEsit);
        boolean kucukturEsit = 0 >= 0;
        System.out.println(kucukturEsit);

    }

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

        }
    }
}
