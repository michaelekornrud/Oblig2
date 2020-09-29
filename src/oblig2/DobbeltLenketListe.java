package oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;

import java.util.Iterator;
import java.util.Objects;


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


    //Konstrukttør som skal lage en dobbeltlenket liste med verdiene fra tabellen a. Verdiene skal ligge i samme
    //rekkefølge i listen som i tabellen
    //Hvis a er null, kastes det et avvik.
    //Hvis a inneholder en eller flere null-verdier, skal de ikke tas med. DVS at listen skal inneholde de verdiene fra a som ikke er null
    //Hvis alle verdiene i a er null, får vi en tom liste.
    //hode må peke til den første i listen, og hale må peke til den siste.
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
     *
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
     *
     * @return true/false avhengig av om listen er tom eller ikke
     */
    @Override
    public boolean tom() {
        if (antall == 0){
            return true;
        }
        else return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        //Kast avvik dersom verdi == null (tom verdi). Bruk RequireNonNull fra Objects.
        //Skal legge inn en node med oppgitt verdi bakerst i listen og returnere true
        //Skal skille mellom tomme lister og lister med innhold
        //Dersom listen er tom, skal både hode og hale peke på elementet som blir lagt til
        //Hvis listen inneholder verdier fra før skal kun hale-pekeren peke på det nye elementet.
        //Antallet skal økes etter ny innlegging.

        Liste<String> liste = new DobbeltLenketListe<>();
        Node current = hale;
        T nyVerdi = Objects.requireNonNull(verdi);

            if (verdi != null && liste.antall() != 0) {
                liste[hale] = nyVerdi;
                return true;
            } else if (liste.antall() == 0) {
                liste[current] = nyVerdi;
                return true;
            } else {
                return false;
            }

    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
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
     *
     * @return en tegnstreng med listens verdier
     */
    @Override
    public String toString() {
        if (tom()) { return "[]";} //Skal returnere [] hvis listen ikke inneholder noen verdier

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["); // Starter tegnestrengen []. Koden etter dette skrives ut i tegnestrengen

        Node <T> node = hode;

        while (node != null){
            stringBuilder.append(node.verdi);
            node = node.neste;
        }


        stringBuilder.append("]"); // Avslutter tegnestrengen []
        return stringBuilder.toString(); //Returnerer tegnestringen med innhold.
    }

    /**
     *
     * @return samme tegnstreng som i toString, men i omvendt rekkefølge
     */
    public String omvendtString() {
        if (tom()) { return "[]";} //Skal returnere [] hvis listen ikke inneholder noen verdier

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["); // Starter tegnestrengen []. Koden etter dette skrives ut i tegnestrengen

        Node <T> node = hale;

        while (node != null){
            stringBuilder.append(node.verdi);
            node = node.forrige;
        }

        stringBuilder.append("]"); // Avslutter tegnestrengen []
        return stringBuilder.toString(); //Returnerer tegnestringen med innhold.
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

