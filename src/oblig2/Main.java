package oblig2;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        //Test av oppgave 1 a og b (antall og tom)
        //Skal returnere "0 true"
        DobbeltLenketListe<String> listeA = new DobbeltLenketListe<>();
        System.out.println(listeA.antall()+" "+listeA.tom());


        //Sjekker konstruktøren DobbeltLinkedeLister(T[]a) og hele oppgave 1.
        //Skel returnere 3 false
        String [] s = {"Ole", null, "Per", "Kari",null};
        Liste<String> listeB = new DobbeltLenketListe<>(s);
        System.out.println(listeB.antall()+ " "+listeB.tom());


        //Oppgave 2 a

        String [] s1 = {}, s2 = {"A"}, s3 = {null, "A", null, "B", null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);




       System.out.println(l1.toString()+" "+l2.toString()+" "+l3.toString()+" "
               +l1.omvendtString()+" "+l2.omvendtString()+" "+l3.omvendtString());

        //// Oppgave 2 b //////////////////
        /*DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        System.out.println(liste.toString() + " " + liste.omvendtString());
        for (int i = 1; i <= 3; i++)
        {
            liste.leggInn(i);
            System.out.println(liste.toString() + " " + liste.omvendtString());
        }
        // Utskrift:
        // [] []
        // [1] [1]
        // [1, 2] [2, 1]
        // [1, 2, 3] [3, 2, 1]*/



        ////Ooppgave 4 ////
        DobbeltLenketListe<String> sliste = new DobbeltLenketListe<>();
        sliste.leggInn("A"); //indeks 0
        sliste.leggInn("B"); //indeks 1
        sliste.leggInn("C"); //indeks 2
        sliste.leggInn(String.valueOf(1)); //indeks 3
        System.out.println(sliste.indeksTil(String.valueOf(1)));


        //// Oppgave 10 ///////////////
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};

        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
        Liste<String> liste2 = new TabellListe<>(navn);
        Liste<String> liste3 = new EnkeltLenketListe<>(navn);

        DobbeltLenketListe.sorter(liste1, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste2, Comparator.naturalOrder());
        DobbeltLenketListe.sorter(liste3, Comparator.naturalOrder());
        System.out.println(liste1); // [Anders, Berit, Bodil, Kari, Lars, Per]
        System.out.println(liste2); // [Anders, Berit, Bodil, Kari, Lars, Per]
        System.out.println(liste3); // [Anders, Berit, Bodil, Kari, Lars, Per]

        // Tabellen navn er upåvirket:
        System.out.println(Arrays.toString(navn));
        // [Lars, Anders, Bodil, Kari, Per, Berit]
    }
}
