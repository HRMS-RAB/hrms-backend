package com.hrms.employee.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Value("${hrms.rabbitmq.employee.exchange}")
    private String exchangeName;
    @Value("${hrms.rabbitmq.employee.queue}")
    private String queueName;
    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String routingKey;

    @Bean
    public DirectExchange employeeExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue employeeQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding employeeBinding(DirectExchange employeeExchange, Queue employeeQueue) {
        return BindingBuilder.bind(employeeQueue).to(employeeExchange).with(routingKey);
    }

    /*— JSON converter & template —*/
    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(jacksonConverter());
        return rt;
    }
}



/*package com.hrms.employee.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ topology for the HRMS employee-events producer.
 * --------------------------------------------------------
 *  • Direct exchange  : hrms.employee.exchange   (durable)
 *  • Main queue       : hrms.employee.queue      (durable)
 *  • DLQ              : hrms.employee.queue.dlq  (durable)
 *  • Routing key      : hrms.employee.routing-key
 *
 *  The main queue is configured with DLX/DLK so any rejected
 *  message is routed to *.dlq for later inspection.
 *
 *  RabbitTemplate uses Jackson for JSON payloads.
 
@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Value("${hrms.rabbitmq.employee.exchange}")
    private String employeeExchange;

    @Value("${hrms.rabbitmq.employee.queue}")
    private String employeeQueue;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String employeeRoutingKey;
    
 // ---------- Exchange ----------------------------------------------------

    @Bean
    public DirectExchange employeeExchange(@Value("${hrms.rabbitmq.employee.exchange}") String exchangeName) {
        return ExchangeBuilder.directExchange(exchangeName).durable(true).build();
    }

    // ---------- Queues ------------------------------------------------------
    @Bean
    public Queue mainQueue(@Value("${hrms.rabbitmq.employee.queue}") String queueName,
                           @Value("${hrms.rabbitmq.employee.exchange}") String exchangeName) {
        return QueueBuilder.durable(queueName)
                           .withArgument("x-dead-letter-exchange", exchangeName)
                           .withArgument("x-dead-letter-routing-key", queueName + ".dlq")
                           .build();
    }
   

	@Bean
	public Queue deadLetterQueue(
	        @Value("${hrms.rabbitmq.employee.queue}") String queueName) {
	    return QueueBuilder.durable(queueName + ".dlq").build();
	}
    
    @Bean
    public Queue employeeQueue(
            @Value("${hrms.rabbitmq.employee.queue}") String queueName) {
        return new Queue(queueName, true);          // NO arguments
    }*/


    // ---------- Bindings ----------------------------------------------------

    /*@Bean
    public Binding mainBinding(Queue mainQueue, DirectExchange employeeExchange,
                               @Value("${hrms.rabbitmq.employee.routing-key}") String routingKey) {
        return BindingBuilder.bind(mainQueue).to(employeeExchange).with(routingKey);
    }
	
	
	@Bean
	public Binding dlqBinding(
	        Queue deadLetterQueue,
	        DirectExchange employeeExchange,
	        @Value("${hrms.rabbitmq.employee.queue}") String queueName) {
	    return BindingBuilder
	            .bind(deadLetterQueue)
	            .to(employeeExchange)
	            .with(queueName + ".dlq");
	}

    // ---------- Message conversion & template ------------------------------
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf,
                                         Jackson2JsonMessageConverter converter,
                                         @Value("${hrms.rabbitmq.employee.exchange}") String exchangeName,
                                         @Value("${hrms.rabbitmq.employee.routing-key}") String routingKey) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(converter);
        rt.setExchange(exchangeName);
        rt.setRoutingKey(routingKey);
        return rt;
    }

    
} */
   
