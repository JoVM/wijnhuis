package be.vdab.services;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService {
	private final WijnRepository wijnRepository = new WijnRepository();

	public Wijn read(long id) {
		return wijnRepository.read(id);
	}

	public Wijn readById(long id) {
		return wijnRepository.readById(id);
	}
}
