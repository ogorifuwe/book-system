package com.triloyed.booksystemnotequeueconsumer;

import com.sun.tools.corba.se.idl.constExpr.Not;
import com.triloyed.booksystemnotequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

  @RabbitListener(queues = BookSystemNoteQueueConsumerApplication.QUEUE_NAME)
  public void receiveMessage(Note msg) {
    System.out.println(msg.toString());
  }
}
