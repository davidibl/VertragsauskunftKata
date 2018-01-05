package de.lv1871.dms.Vertragsauskunft.data;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public class FileRepository {

	Optional<File> getFile(Long filename) {
		ClassLoader classLoader = getClass().getClassLoader();
		String fullPath = Optional.ofNullable(classLoader.getResource(String.format("%s.json", filename)))
				.map(URL::getFile).orElse(null);
		if (filename == null) {
			Optional.empty();
		}
		return Optional.of(new File(fullPath));
	}
}
