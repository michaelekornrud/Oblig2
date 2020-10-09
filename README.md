# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
- Johannes Eerdahl Andresen, s341876, s341876@oslomet.no
- Aina Turum Wangsmo, s341826, s341826@oslomet.no
- Ole-Michael Ekornrud, s341866, s341866@oslomet.no

Vi har brukt git til å dokumentere arbeidet vårt. Vi har 16 commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Aina har hatt hovedansvar for oppgave 9 og 8ab).
* Johannes har hatt hovedansvar for oppgave 6, 7 og 8cd).
* Michael har hatt hovedansvar for oppgave 2b, 5, og 10.  
* Vi har i fellesskap løst oppgave 1, 2, 3 og 8. 

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: antall(): løste ved å først sjekkeom listen er tom (og returnerer 0), så setter vi antall til 0 slik at verdien 
             starter på 0 hver gang metoden blir kalt på. Deretter sjekker vi om listen har en neste --> legger til 1 hvis ja. 
             tom(): vi har 3 forksjellige måter å løse denne på, men valgte å gå for den IntelliJ "likte" best. Metoden returnerer
             false dersom antall > 0 og true dersom antall = 0. 
             
* Oppgave 2: ...
             2b) Bruker requireNonNull på input-verdi --> sjekker om listen er tom --> legger inn verdien på hode og hale.
             Hvis listen ikke er tom, oppdaterer vi halen og verdien blir lagt til bakerst i listen. 
* Oppgave 3: a) Sjekker indeks ift om listen skal starte på hode eller hale. Looper gjennom listen og gir noden nye verdier og 
             finner indeks. Metode hent() som henter nodens indeks og dens verdi, samt sjekker indeksen. oppdater(), sjekker om indeks og nyverdi er lovlige verdier -> kaster evt feilmelding. Bruker finnNode-metoden til å definere node og oppdaterer verdien til noden. 
             b)Sjekker om indeksene fra og til er lovlige, og oppretter deretter listen. Returnerer direkte om lengden er mindre enn 1. Bruker finnNode og finner noden med indeks fra. Mens lengden er større enn 0, legges verdier fra tabellen inn i listen og minker lengden. Returnerer den nye listen med verdier fra til. 
* Oppgave 4: ...

* Oppgave 5: Løste ved å først sjekke om listen er tom --> sette hode og hale til verdien
             Så sjekket jeg om index = 0 --> legge verdien inn til hode (index 0). 
             Deretter sjekket jeg kom index = antall --> verdien blir lagt til bakerst i listen
             Til slutt, hvis verdien skal legges inn et tilfeldig sted --> looper gjennom fra 1 til index, 
             og oppdaterer verdiene til slutt. 
             
* Oppgave 6: ...

* Oppgave 7: ...

* Oppgave 8: a) Sjekker om iteratorendringer er lik endringer, kaster en ConcurrentModificationException hvis ikke. Hvis hasNext() ikke er true, kastes en NoSuchElementException (altså hvis det ikke er flere igjen i listen). Setter fjernOk til true. Verdien tl denne lagres i denneVerdi og denne flyttes til neste node. Verdien til denne returneres. 
             b) Returnerer en instans av iteratorklassen.
             c) Bruker finnNode til å finne indeksen og oppretter en node med denne. Sjekker om indeks er større enn antall og mindre enn 0, hvis det er tilfellet, kastes en IndexOutOfBoundsException. Setter denne = newNode, som er den nye noden med indeks. fjernOk = false blir sann når next() kalles. (Som i den ferdigkodede konstruktøren. 
             d) Bruker indeksKontroll til å sjekke om indeks er lovlig, deretter returneres en instans av iteratorklassen ved hjelp av konstruktøren i c).
             
* Oppgave 9: Først sjekker vi om listen er tom --> kan ikke fjerne fra en tom liste. Deretter sjekker vi om antall = 1 og setter hode og hale = null
             Så sjekker vi om halen skal fjernes --> oppdaterer hale til hale.forige, så sjekker vi om hode skal fjernes og setter hode = hode.neste
             Til slutt sjekker vi om det er en tilfeldig verdi som skal fjernes og oppdaterer veriene før og etter 
             
* Oppgave 10: Først sjekker jeg om listen er tom og returnerer dersom den er det. 
              Så lager jeg en dobbel loop, i og j, hvor 0 < i < antall - 1 og 1 < j < antall.
              Deretter sjekker jeg om verdiene på index i og j = null, hvis ikke henter jeg verdiene. 
              Tilfelle 1 og 3 tilsier at sortering ikke er nødvending, men i tilfelle 2, hvis a > b skal verdiene
              byttes om på. 


Vi får 2 feil når vi kjører testene. Èn på oppgave 3a og èn på oppgave 6zg
* Oppgave 3a: Testen bruker omlag 5000 ms på å kjøre gjennom, og vi får beskjed om at dette går "altfor sakte". Ut ifra 
              hva vi forstår er det metoden finnNode() som bruker for lang tid, men vi finner dessverre ingen annen løsning som 
              fungerer og er innenfor tidskravene.  

* Oppgave 6zg: Testen for hele oppgave 6 bruker omlag 8000 ms på å kjøre gjennom. Her har vi klart å fjerne omlag 1 sek av den
               opprinnelige tiden, men det var desverre ikke nok. Tiden løper fra oss og vi må dessverre lever slik som dette. 
               
* Helhetlig: Vi har brukt mye tid og hatet på noen av oppgavene, men vi føler alle at vi har lært masse. Vi har hjulpet hverandre 
             der det har vært nødvendig og vi har jobbet godt som et team, selvom vi stort sett har sittet hver for oss. 
