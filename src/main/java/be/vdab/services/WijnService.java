package be.vdab.services;

import java.util.Optional;

import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService {
	private final WijnRepository wijnRepository = new WijnRepository();

	public Optional<Wijn> read(long id) {
		return wijnRepository.read(id);
	}
}
