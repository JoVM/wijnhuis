package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.BestelbonLijn;

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

	@Embedded
	private Adres adres;

	private Bestelwijze bestelwijze;
	private String naam;
	private int versie;

	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid"))
	private Set<BestelbonLijn> bestelbonlijnen;

	public Bestelbon(Date besteld, Bestelwijze bestelwijze, String naam, int versie) {
		this.besteld = besteld;
		this.bestelwijze = bestelwijze;
		setNaam(naam);
		setAdres(adres);
		bestelbonlijnen = new HashSet<>();
		this.versie = versie;
	}

	protected Bestelbon() {
	}

	public void addBestelbonLijn(BestelbonLijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}

	public void removeBestelbonLijn(BestelbonLijn bestelbonlijn) {
		bestelbonlijnen.remove(bestelbonlijn);
	}

	public static boolean isNaamValid(String naam) {
		return naam != null && !naam.isEmpty();
	}

	public long getId() {
		return this.id;
	}

	public Set<BestelbonLijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}

	public Adres getAdres() {
		return adres;
	}

	public Date getBesteld() {
		return this.besteld;
	}

	public Bestelwijze getBestelwijze() {
		return this.bestelwijze;
	}

	public String getNaam() {
		return this.naam;
	}

	public int getVersie() {
		return this.versie;
	}

	public void setBesteld(Date besteld) {
		this.besteld = besteld;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

}