package de.lv1871.dms.Vertragsauskunft.data;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.lv1871.dms.Vertragsauskunft.model.Kunde;

@Service
public class Kundenrepository extends FileRepository {

	private ObjectMapper mapper = new ObjectMapper();

	public Optional<Kunde> getKunde(Long kundennummer) {
		try {
			if (kundennummer == 98782632L) {
				// wenn das passiert soll simuliert werden dass ein weiteres
				// System in der Aufrufkette einen Fehler verursacht hat!
			}
			Optional<File> file = getFile(kundennummer);
			if (!file.isPresent()) {
				return Optional.empty();
			}
			return Optional.ofNullable(mapper.readValue(file.get(), Kunde.class));
		} catch (IOException | NullPointerException e) {
			return Optional.empty();
		}
	}
}
