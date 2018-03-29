package teralco.sedeelectronica.gexflow.converter;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gexflow.wsdl.CategoriaWS;
import gexflow.wsdl.SubcategoriaWS;
import gexflow.wsdl.SubcategoriasWS;
import teralco.sedeelectronica.app.Application;
import teralco.sedeelectronica.gexflow.dto.CategoriaDTO;
import teralco.sedeelectronica.gexflow.dto.SubcategoriaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@Ignore
public class CategoriaConverterTest {

	private static final String DESCRIPCION_1 = "DESCRIPCION 1";
	private static final String TEST = "TEST";
	private static final int ID_CATEGORIA = 12;
	@Autowired
	private CategoriaConverter converter;

	@Test
	public void nullCategoriaTest() {
		// ARRANGE

		// ACT
		CategoriaDTO createFrom = this.converter.createFrom(null);
		// ASSET
		Assert.assertNull(createFrom);
	}

	@Test
	public void conPropiedadesTest() {
		// ARRANGE
		CategoriaWS categoriaWS = nuevaCategoriaWS();

		// ACT
		CategoriaDTO dto = this.converter.createFrom(categoriaWS);

		// ASSET
		Assert.assertEquals(ID_CATEGORIA, dto.getIdCategoria().intValue());
		Assert.assertEquals(TEST, dto.getNombre());
		Assert.assertEquals(DESCRIPCION_1, dto.getDescripcion());
	}

	@Test
	public void conSubCategoriasTest() {
		// ARRANGE
		CategoriaWS categoriaWS = nuevaCategoriaWS();
		categoriaWS.setSubcategorias(new SubcategoriasWS());

		categoriaWS.getSubcategorias().getSubcategoria().add(nuevaSubcategoria());
		categoriaWS.getSubcategorias().getSubcategoria().add(nuevaSubcategoria());
		categoriaWS.getSubcategorias().getSubcategoria().add(nuevaSubcategoria());
		categoriaWS.getSubcategorias().getSubcategoria().add(nuevaSubcategoria());

		// ACT
		CategoriaDTO dto = this.converter.createFrom(categoriaWS);

		// ASSET
		Assert.assertEquals(ID_CATEGORIA, dto.getIdCategoria().intValue());
		Assert.assertEquals(TEST, dto.getNombre());
		Assert.assertEquals(DESCRIPCION_1, dto.getDescripcion());

		Assert.assertEquals(4, dto.getSubcategorias().size());

		SubcategoriaDTO subcategoriaDTO = dto.getSubcategorias().get(0);
		Assert.assertEquals(ID_CATEGORIA, subcategoriaDTO.getIdSubcategoria().intValue());
		Assert.assertEquals(TEST, subcategoriaDTO.getNombre());
		Assert.assertEquals(DESCRIPCION_1, subcategoriaDTO.getDescripcion());
	}

	private SubcategoriaWS nuevaSubcategoria() {
		SubcategoriaWS subcategoria = new SubcategoriaWS();
		subcategoria.setIdSubcategoria(ID_CATEGORIA);
		subcategoria.setNombre(TEST);
		subcategoria.setDescripcion(DESCRIPCION_1);
		return subcategoria;
	}

	private CategoriaWS nuevaCategoriaWS() {
		CategoriaWS categoriaWS = new CategoriaWS();
		categoriaWS.setIdCategoria(ID_CATEGORIA);
		categoriaWS.setNombre(TEST);
		categoriaWS.setDescripcion(DESCRIPCION_1);
		return categoriaWS;
	}

}
