package be.vdab.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Soort
 *
 */
@Entity
@Table(name = "soorten")
public class Soort implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private int versie;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "landid")
	private Land land;

	@OneToMany(mappedBy = "soort")
	@OrderBy("jaar")
	private Set<Wijn> wijnen;

	public Soort(String naam, int versie) {
		this.naam = naam;
		this.versie = versie;
	}

	protected Soort() {
	}

	public void setLand(Land land) {
		if (this.land != null && this.land.getSoorten().contains(this)) {
			this.land.remove(this);
		}
		this.land = land;
		if (land != null && !land.getSoorten().contains(this)) {
			land.add(this);
		}
	}

	public void add(Wijn wijn) {
		wijnen.add(wijn);
		if (wijn.getSoort() != this) {
			wijn.setSoort(this);
		}
	}

	public void remove(Wijn wijn) {
		wijnen.remove(wijn);
		if (wijn.getSoort() == this) {
			wijn.setSoort(null);
		}
	}

	public Set<Wijn> getWijnen() {
		return wijnen;
	}

	public Land getLand() {
		return land;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + versie;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soort other = (Soort) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (versie != other.versie)
			return false;
		return true;
	}

}
