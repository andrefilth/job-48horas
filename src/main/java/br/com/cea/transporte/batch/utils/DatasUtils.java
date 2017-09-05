package br.com.cea.transporte.batch.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import lombok.extern.log4j.Log4j;

@Log4j
public class DatasUtils {

	private static final String PATTERN_DATE = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat formatadorDeData = new SimpleDateFormat(PATTERN_DATE);

	public static Date mostrarDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();
	}
	
	public static long mostrarDataAtualTime() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	public static java.sql.Date mostrarDataAtualSql(){
		return new java.sql.Date(mostrarDataAtualTime());
		
	}

	public static Date mostrarDataFuturo(Integer horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mostrarDataAtual());
		calendar.add(Calendar.HOUR_OF_DAY, horas);
		return calendar.getTime();
	}

	public static String mostrarDataAtualFormatada() {
		return formatadorDeData.format(mostrarDataAtual());

	}

	public static String mostrarDataFuturoFormatada(Integer horas) {
		return formatadorDeData.format(mostrarDataFuturo(horas));

	}

	public static XMLGregorianCalendar converterXMLGregorianCalendar(Date dataCriacao) {
		if(dataCriacao == null) {
			dataCriacao = new Date();
		}
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataCriacao);
		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			log.error("Erro ao converter datas XMLGregorianCalendar : - " + xmlDate);
		}
		return xmlDate;
	}
}
