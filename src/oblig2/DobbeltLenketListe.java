package oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////

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
        //bruker requireNonNull for å kaste avvik.
         Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt!");

          //Definerer en ny node
          Node <T> node = new Node<>(verdi);


           //Tilfelle 1: Hvis listen på forhånd er tom
        if (tom()) { //Bruker metoden tom() for å sjekke om listen er tom
            //oppdaterer verdiene
            hode = node;
            hale = hode;
        }

            //Tilfelle 2: Hvis listen ikke er tom
            else {
            //oppdaterer verdiene
            node.forrige = hale;
            hale.neste = node;
            hale = node;
        }
            antall++;
            endringer++;
            return true;
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

       // Node <T> node = hode; //definerer noden som hode (starten) av listen
        int index = 0; //definerer en index. (hjelpevariabel) som skal returneres

        //oppretter ny node som med startverdi hode.
        //Mens node != null og hvis noden sin nåverende verdi = inputverdi, returnerer metoden indeksen
        for (Node<T> node = hode; node != null;){
            if (node.verdi.equals(value)){
                return index;
            }
            //Hvis ikke noden sin nåverende verdi = input verdi, går noden videre til neste verdi og indeksen oppdateres
            else {
                node = node.neste;
                index++;
            }
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

            throw new UnsupportedOperationException();
    }


    //oppg 6
    //Skal fjerne verdi fra listen, og returnere true
    //Hvis det er flere av samme verdi skal den første gitte verdien (fra hode) fjernes
    //Hvis verdien ikke er i listen, returner false.
    //Skal ikke kastes unntak hvis verdi er mull, men returnere false.
    @Override
    public boolean fjern(T verdi) {


        //Returnerer false hvis verdi er null
        if (verdi == null){
            return false;
        }

        Node <T> node = hode;

        while (node!= null){
           if (node.verdi == verdi){ //Hvis verdien er lik noden sin verdi, hopp ut
               break;
           }
           node = node.neste; //Går videre til neste node
        }

        //Når noden går ut av listen, returnerer metoden false. (node = null --> ingen flere verdier i lsiten)
        if (node == null){ return false; }


        //Hviv verdien = hode (starten av listen)
        if (node == hode) {
            hode = hode.neste; //Går videre fra starten av tabellen

            //Sjekker om det bare finnes en verdi i tabellen
            if (hode != null) {
                hode.forrige = null;
            } else {
                hale = null;
            }
        }

        //Hvis verdien er på slutten av tabellen
        else if (node == hale){
            hale = hale.forrige;
            hale.neste = null;
        } else{ node.forrige.neste = node.neste; node.neste.forrige = node.forrige;}

        antall--;
        endringer++;
        return true;
    }


    //oppg 6
    //Skal fjerne og returnere verdien på gitt ideks
    @Override
    public T fjern(int indeks) {
    indeksKontroll(indeks, false);
    Node<T> node;

        //Tilfelle 1: Den første fjernes
        if (indeks == 0) { //Hvis indeksen er 0 (første tallet i listen, dvs hode)
            node = hode; //gir noden verdien til hode (indeks 0)
            hode = hode.neste; //oppdaterer hode til at den får verdien til den neste verdien i listen
            hode.forrige = null; //Fjerner verdien til indeks 0 (gir den verdien null)
        }
        //Tilfelle 2: Den siste fjernes
        else if (indeks == antall - 1){ //Hvis indeksen er det siste tallet i listen
            node = hale; //Da er noden = halen (siste verdien i listen)
            hale = hale.forrige; //Oppdaterer verdien til halen -> verdien til antall-2
            hale.neste = null; //fjerner den gamle ferdien til indeksen (gir den verdien null)
        }
        //Tilfelle 3: En verdi mellom to andre fjernes
        else
        {
            node = finnNode(indeks); //Finner noden ved hjelp av finnNode metoden som er lagd tidligere
            node = node.neste; //oppdaterer verdien til noden
            node = node.forrige;
        }
        //Antall skal reduseres --
        //endringer skal økes ++
        antall--;
        endringer++;
        return node.verdi; //Returnerer verdien til noden på gitt indeks
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

        //Hvis listen er tom, returner []
        if (tom()) return "[]";

        //Oppretter en stringjoiner der verdiene skal legges inn i [] med "," og mellomrom
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");

        //Oppretter en ny node og setter verdien til hode (starten av listen), loopen fortsetter til noden sin verdi er null
        //Hvis noden ikke er null, legges node.verdi inn i stringjoiner og noden går videre (node = node.neste)
        for (Node<T> node = hode; node != null; node = node.neste) {
            stringJoiner.add(node.verdi.toString());
        }


        //Returnerer stringJoiner/listen med oppdaterte verdier.
        return stringJoiner.toString();
    }

    /**
     * Sjekker først om listen er tom, og retunerer [] hvis den er det.
     * Lager strengen med Stringbuilder. Denne gangen starter vi på hale, og legger forrige node
     * sin verdi inn i strengen, da får vi den omvendte rekkefølgen.
     * @return samme tegnstreng som i toString, men i omvendt rekkefølge
     */
    public String omvendtString() {
        /*if (tom()) { return "[]";} //Skal returnere [] hvis listen ikke inneholder noen verdier

        // Setter opp en kolonne der verdiene senere skal settes inn.
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");


        Node <T> node = hale; //Definerer noden til hale (slutten av listen)
        stringBuilder.append(node.verdi);
        node = node.forrige;

        while (node != null){ //Hvis verdien (noden) ikke er null, så...
            stringBuilder.append(", ").append(node); //leggert til verdien i tegnestrengen med "," og mellomrom
            node = node.forrige; //Hopper videre til neste verdi
        }

        stringBuilder.append("]");
        return stringBuilder.toString(); //Returnerer tegnestringen med innhold.*/

        //Hvis listen er tom, returner []
        if (tom()) return "[]";


        //Oppretter en stringjoiner der verdiene skal legges inn i [] med "," og mellomrom
       StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");


        //Oppretter en ny node og setter verdien til hode (starten av listen), loopen fortsetter til noden sin verdi er null
        //Hvis noden ikke er null, legges node.verdi inn i stringjoiner og noden går videre (node = node.neste)
        for (Node<T> node = hale; node != null; node = node.forrige) {
            stringJoiner.add(node.verdi.toString());
        }

        //returnerer stringjoiner/listen med oppdaterte verdier
    return stringJoiner.toString();
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

