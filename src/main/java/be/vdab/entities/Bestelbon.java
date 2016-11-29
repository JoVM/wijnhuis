package be.vdab.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.enums.Bestelwijze;

/**
 * The persistent class for the bestelbonnen database table.
 * 
 */
@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date besteld;

	private Bestelwijze bestelwijze;
	private String gemeente;
	private String huisNr;
	private String naam;
	private String postCode;
	private String straat;
	private int versie;

	public Bestelbon() {
	}

	public long getId() {
		return this.id;
	}

	public Date getBesteld() {
		return this.besteld;
	}

	public Bestelwijze getBestelwijze() {
		return this.bestelwijze;
	}

	public String getGemeente() {
		return this.gemeente;
	}

	public String getHuisNr() {
		return this.huisNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public String getStraat() {
		return this.straat;
	}

	public int getVersie() {
		return this.versie;
	}

}