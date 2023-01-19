package org.example.sample_spring_batch;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SampleBatchConfiguration extends DefaultBatchConfiguration {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .generateUniqueName(true).build();
  }

  @Bean
  public JdbcTransactionManager transactionManager(DataSource dataSource) {
    return new JdbcTransactionManager(dataSource);
  }

  @Bean
  public Job helloJob(JobRepository jobRepository, Step helloStep) {
    return new JobBuilder("helloJob", jobRepository)
        .start(helloStep)
        .build();
  }

  @Bean
  public Step helloStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("helloStep", jobRepository)
        .tasklet(new MessageTasklet("Hello Spring Batch 5"), transactionManager)
        .build();
  }

}
