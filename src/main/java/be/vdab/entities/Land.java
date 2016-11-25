package be.vdab.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Land
 *
 */
@Entity
@Table(name = "landen")
public class Land implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private int versie;

	@OneToMany(mappedBy = "land")
	private Set<Soort> soorten;

	public Land(String naam, int versie) {
		this.naam = naam;
		this.versie = versie;
	}

	protected Land() {
	}

	public void add(Soort soort) {
		soorten.add(soort);
		if (soort.getLand() != this) {
			soort.setLand(this);
		}
	}

	public void remove(Soort soort) {
		soorten.remove(soort);
		if (soort.getLand() == this) {
			soort.setLand(null);
		}
	}

	public Set<Soort> getSoorten() {
		return soorten;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public int getVersie() {
		return versie;
	}

}
