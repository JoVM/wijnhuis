package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Wijn;

@Embeddable
public class BestelbonLijn implements Serializable {
	private static final long serialVersionUID = 1L;

	private int aantal;
	private BigDecimal prijs;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "wijnid")
	private Wijn wijn;

	public BestelbonLijn(int aantal, BigDecimal prijs, Wijn wijn) {
		this.aantal = aantal;
		this.prijs = prijs;
		this.wijn = wijn;
	}

	protected BestelbonLijn() {

	}

	public void setAantal(int aantal) {
		if (aantal < 0) {

		}
	}

	public void setPrijs(BigDecimal prijs) {
		
	}

	public int getAantal() {
		return aantal;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public Wijn getWijn() {
		return wijn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wijn == null) ? 0 : wijn.hashCode());
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
		BestelbonLijn other = (BestelbonLijn) obj;
		if (wijn == null) {
			if (other.wijn != null)
				return false;
		} else if (!wijn.equals(other.wijn))
			return false;
		return true;
	}

}
