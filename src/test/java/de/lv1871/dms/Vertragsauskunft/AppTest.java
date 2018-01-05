package de.lv1871.dms.Vertragsauskunft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import de.lv1871.dms.Vertragsauskunft.model.Beitrag;
import de.lv1871.dms.Vertragsauskunft.model.Vertrag;
import de.lv1871.dms.Vertragsauskunft.model.Zahlweise;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class AppTest {

	@Autowired
	private TestRestTemplate restTemplate;

	// Alle Verträge, die im Kunden 511718 referenziert und "AKTIV" sind (siehe
	// 111111.json) sollen geliefert werden.
	@Test
	public void testGetVertrag() throws UnsupportedEncodingException, Exception {
		ResponseEntity<Vertrag[]> responseEntity = restTemplate.getForEntity("/api/vertrag?kundennummer=111111",
				Vertrag[].class);
		Vertrag[] vertraege = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(vertraege);
		assertEquals(2, vertraege.length);
		assertTrue(Arrays.asList(vertraege).stream().filter(v -> v.getVersicherungsnummer() == 55555555L).count() == 1);
		assertTrue(Arrays.asList(vertraege).stream().filter(v -> v.getVersicherungsnummer() == 66666666L).count() == 1);
	}

	// Wenn keine Kundennummer übergeben wird, soll ein Internal Service Errer
	// kommen. Im Response Body soll die Message
	// "Der Kunde konnte nicht gefunden werden" enthalten sein.
	@Test
	public void testgetVertraegeKundennummerNull() throws UnsupportedEncodingException, Exception {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/vertrag?kundennummer=", String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody().contains("Der Kunde konnte nicht gefunden werden"));
	}

	// Wenn man die Jahresbeitrag Resource abruft, soll eben dieser kommen,
	// sofern der Vertrag zahlweise jährlich hat.
	@Test
	public void testGetJahresbeitrag() throws UnsupportedEncodingException, Exception {
		ResponseEntity<Double> responseEntity = restTemplate
				.getForEntity("/api/vertrag/66666666/jahresbeitrag/zahlbeitrag", Double.class);
		Double zahlbeitrag = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(zahlbeitrag);
		assertEquals(new Double(102.45), zahlbeitrag);
	}

	// Wenn man die Jahresbeitrag Resource abruft und der Vertrag hat Zahlweise
	// Monatlich, soll der Monatliche Zahlbeitrag mit 12 multipliziert und
	// geliefert werden.
	@Test
	public void testGetJahresbeitragWhenMonatlicheZahlweise() throws UnsupportedEncodingException, Exception {
		ResponseEntity<Double> responseEntity = restTemplate
				.getForEntity("/api/vertrag/55555555/jahresbeitrag/zahlbeitrag", Double.class);
		Double zahlbeitrag = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(zahlbeitrag);
		assertEquals(new Double(1229.4), zahlbeitrag);
	}

	// Wenn man den Jahresbeitrag eines Vertrags abruft, der gesperrt ist, soll
	// ein 404 zurück kommen.
	@Test
	public void testGetJahresbeitragWhenVertragGesperrt() throws UnsupportedEncodingException, Exception {
		HttpStatus statusCode = restTemplate
				.getForEntity("/api/vertrag/77777777/jahresbeitrag/zahlbeitrag", String.class).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
	}

	// Wenn man den Jahresbeitrag eines Vertrags abruft, der keinen Beitrag hat,
	// soll
	// ein 404 zurück kommen.
	@Test
	public void testGetJahresbeitragWhenVertragOhneZahlbeitrag() throws UnsupportedEncodingException, Exception {
		HttpStatus statusCode = restTemplate
				.getForEntity("/api/vertrag/88888888/jahresbeitrag/zahlbeitrag", String.class).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
	}

	// Bei Abfrage der zahlbeitrag Resource soll schlicht der Zahlbeitrag
	// geliefert werden.
	@Test
	public void testGetZahlbeitrag() throws UnsupportedEncodingException, Exception {
		ResponseEntity<Double> responseEntity = restTemplate.getForEntity("/api/vertrag/55555555/beitrag/zahlbeitrag",
				Double.class);
		Double zahlbeitrag = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(zahlbeitrag);
		assertEquals(new Double(102.45), zahlbeitrag);
	}

	// Bei Abfrage der zahlbeitrag Resource bei gesperrten Verträgen soll
	// natürlich ein 404 kommen..
	@Test
	public void testGetZahlbeitragWhenVertragGesperrt() throws UnsupportedEncodingException, Exception {
		HttpStatus statusCode = restTemplate.getForEntity("/api/vertrag/77777777/beitrag/zahlbeitrag", String.class)
				.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
	}

	// Bei Abfrage der Beitrg Resource soll das Beitragsobjekt kommen.
	@Test
	public void testGetBeitrag() throws UnsupportedEncodingException, Exception {
		ResponseEntity<Beitrag> responseEntity = restTemplate.getForEntity("/api/vertrag/55555555/beitrag",
				Beitrag.class);
		Beitrag beitrag = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(beitrag);
		assertEquals(new Double(102.45), beitrag.getZahlbeitrag());
		assertEquals(Zahlweise.MONATLICH, beitrag.getZahlweise());
	}

	// Und auch wenn der Beitrag eines gesperrten Vertrags abgefragt wird, soll
	// in 404 fliegen
	@Test
	public void testGetBeitragWhenVertragGesperrt() throws UnsupportedEncodingException, Exception {
		HttpStatus statusCode = restTemplate.getForEntity("/api/vertrag/77777777/beitrag", String.class)
				.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
	}

}
