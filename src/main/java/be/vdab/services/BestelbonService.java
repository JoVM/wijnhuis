package be.vdab.services;

import java.util.Set;

import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

import be.vdab.entities.Bestelbon;
import be.vdab.exceptions.RecordAangepastException;
import be.vdab.repositories.BestelbonRepository;
import be.vdab.repositories.WijnRepository;
import be.vdab.valueobjects.BestelbonLijn;

public class BestelbonService extends AbstractService {
	BestelbonRepository bestelbonrepository = new BestelbonRepository();
	WijnRepository wijnRepository = new WijnRepository();

	public Bestelbon read(long id) {
		return bestelbonrepository.read(id);
	}

	public void create(Bestelbon bestelbon, Set<BestelbonLijn> bestelbonlijnen) {
		beginTransaction();
		try {
			for (BestelbonLijn bestelbonlijn : bestelbonlijnen) {
				wijnRepository.inBestelling(bestelbonlijn.getWijn().getId(), bestelbonlijn.getAantal());
				bestelbon.addBestelbonLijn(bestelbonlijn);
			}
			bestelbonrepository.create(bestelbon);
			commit();
		} catch (RollbackException ex) {
			rollback();			
			if (ex.getCause() instanceof OptimisticLockException) {
				throw new RecordAangepastException();
			}
		} catch (RuntimeException ex) {
			rollback();
			throw ex;
		}
	}
}
