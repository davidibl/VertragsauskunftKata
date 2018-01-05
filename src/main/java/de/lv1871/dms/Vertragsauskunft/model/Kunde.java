package de.lv1871.dms.Vertragsauskunft.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Kunde {

	private List<Long> vertraege;

	public List<Long> getVertraege() {
		return vertraege;
	}

	public void setVertraege(List<Long> vertraege) {
		this.vertraege = vertraege;
	}
}
