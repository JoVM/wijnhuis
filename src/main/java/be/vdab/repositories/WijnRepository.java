package be.vdab.repositories;

import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {
	public Wijn read(long id) {
		return getEntityManager().find(Wijn.class, id);
	}

	public Wijn readById(long id) {
		return getEntityManager().createNamedQuery("Wijn.metId", Wijn.class).setParameter("id", id).getSingleResult();
	}
}
