package be.vdab.repositories;

import be.vdab.entities.Soort;

public class SoortRepository extends AbstractRepository {
	public Soort read(long id) {
		return getEntityManager().find(Soort.class, id);
	}
}
