package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Land;

public class LandRepository extends AbstractRepository {
	public List<Land> findAll() {
		return getEntityManager().createNamedQuery("Land.findAll", Land.class).getResultList();
	}
}
