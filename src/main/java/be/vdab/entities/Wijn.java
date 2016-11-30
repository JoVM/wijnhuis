package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the wijnen database table.
 * 
 */
@Entity
@Table(name = "wijnen")
public class Wijn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private byte beoordeling;
	private int inBestelling;
	private int jaar;
	private BigDecimal prijs;
	private int versie;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "soortid")
	private Soort soort;

//	public Wijn(byte beoordeling, int inBestelling, int jaar, BigDecimal prijs, int soortid, int versie) {
//		this.beoordeling = beoordeling;
//		this.inBestelling = inBestelling;
//		this.jaar = jaar;
//		this.prijs = prijs;
//		this.versie = versie;
//	}
//
//	protected Wijn() {
//	}

	public void setSoort(Soort soort) {
		if (this.soort != null && this.soort.getWijnen().contains(this)) {
			this.soort.remove(this);
		}
		this.soort = soort;
		if (soort != null && !soort.getWijnen().contains(this)) {
			soort.add(this);
		}
	}

	public long getId() {
		return this.id;
	}

	public byte getBeoordeling() {
		return this.beoordeling;
	}

	public int getInBestelling() {
		return this.inBestelling;
	}

	public int getJaar() {
		return this.jaar;
	}

	public BigDecimal getPrijs() {
		return this.prijs;
	}

	public int getVersie() {
		return versie;
	}

	public Soort getSoort() {
		return soort;
	}

	public static boolean isAantalValid(int aantal) {
		if (aantal <= 0) {
			return false;
		}
		return true;
	}

	public void inBestelling(int aantal) {
		inBestelling = inBestelling + aantal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jaar;
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
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
		Wijn other = (Wijn) obj;
		if (jaar != other.jaar)
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		return true;
	}

}