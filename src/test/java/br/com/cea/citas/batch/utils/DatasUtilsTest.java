package br.com.cea.citas.batch.utils;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cea.transporte.batch.utils.DatasUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasUtilsTest {

	private static final String PATTERN_DATE = "yyyy-MM-dd";

	private static Logger logger = Logger.getLogger(DatasUtilsTest.class);

	private static SimpleDateFormat formatadorDeData = new SimpleDateFormat(PATTERN_DATE);

	private String primeiraDataformatada;

	private String segundaDataformatada;

	@Before
	public void init() {
		logger.info("Inicando testes de DatasUtils...");
		Date dataAtual = Calendar.getInstance().getTime();
		this.primeiraDataformatada = formatadorDeData.format(dataAtual);
		logger.info("Data atual de inicialização: " + this.primeiraDataformatada);
	}

	@Test
	public void mostrarDataAtual() {
		Date dataAtual = DatasUtils.mostrarDataAtual();
		this.segundaDataformatada = formatadorDeData.format(dataAtual);
		assertEquals(primeiraDataformatada, segundaDataformatada);
		logger.info("Data atual da classe DatasUtils: " + this.segundaDataformatada);
	}
	
	@Test
	public void mostraDataFuturoRecebendoUmDia() {
		Integer valorConvertido = Integer.valueOf(StringUtils.remove("24:00",":00"));
		Date mostrarDataFuturo = DatasUtils.mostrarDataFuturo(valorConvertido);
		String dataFuturoFormatada = formatadorDeData.format(mostrarDataFuturo);
		logger.info("Data futuro somando 24 horas: " + dataFuturoFormatada);
		logger.info("Valor Convertido: " + valorConvertido);
	}
	
	@Test
	public void testeConversaoDataSql() {
		java.sql.Date dataAtualSql = DatasUtils.mostrarDataAtualSql();
		logger.info("Teste de convsersão de Sql Data: "+ dataAtualSql);
	}

}
