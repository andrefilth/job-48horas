//package br.com.cea.citas.batch.type;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//
//import br.com.cea.citas.batch.utils.DatasUtilsTest;
//
//public class ParametroTypeTest {
//
//	private static Logger logger = Logger.getLogger(DatasUtilsTest.class);
//
//	private static final int DOIS = 2;
//
//	@Test
//	public void pegaParametroJobPorCodigoDoJob48Horas() {
//		String parametro = ParametroType.retornaDescricaoPorCodigo(DOIS);
//		logger.info("Teste: " + parametro);
//		assertEquals("HORARIO_PROCESO_48_HORAS", parametro);
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void pegaParametroJobPassandoValorNulo() {
//		String parametro = ParametroType.retornaDescricaoPorCodigo(null);
//		logger.info("Teste: " + parametro);
//		assertNull(parametro);
//	}
//
//}
