package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

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

	public Land(String naam, int versie) {
		this.naam = naam;
		this.versie = versie;
	}

	protected Land() {
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
