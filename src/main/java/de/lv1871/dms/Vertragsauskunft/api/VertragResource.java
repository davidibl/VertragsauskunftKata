package de.lv1871.dms.Vertragsauskunft.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.lv1871.dms.Vertragsauskunft.model.Beitrag;
import de.lv1871.dms.Vertragsauskunft.model.Vertrag;
import de.lv1871.dms.Vertragsauskunft.service.VertragService;

@Controller
@CrossOrigin
public class VertragResource {

	@Autowired
	private VertragService service;

	@RequestMapping(path = "/api/vertrag", method = RequestMethod.GET)
	public @ResponseBody List<Vertrag> getVertraege(@RequestParam(name = "kundennummer") Long kundennummer) {
		return service.getVertraegeZuKundennummer(kundennummer);
	}

	@RequestMapping(path = "/api/vertrag/{versicherungsnummer}/beitrag", method = RequestMethod.GET)
	public @ResponseBody Beitrag getBeitrag(@PathVariable(name = "versicherungsnummer") Long versicherungsnummer) {
		return null;
	}

	@RequestMapping(path = "/api/vertrag/{versicherungsnummer}/beitrag/zahlbeitrag", method = RequestMethod.GET)
	public @ResponseBody Double getZahlbeitrag(@PathVariable(name = "versicherungsnummer") Long versicherungsnummer) {
		return null;
	}

	@RequestMapping(path = "/api/vertrag/{versicherungsnummer}/jahresbeitrag/zahlbeitrag", method = RequestMethod.GET)
	public @ResponseBody Double getZahlbeitragJahr(
			@PathVariable(name = "versicherungsnummer") Long versicherungsnummer) {
		return null;
	}
}
