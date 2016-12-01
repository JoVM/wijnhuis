package be.vdab.entities;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.valueobjects.BestelbonLijn;

public class BestelbonLijnTest {
	private Bestelbon bestelbon;

	@Before
	public void before() {
		bestelbon = new Bestelbon();
	}

//	@Test
//	public void maakEenBestelbonLijnInstantie() {
//		bestelbon.addBestelbonLijn(new BestelbonLijn(10, BigDecimal.valueOf(20), new Wijn()));
//	}

	@Test(expected = IllegalArgumentException.class)
	public void aantalMagNietGelijkZijnAanNul() {
		bestelbon.addBestelbonLijn(new BestelbonLijn(0, BigDecimal.valueOf(10), new Wijn()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void aantalMagNietKleinerZijnDanNul() {
		bestelbon.addBestelbonLijn(new BestelbonLijn(-1, BigDecimal.valueOf(10), new Wijn()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void prijsMagNietKleinerZijnDanNul() {
		bestelbon.addBestelbonLijn(new BestelbonLijn(2, BigDecimal.valueOf(-1), new Wijn()));
	}

	@Test(expected = NullPointerException.class)
	public void prijsMagNietNullZijn() {
		bestelbon.addBestelbonLijn(new BestelbonLijn(1, null, new Wijn()));
	}

}
