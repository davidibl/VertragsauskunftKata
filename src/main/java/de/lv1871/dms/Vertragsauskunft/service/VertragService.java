package de.lv1871.dms.Vertragsauskunft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lv1871.dms.Vertragsauskunft.data.Kundenrepository;
import de.lv1871.dms.Vertragsauskunft.data.Vertragsrepository;
import de.lv1871.dms.Vertragsauskunft.model.Vertrag;

@Service
public class VertragService {

	@Autowired
	private Kundenrepository kundenRepo;

	@Autowired
	private Vertragsrepository vertragRepo;

	public List<Vertrag> getVertraegeZuKundennummer(Long kundennummer) {
		return null;
	}

}
