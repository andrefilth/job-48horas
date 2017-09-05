package br.com.cea.transporte.batch.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cea.transporte.batch.model.Cita;
import br.com.cea.transporte.batch.processor.CitasProcessor;
import br.com.cea.transporte.batch.reader.CitasItemReader;
import br.com.cea.transporte.batch.writer.CitasWriter;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;


	@Bean
	public Step loadCitasStep(@Qualifier("citasItemReader") ItemReader<Cita> reader,
			@Qualifier("citasProcessor") ItemProcessor<Cita, Cita> processor,
			@Qualifier("citasWriter") ItemWriter<Cita> writer) {

		CompositeItemProcessor<Cita, Cita> compositeProcessor = new CompositeItemProcessor<>();
		List<ItemProcessor<Cita, Cita>> itemProcessors = new ArrayList<>();
		itemProcessors.add(processor);
		compositeProcessor.setDelegates(itemProcessors);
		
		return stepBuilderFactory.get("loadCitasStep")
				.<Cita, Cita>chunk(10)
				.reader(reader)
				.processor(compositeProcessor)
				.writer(writer)
				.build();
	}


	@Bean
	public Job job(@Qualifier("loadCitasStep") Step loadCitasStep) throws Exception {
		return jobBuilderFactory.get("job")
								.incrementer(new RunIdIncrementer())
								.start(loadCitasStep)
								.build();
	}
	
	@Bean
	public ItemReader<Cita> citasItemReader() {
		return new CitasItemReader();
	}

	@Bean
	public ItemProcessor<Cita, Cita> citasProcessor() {
		return new CitasProcessor();
	}

	@Bean
	public ItemWriter<Cita> citasWriter() {
		return new CitasWriter();
	}

}
