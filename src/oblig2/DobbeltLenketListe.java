package oblig2;

////////////////// class DobbeltLenketListe //////////////////////////////

import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T>{

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

    /*------------------------------ Hjelpemetoder --------------------------------------------------------*/
    //Hjelpemetode hentet fra kompendie

    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }
    /*------------------------------ Hjelpemetoder --------------------------------------------------------*/

    /*---------------------------------    Oppgave 1    ----------------------------------------------------------*/
    public DobbeltLenketListe(T[] a) {
        //Kaster et NullPointerException hvis tabellen er tom
        //Eller hvos tabellen bare inneholder "null-verdier".
        Objects.requireNonNull(a, "Tabellen a er Null!");
        int countNullValues= 0; //Antall verdier som er null


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
        antall = 0;
        Node<T> current = hode;

        while (current != null){
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
        // Enkleste måten, likevell effektiv og det IntelliJ ønsket
            return (antall == 0);

        // Ternary-if for å sjekke om en liste er tom. ///////////
        //  return (antall == 0) ? true : false;

        //Vanlig if-else for å sjekke om en liste er tom. /////////
            /*if (antall == 0){
                return true;
            }
            else return false;*/
    }

    /*---------------------------------  Slutt på Oppgave 1    ----------------------------------------------------------*/


    /*---------------------------------    Oppgave 2    ----------------------------------------------------------*/
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
    public boolean leggInn(T verdi) {
        //bruker requireNonNull for å kaste avvik.
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt!");

        //Definerer en ny node
        Node <T> node = new Node<>(verdi);


        //Tilfelle 1: Hvis listen på forhånd er tom
        if (tom()) { //Bruker metoden tom() for å sjekke om listen er tom
            //oppdaterer verdiene
           hode = hale = node;
        }

        //Tilfelle 2: Hvis listen ikke er tom
        else {
            //oppdaterer verdiene
            node.forrige = hale;
            hale.neste = node;
            hale = node;

        }
        //øker antall og endringer
        antall++;
        endringer++;
        return true;
    }

    /*--------------------------------- Slutt på Oppgave 2    ----------------------------------------------------------*/


    /*---------------------------------  Oppgave 3    ----------------------------------------------------------*/

    /**
     * Sjekker om indeksen er mindre enn antall/2. Hvis den gjør det startes letingen fra hode
     * og går mot høyre ved hjelp av neste-pekere.
     * Hvis ikke skal letingen starte fra halen og gå mot venstre ved hjelp av forrige-pekere.
     * @param indeks til node
     * @return noden med den gitte indeksen/posisjonen
     */

    private Node<T> finnNode(int indeks) {

        indeksKontroll(indeks, false);
        //Definerer noden
        Node<T> node;
        int count = 0;

        //Hvis indeksen er mindre eller lik midten av listen, så er node  = hode (starten av listen)
        if(indeks < antall/2) {
            node = hode;
            //Looper gjennom listen og gir noden nye verdier for å finne verdi fra gitt indeks
            for (int i = 0; i < indeks; i++) {
                node = node.neste;
            }

        }
        //Hvis indeksen er større enn halvparten av listen, blir noden definert som halen (slutten av listen)
        else {
            node = hale;
            //looper gjennom listen fra antalll-1 (bakerst i listen) og gir i-- (slik at den leter bakover i listen og ikke fremover)
            //Så gir man noden den nye ferdien frem til den finner verdien på den gitte indeksen
            for (int j = antall-1; j > indeks; j--) {
                node = node.forrige;
            }
        }

        //Returnerer noden på gitt indeks
        return node;
    }


    /**
     * Henter nodens indeks og verdien dens
     * Sjekker indeks.
     * @param indeks til node
     * @return ny verdi
     */
    @Override
    public T hent(int indeks) {
     indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    /**
     * Passer på at man ikke kan legge inn null-verdier.
     * Metoden skal erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før.
     * Sjekker indeks og variabelen endringer økes.
     * @param indeks til node
     * @param nyverdi til node
     * @return ny verdi
     */
    @Override
    public T oppdater(int indeks, T nyverdi) {
        //Sjekker om indeksen er en lovlig verdi
        indeksKontroll(indeks, false);
        //Sjekker om nyverrdi er en lovlig verdi og kaster en feilmelding hvis ikke
        Objects.requireNonNull(nyverdi, "Feilmelding");

        //Definerer noden ved hjelp av finnNode metoden
        Node<T> node = finnNode(indeks);
        //Definerer den nåverende verdien (gammel verdi)
        T verdi = node.verdi;
        //Endringer økes
        endringer++;

        //Oppdaterer verdien
        node.verdi = nyverdi;


        //Returnerer gammel verdi
        return verdi;
    }



    public Liste<T> subliste(int fra, int til){
        //Sjekker om indeksene fra og til er lovlige
     fratilKontroll(antall, fra, til);

    //oppretter listen
     Liste<T> liste = new DobbeltLenketListe<>();

     //Definerer lengden
     int lengde = til-fra;

     //Hvis lengden er mindre enn 1, skal listen returneres direkte
     if (lengde < 1){
         return liste;
     }
     //Oppretter en ny node som gir den verdier finnNode(fra) (finner noden med indeksen fra)
        Node <T> node = finnNode(fra);

     //Mens lengden er større enn 0 legges verdier fra tabellen inn i listen.
     while (lengde > 0){
         liste.leggInn(node.verdi);
         node = node.neste;
         //Lengden til tabellen blir mindre
         lengde--;
     }

     //Returnerer den nye listen med verider fra til.
     return liste;
    }

    /*---------------------------------  Slutt på Oppgave 3    ----------------------------------------------------------*/



    /*--------------------------------- Oppgave 4    ----------------------------------------------------------*/
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


    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }


    /*---------------------------------  Slutt på Oppgave 4    ----------------------------------------------------------*/

    /*--------------------------------- Oppgave 5 FERDIG   ----------------------------------------------------------*/

    /**
     * Skal legge verdi inn i listen, og den får indeks/posisjon "indeks"
     * Når verdi legges inn riktig i forhold til neste/forrige-pekere, skal endringer og antall oppdateres
     * @param indeks til node
     * @param verdi til node
     */
    @Override
    public void leggInn(int indeks, T verdi) {
        //bruker requireNonNull for å kaste avvik.
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt!");


        //Sjekker om indexen er mindre enn 0 eller større enn antallet verdier i listen.
        if(indeks < 0 || indeks > antall){
            throw new IndexOutOfBoundsException("indexen er utenfor rekkevidde");
        }

        //Definerer en ny node for verdi
        Node <T> node = new Node<>(verdi);

        //Tilfelle 1: Hvis listen på forhånd er tom
        if (tom()) { //Bruker metoden tom() for å sjekke om listen er tom
            //oppdaterer verdiene og gjør slik at hode og hale = den nye veriden (bare en verdi i listen)
           hode = hale = new Node<>(verdi, null, null);
        }
        //Tilfelle 2: Hvis listen ikke er tom og indeks = 0
        //Dersom indeks = 0, skal verdien legges inn først i listen.
        else if (indeks == 0){
            //Oppdaterer verdiene
            node.neste = hode; //setter verdien bak den første verdien
            hode.forrige = node;
            hode = node; //Gir hode den nye verdien
        }
        //Tilfelle 3: Hvis listen ikke er tom og index = antall
        else if (indeks == antall){ //Dersom index = antall skal verdien bli lagt til som hale
            //Oppdaterer verdiene
            node.forrige = hale;
            hale.neste = node;
            hale = node;
        }

        //Tilfelle 4: Hvis listen ikke er tom og 0 < index < antall
        else {
            //Oppretter en hjelpeNode
            Node<T> newNode = hode;
            for (int i = 1; i < indeks; i++){ //Starter på 1 fordi indeks 0 allerede er opptatt
                //Noden looper videre for å finne gitt indeks
                newNode = newNode.neste;

            }
            //Oppdaterer verdiene
            node.neste = newNode.neste;
            newNode.neste = node;
            node.forrige = newNode;
            node.neste.forrige = node.forrige.neste = node;

        }
        //Oppdaterer antall og antall endringer
        antall++;
        endringer++;

        }


    /*---------------------------------  Slutt på Oppgave 5    ----------------------------------------------------------*/



    /*---------------------------------  Oppgave 6    ----------------------------------------------------------*/


    //Skal fjerne verdi fra listen, og returnere true
    //Hvis det er flere av samme verdi skal den første gitte verdien (fra hode) fjernes
    //Hvis verdien ikke er i listen, returner false.
    //Skal ikke kastes unntak hvis verdi er mull, men returnere false.

    /**
     * Skal fjerne verdi fra listen. Hvis det er flere forekomster er det den første som skal fjernes (fra venstre), return true
     * Hvis verdi ikke finnes, skal det returneres false.
     * @param verdi til node
     * @return true/false ettersom verdi blir fjernet eller ikke
     */
    @Override
    public boolean fjern(T verdi) {
        //Returnerer false hvis verdi er null
        if (verdi == null) {
            return false;
        }
        Node<T> node = hode;
        while (node != null) {
            if (node.verdi.equals(verdi)) { //Hvis verdien er lik noden sin verdi, hopp ut
                break;
            }
            node = node.neste; //Går videre til neste node
        }

        //Når noden går ut av listen, returnerer metoden false. (node = null --> ingen flere verdier i lsiten)
        if (node == null){ return false; }

        //Hvis det bare eksisterer én node i listen
        else if (antall == 1){
            hode = hale = null;
        }

        //Hviv verdien = hode (starten av listen)
        else if (node == hode) {
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
        } else{
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        node.verdi = null;
        node.forrige = node.neste = null;

        antall--;
        endringer++;
        return true;
    }



    //Skal fjerne og returnere verdien på gitt ideks

    /**
     * Denne skal fjerne og returnere verdien på posisjonen "indeks".
     * Her må vi også sjekke indeks først.
     * @param indeks til node
     * @return verdien på posisjonen "indeks"
     */
    @Override
    public T fjern(int indeks) {

    /* indeksKontroll(indeks, false);     //Sjekker om index er gyldig
     Node<T> newNode = finnNode(indeks);       //Finner verdien på plass index
     T value =  newNode.verdi;                 //Henter verdien på plass index
     fjern(value);                             //Bruker fjern-metoden over til å fjerne verdien på plass index
     return value;                             //Returnerer fjernet verdi*/

        //Hard-coded versjon av det som skjer over.
        Node<T> node = hode;

        //Hvis listen bare inneholder 1 verdi
        if (antall == 1){
            //Gi verdien på indeksen verdi null.
            hode = hale = null;
        }

        //Tilfelle 1: Den første fjernes
        else if (indeks == 0) { //Hvis indeksen er 0 (første tallet i listen, dvs hode)
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
            node.forrige.neste = node.neste; //oppdaterer verdien til noden
            node.neste.forrige= node.forrige;
        }


        T nodeVverdi = node.verdi; //Verdien som skal returneres
        node.verdi = null; //Gir verdien til indeksen = null
        node.forrige = node.neste = null;


        antall--;   //Antall skal reduseres --
        endringer++;  //endringer skal økes ++
        return nodeVverdi; //Returnerer verdien til fjernet node
    }



    /*---------------------------------  Slutt på Oppgave 6    ----------------------------------------------------------*/



    /*---------------------------------   Oppgave 7 ferdig   ----------------------------------------------------------*/


    /**
     * Her skal vi tømme listen og nulle alt slik at det som ikke blir brukt kan fjernes
     */
    @Override
    public void nullstill() {

        //metode 1 tar 7ms for å teste.
        //Starter i hode og går mot hale ved hjelpepekeren neste
        Node <T> nodeHode = hode;

        //Så lenge nodeHode != null, slett alle nodene
        while ( nodeHode != null){
            nodeHode.neste = null;
            nodeHode.forrige = null;
            nodeHode = null;
        }

      /* while (nodeHode!= null){
            fjern(0);
        }*/

        //til slutt settes både hode og hale til null, antall til 0 og endringer økes (fra oppgtekst)
        hode = hale = null; //ingen verdier igjen i listen
        antall = 0;
        endringer++; //Det er gjort endringer

        //Metode 2 gir feil i test. Velger derfor metode 1.


    }


    /*---------------------------------  Slutt på Oppgave 7    ----------------------------------------------------------*/

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }


    /*---------------------------------  Oppgave 8    ----------------------------------------------------------*/

    /**
     *
     * @return en instans av iteratorklassen
     */
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    /**
     * Sjekke at indeks er lovlig ved å bruke indeksKontroll(), deretter bruke konstruktøren
     * i oppg c) til å returnere en instans av iteratorklassen
     * @param indeks til node
     * @return h
     */
    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        //Er ferdigkodet og skal IKKE endres
        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        //Denne skal kodes. 1)Sette pekeren "denne" til noden som hører til den oppgitte indeksen. Resten skal være som i den
        //Ferdigkodete konstruktøren over.
        private DobbeltLenketListeIterator(int indeks) {
            Node<T> newNode = finnNode(indeks);
            if (indeks > antall || indeks < 0) {
                throw new IndexOutOfBoundsException("Index utenfor rekkevidde!");
            }
            denne = newNode;
            fjernOK = false;//Samme som over
            iteratorendringer = endringer;
        }


        //Er ferdigkodet og skal IKKE endres
        @Override
        public boolean hasNext(){
            return denne != null;
        }


        /**
         * Først sjekke om iteratorendringer er lik endringer, hvis ikke kastet ConcurrentModificationException,
         * hvis det ikke er flere igjen i listen kastet NoSuchElementException
         * Sett fjernOk til true, verdien til denne returneres og denne flyttes til neste node
         * @return verdien til denne
         */
        @Override
        public T next(){
            if(iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            }
            T denneVerdi;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                fjernOK = true;
                denneVerdi = denne.verdi;   //lagrer verdien i denne
                denne = denne.neste;        //flytter denne til den neste noden
                return denneVerdi;          //og returnerer verdien
            }

        }


        /*---------------------------------  Slutt på Oppgave 8    ----------------------------------------------------------*/


        /*---------------------------------  Oppgave 9    ----------------------------------------------------------*/
        //

        /**
         * Hvis det ikke er tillatt å kalle denne metoden, kastes en IllegalStateException,
         * Hvis endringer og iteratorendringer er forskjellige, kastes en ConcurrentModificationException,
         * Hvis disse hindrene passeres, settes fjernOk() til false.
         * Noden rett til venstre for p fjernes
         * Antall reduseres, men endringer og iteratorendringer økes.
         */
        @Override
        public void remove() {

            if (!fjernOK) {
                throw new IllegalStateException("Ulovlig tilstand");
            }

            if(endringer != iteratorendringer) {
                throw new ConcurrentModificationException();
            }

                fjernOK = false; //da kan ikke remove() kalles på nytt


            //Sjekker om listen er tom
            if(hode == null) {
                return;
            }
            //Hvis det bare er en node
            if(antall == 1) {
                hode = hale = null; //Setter hode og hale = null
            }
            else {
                Node<T> neste = denne;
                //Hvis den siste noden skal fjernes
                if(denne == null) {
                    Node<T> forrige = hale.forrige;     //Hjelpeverdi
                    hale = forrige;                     //Setter hale til hale.forrige
                    forrige.neste = null;               //Oppdaterer den gamle halen til null
                }
                //Hvis den første noden skal fjernes
                else if (denne.forrige == hode) {
                    hode = neste;               //Oppdaterer hode til neste verdi i listen
                    neste.forrige = null;       //Setter gamle hode til null
                }
                //Hvis en node i midten skal fjernes
                else {
                    Node<T> forrige = denne.forrige.forrige;        //Hjelpeverdi
                    forrige.neste = neste;                          //Oppdaterer verdien før
                    neste.forrige = forrige;                        //Oppdaterer verdien etter
                }

            }
                antall--; // oppdaterer antallet noder
                endringer++; // oppdaterer antall endringer
                iteratorendringer++; //og iteratorendringer
            }

        }
    // class DobbeltLenketListeIterator

        /*---------------------------------  Slutt på Oppgave 9    ----------------------------------------------------------*/




    /*---------------------------------   Oppgave 10    ----------------------------------------------------------*/

    public static  <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        // Sjekker om listen er tom
        if (liste.tom()){
            return; //Dersom listen er tom er det ikke noe å sortere og kan derfor returnere.
        }

        int curr_index; // Hjelpeverdi for i
        int next_index; // Hjelpeverdi for j
        T curr_value;   //
        T tmp;
        for (int i = 0; i < liste.antall() - 1; ++i){ //Looper fra 0 til antall - 1.
            for (int j = i + 1; j < liste.antall();  ++j){ // Looper fra 1 til antall slik at j alltid er 1 større enn i
                curr_index = i;
                next_index = j;
                if (liste.hent(i) != null && liste.hent(j) != null) { //Sjekker om noen av verdiene er null
                    curr_value = liste.hent(curr_index);    //Henter verdi på index i
                    tmp = liste.hent(next_index);           //Henter verdi på index j

                    //Tilfelle 1: hvis a == b --> ingenting trenger å skje

                    //Tilfelle 2: Hvis a er større enn b --> Da bytter vi om på verdiene.
                    if (c.compare(curr_value, tmp) > 0) {
                        liste.oppdater(i, tmp);
                        liste.oppdater(j, curr_value);
                    }
                    //Tilfelle 3: hvis a < b --> De er allerede i riktig rekkefølge, ingenting trenger å skje.
                }
            }
        }
    }
    /*---------------------------------  Slutt på Oppgave 10    ----------------------------------------------------------*/


} // class DobbeltLenketListe
