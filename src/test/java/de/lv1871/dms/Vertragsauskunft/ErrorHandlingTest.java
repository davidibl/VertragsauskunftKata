package de.lv1871.dms.Vertragsauskunft;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = App.class)
public class ErrorHandlingTest {

	// BITTE BEACHTEN!
	// In der application.yml einen eigenen port auswählen!
	// Alle restaufrufe in diesem test dürfen nicht mit dem TestRestTemplate
	// implementiert werden! Man kann dann das normale restTemplate verwenden
	// und den Url http://localhost:<euer_port> aufrufen. Der Server wird in
	// dieser Konfiguration wirklich gestartet!
	// Zudem, für alle JEE Leute. Das RestEasy Client Framework (RestTemplate
	// sucks!) steht vollständig zur Verfügung. Man kann die Client Aufrufe also
	// auch mit Resteasy Client implementieren.

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	// Aufruf soll Vertrag zu Kundennummer 98782632 abrufen. Hier soll simuliert
	// werden dass ein remote system einen Fehler verursacht. Dies soll dann
	// auch in diesem Test korrekt erkannt werden. Der Aufruf soll eine
	// Exception verursachen die diese Information und eineMessage enthält.
	@Test
	public void testGetVertraegeRemoteSystemThrowsError() {
	}

	// Aufruf soll Vertrag zu Kundennummer 4564646 abrufen. Der Server soll
	// mitteilen dss dieser Kunde nicht existiert. Der Aufruf soll eine
	// entsprechende Exception mit einer meldung enthalten.
	@Test
	public void testGetVertraegeKundeNichtGefunden() {
	}

	// Aufruf soll Beitrag zu Vertragsnummer 78958949 abrufen. Der Server soll
	// einen allgemeinen Fehler verursachen und eine sprechende Nachricht
	// schicken.
	@Test
	public void testGetBeitragAllgemeinerfehler() {
	}

	// Aufruf soll im Server als invalides Argument erkannt werden. Der Aufruf
	// soll im Client eine entsprechende Exception mit passender nachricht
	// verursachen in der die Invaliden Felder in einem datenmodell stehen.
	// Idealerweise ist das eine Liste aus fehlerhaften Feldern mit den Fehlern
	// pro Feld.
	// Die entsprechende Fehlerdatenstruktur soll in der Exception als eigenes
	// Feld vorkommen und so benutzbar werden.
	@Test
	public void testGetVertraegKundeAllgemeinerfehler() {
	}

	// Vorletzte Aufgabe:
	// Sofern noch nicht geschehen, erarbeitet ein intuitives Konzept
	// generalisiert über ein Standardverfahren die Fehler korrekt zu verurschen
	// und im Client auszuwerten.

	// Letzte Aufgabe:
	// Jeder Fehler soll auf dem Server gelogged werden sobald die Schnittstelle
	// fehlerhaft passiert wird. Das heißt bei jedem Fehlerhaften Aufruf soll
	// der Fehler als solcher sowie alle Aufrufparameter und der Methodenname in
	// der Konsole erscheinen. Wer mag darf hierzu gerne auch einen test
	// implementieren. Mir fehlt leider Zeit und Muße an der Stelle. :-(

}
