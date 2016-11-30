package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;

	private String gemeente;
	private String huisNr;
	private String postCode;
	private String straat;

	public Adres(String gemeente, String huisNr, String postCode, String straat) {
		this.gemeente = gemeente;
		this.huisNr = huisNr;
		this.postCode = postCode;
		this.straat = straat;
	}

	protected Adres() {
	}

	public static boolean isGemeenteValid(String gemeente) {
		return gemeente != null && !gemeente.isEmpty();
	}

	public static boolean isHuisNrValid(String huisNr) {
		return huisNr != null && !huisNr.isEmpty();
	}

	public static boolean isPostCodeValid(String postCode) {
		return postCode != null && !postCode.isEmpty();
	}

	public static boolean isStraatValid(String straat) {
		return straat != null && !straat.isEmpty();
	}

	public String getGemeente() {
		return gemeente;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getStraat() {
		return straat;
	}

}
