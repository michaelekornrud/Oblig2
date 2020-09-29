package oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////


import jdk.jshell.EvalException;

import java.util.Comparator;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
       hode = hale = null;
       antall = 0;
       endringer = 0;
    }



    public DobbeltLenketListe(T[] a) {
        //Kaster et NullPointerException hvis tabellen er tom
        //Eller hvos tabellen bare inneholder "null-verdier".
        Objects.requireNonNull(a, "Tabellen a er Null!");
        int countNullValues = 0; //Antall verdier som er null


        for (T value : a) { //looper gjennom tabellen med T verdien
            if (value == null) { //Sjekker om nåverende verdi er null
              countNullValues++; //Oppdaterer antall verdier som er null

            } else if (tom()){ //Hvis verdien ikke er null, oppdateres første verdi.
                Node<T>  node = new Node<>(value, hode, null);
                hode = hale = node; //oppdaterer noden
                antall++; //oppdaterer antall verdier som ikke er null
            } else { //Hvis verdien ikke er null, oppdatreres siste verdi i tabellen.
                Node <T> node = new Node<>(value, hale, null);
                hale.neste = node;
                hale = node;
            }
        }
        //System.out.println("Antall null-verdier: "+countNullValues);
        //System.out.println("Antall definerte verdier: "+antall);
    }






    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
    }

    /**
     * Sjekker om listen er tom. Hvis ikke settes første node til hode. Går gjennom listen og oppdaterer
     * antallet ettersom vi finner neste node.
     * @return antall verdier i listen
     */
    @Override
    public int antall() {
        if (tom()){
            return 0;
        }
        Node current = hode;

        while (current.neste != null){
            current = current.neste; //oppdaterer det nåverende tallet.
            antall++; //oppdaterer antall
        }
        return antall;
        }



    /**
     * Sjekker om antallet er lik 0, og returnerer deretter.
     * @return true/false avhengig av om listen er tom eller ikke
     */
    @Override
    public boolean tom() {
        if (antall == 0){
            return true;
        }
        else return false;
    }

    /**
     *
     * @param verdi
     * @return
     */
    @Override
    public boolean leggInn(T verdi) {
        //Kast avvik dersom verdi == null (tom verdi). Bruk RequireNonNull fra Objects.
        //Skal legge inn en node med oppgitt verdi bakerst i listen og returnere true
        //Skal skille mellom tomme lister og lister med innhold
        //Dersom listen er tom, skal både hode og hale peke på elementet som blir lagt til
        //Hvis listen inneholder verdier fra før skal kun hale-pekeren peke på det nye elementet.
        //Antallet skal økes etter ny innlegging.



        Liste<String> liste = new DobbeltLenketListe<>();
        Node tail = hale;
        Node head = hode;
        T nyVerdi = Objects.requireNonNull(verdi);

            if (liste.antall() != 0) {
                tail = new Node(nyVerdi);
                return true;
            }

            else if (liste.antall() == 0) {
                tail = new Node(nyVerdi);
                head = tail;
                return true;
            }

            else {
                return false;
            }

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    /**
     * Sjekker om indeksen er mindre enn antall/2. Hvis den gjør det startes letingen fra hode
     * og går mot høyre ved hjelp av neste-pekere.
     * Hvis ikke skal letingen starte fra halen og gå mot venstre ved hjelp av forrige-pekere.
     * @param indeks
     * @return noden med den gitte indeksen/posisjonen
     */

    private Node<T> finnNode(int indeks) {

        Node<T> h = hode;
        Node<T> t = hale;
        if(indeks < antall/2) {
            for (int i = 0; i < indeks; i++) h = h.neste;
            return h;
        }
        else {
            for (int i = 0; i < indeks; i++) t = t.forrige;
            return t;
        }
        // returnere noe her
    }

    /**
     * Henter nodens indeks og verdien dens
     * Sjekker indeks.
     * @param indeks
     * @return
     */
    @Override
    public T hent(int indeks) {
        if (indeks == -1) {
            indeksKontroll(indeks, false);
        }
        return finnNode(indeks).verdi;
    }


    //Oppg 4. Returnere indeksen/posisjonen til verdi hvis den finnes i listen, og returnere -1 hvis ikke.
    @Override
    public int indeksTil(T value) {

        Node <T> node = hode; //definerer noden som hode (starten) av listen
        int index = 0; //definerer en index. (hjelpevariabel) som skal returneres

        //Sjekker om verdi er null eller ikke
        while (node != null) {
           if (node.verdi.equals(value)) {  //Sjekker om nåverende node sin verdi = inputverdi
           return index; //returnerer indeksen til verdien
    }
        node = node.neste; //hopper videre til neste verdi
        index++; //indeksen går videre (+1)
}

        return -1; //returnerer -1 hvis verdien ikke er funner  if(!node.verdi.equals(value))
    }

    /**
     * Passer på at man ikke kan legge inn null-verdier.
     * Metoden skal erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før.
     * Sjekker indeks og variabelen endringer økes.
     * @param indeks
     * @param nyverdi
     * @return
     */
    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Null-verdier er ikke tillatt her");
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }


    /**
     * Sjekker først om listen er tom, og returnerer kun [] hvis den er det.
     * Deretter lages en streng, som starter på hode,og hvis node sin verdi ikke er null,
     * så legges verdien i strengen.
     * @return en tegnstreng med listens verdier
     */
    @Override
    public String toString() {
        if (tom()) { return "[]";} //Skal returnere [] hvis listen ikke inneholder noen verdier

        //Lager er klammeparentes der verdiene senere skal legges inn i med skille ", "
        StringJoiner stringJoiner = new StringJoiner(", ", "[","]");

        Node <T> node = hode; //Definerer node som hode (starten av listen)

        while (node != null){ //Hvis verdien (noden) ikke er null, så..
            stringJoiner.add((CharSequence) node.verdi); //legger til ny verdi i klammeparantesen (strinJoiner)
            node = node.neste; //Hopper videre til enste verdi
        }

        return stringJoiner.toString(); //Returnerer tegnestringen med innhold.
    }

    /**
     * Sjekker først om listen er tom, og retunerer [] hvis den er det.
     * Lager strengen med Stringbuilder. Denne gangen starter vi på hale, og legger forrige node
     * sin verdi inn i strengen, da får vi den omvendte rekkefølgen.
     * @return samme tegnstreng som i toString, men i omvendt rekkefølge
     */
    public String omvendtString() {
        if (tom()) { return "[]";} //Skal returnere [] hvis listen ikke inneholder noen verdier

        // Setter opp en kolonne der verdiene senere skal settes inn.
        StringJoiner stringJoiner = new StringJoiner(", ","[", "]" );

        Node <T> node = hale; //Definerer noden til hale (slutten av listen)

        while (node != null){ //Hvis verdien (noden) ikke er null, så...
            stringJoiner.add((CharSequence) node.verdi); //Hvis ikke verdien er null, legges den inn i kolonnen
            node = node.forrige; //Hopper videre til neste verdi
        }

        return stringJoiner.toString(); //Returnerer tegnestringen med innhold.
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe

