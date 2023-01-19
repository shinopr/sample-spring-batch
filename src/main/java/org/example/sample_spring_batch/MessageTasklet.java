package org.example.sample_spring_batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MessageTasklet implements Tasklet {

  private final String message;

  public MessageTasklet(String message) {
    this.message = message;
  }

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    System.out.println("Message: " + message);
    return RepeatStatus.FINISHED;
  }
}