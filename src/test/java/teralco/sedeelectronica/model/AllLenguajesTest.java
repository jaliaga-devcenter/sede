package teralco.sedeelectronica.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AllLenguajesTest {
	@Test
	public void allLenguagesTest() {
		ParadaLenguaje p = new ParadaLenguaje();
		NormativaLenguaje n = new NormativaLenguaje();
		ModeloLenguaje m = new ModeloLenguaje();
		LicitacionLenguaje l = new LicitacionLenguaje();
		DocumentacionLenguaje d = new DocumentacionLenguaje();
		AvisoLenguaje av = new AvisoLenguaje();
		AperturaLenguaje ap = new AperturaLenguaje();
		AnuncioLenguaje an = new AnuncioLenguaje();
		AdjudicacionLenguaje ad = new AdjudicacionLenguaje();

		assertNotNull(p);
		assertNotNull(n);
		assertNotNull(m);
		assertNotNull(l);
		assertNotNull(d);
		assertNotNull(av);
		assertNotNull(ap);
		assertNotNull(an);
		assertNotNull(ad);
	}

}
