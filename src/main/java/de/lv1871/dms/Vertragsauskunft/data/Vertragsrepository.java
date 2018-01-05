package de.lv1871.dms.Vertragsauskunft.data;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.lv1871.dms.Vertragsauskunft.model.Vertrag;

@Service
public class Vertragsrepository extends FileRepository {

	private ObjectMapper mapper = new ObjectMapper();

	public Optional<Vertrag> getVertrag(Long versicherungsnummer) {
		try {
			Optional<File> file = getFile(versicherungsnummer);
			if (!file.isPresent()) {
				return Optional.empty();
			}
			return Optional.ofNullable(mapper.readValue(file.get(), Vertrag.class));
		} catch (IOException | NullPointerException e) {
			return Optional.empty();
		}
	}
}
