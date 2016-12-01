package be.vdab.repositories;

import java.util.Optional;

import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {
	public Optional<Wijn> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Wijn.class, id));
	}

	public void inBestelling(long id, int aantal) {
		read(id).ifPresent(wijn -> wijn.inBestelling(aantal));
	}
}
