package com.doubley.batch.communes;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import com.doubley.batch.communes.bean.Commune;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	   @Autowired
	    public JobBuilderFactory jobBuilderFactory;

	    @Autowired
	    public StepBuilderFactory stepBuilderFactory;

	    @Autowired
	    EntityManagerFactory emf;
	    
	    protected static class CommuneFieldSetMapper implements FieldSetMapper<Commune> {
	    	
	    	@Override
	    	public Commune mapFieldSet(FieldSet fieldSet) throws BindException {
	    		Commune commune = new	Commune();
	    		commune.setCodeInsee(fieldSet.readInt(0));
	    		commune.setCodePostal(fieldSet.readInt(1));
	    		commune.setNom(fieldSet.readString(2));
	    		commune.setDepartement(fieldSet.readString(3));
	    		commune.setRegion(fieldSet.readString(4));
	    		commune.setStatut(fieldSet.readString(5));
	    		commune.setAltitude(fieldSet.readString(6));
	    		commune.setSuperficie(fieldSet.readFloat(7));
	    		commune.setPopulation(fieldSet.readFloat(8));
	    		commune.setGeo(fieldSet.readString(9));

	    		return commune;
	    	}
	    }
	    
	    
	    // tag::readerwriterprocessor[]
	    //dernière modification
	    @Bean
	    public FlatFileItemReader<Commune> reader() {
	    		
	        final FlatFileItemReader<Commune> reader = new FlatFileItemReader<>();
	        reader.setResource(new ClassPathResource("communes.csv"));
	        reader.setLinesToSkip(1);
	        
	        final DefaultLineMapper<Commune> lineMapper = new DefaultLineMapper<>();
            final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(";");
	        final CommuneFieldSetMapper fieldMapper = new CommuneFieldSetMapper();
	        
	        lineMapper.setLineTokenizer(lineTokenizer);
	        lineMapper.setFieldSetMapper(fieldMapper);
	        reader.setLineMapper(lineMapper);
	        return reader;
	    }

	    @Bean
	    public CommuneItemProcessor processor() {
	        return new CommuneItemProcessor();
	    }
	    
	   /* 
	    @Bean
	    public FlatFileItemWriter<Commune> itemWriter() {
	            return  new FlatFileItemWriterBuilder<Commune>()
	                                       .name("itemWriter")
	                                       .resource(new ClassPathResource("output.txt"))
	                                       .lineAggregator(new PassThroughLineAggregator<>())
	                                       .build();
	    }
	    */
/*
	    @Bean
	    public JdbcBatchItemWriter<Commune> writer(DataSource dataSource) {
	        return new JdbcBatchItemWriterBuilder<Commune>()
	            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	            .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
	            .dataSource(dataSource)
	            .build();
	    }
	    */
	    
	    @Bean
	    public JpaItemWriter<Commune> writer() {
	        JpaItemWriter<Commune> communeJpaItemWriter = new JpaItemWriter<>();
	        communeJpaItemWriter.setEntityManagerFactory(emf);
	        return communeJpaItemWriter;
	    }
	     
	    // end::readerwriterprocessor[]

	    // tag::jobstep[]
	  @Bean
	    public Job importCommuneJob(JobCompletionNotificationListener listener, Step step1) {
	        return jobBuilderFactory.get("importCommuneJob")
	            .incrementer(new RunIdIncrementer())
	            .listener(listener)
	            .flow(step1)
	            .end()
	            .build();
	    }

	    @Bean
	    public Step step1(JpaItemWriter<Commune> writer) {
	        return stepBuilderFactory.get("step1")
	            .<Commune, Commune> chunk(10)
	            .reader(reader())
	            .processor(processor())
	            .writer(writer)
	            .build();
	    }
}
