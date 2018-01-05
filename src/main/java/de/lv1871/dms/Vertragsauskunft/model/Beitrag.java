package de.lv1871.dms.Vertragsauskunft.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Beitrag {

	private Double zahlbeitrag;
	private Zahlweise zahlweise;

	public Double getZahlbeitrag() {
		return zahlbeitrag;
	}

	public void setZahlbeitrag(Double zahlbeitrag) {
		this.zahlbeitrag = zahlbeitrag;
	}

	public Zahlweise getZahlweise() {
		return zahlweise;
	}

	public void setZahlweise(Zahlweise zahlweise) {
		this.zahlweise = zahlweise;
	}
}
