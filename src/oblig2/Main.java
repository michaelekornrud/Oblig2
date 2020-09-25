package oblig2;

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
    }
}
