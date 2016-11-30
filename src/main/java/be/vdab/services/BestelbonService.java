package be.vdab.services;

import be.vdab.entities.Bestelbon;
import be.vdab.repositories.BestelbonRepository;

public class BestelbonService extends AbstractService {
	BestelbonRepository bestelbonrepository = new BestelbonRepository();

	public void create(Bestelbon bestelbon) {
		beginTransaction();
		bestelbonrepository.create(bestelbon);
		commit();
	}
}
