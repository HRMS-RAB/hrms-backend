package com.hrms.employee.config;

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
 */
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
    public DirectExchange employeeExchange() {
        return ExchangeBuilder
                .directExchange(employeeExchange)
                .durable(true)
                .build();
    }

    // ---------- Queues ------------------------------------------------------

    @Bean
    public Queue mainQueue() {
        // employeeQueue = "hrms.employee.queue"
        // employeeExchange = "hrms.employee.exchange"
        return QueueBuilder
            .durable(employeeQueue)
            // exactly match the existing DLQ name: "hrms.employee.queue.dlq"
            .withArgument("x-dead-letter-exchange", employeeExchange)
            .withArgument("x-dead-letter-routing-key", employeeQueue + ".dlq")
            .build();
    }


    // ---------- Bindings ----------------------------------------------------

    @Bean
    public Binding mainBinding(Queue mainQueue, DirectExchange employeeExchange) {
        return BindingBuilder.bind(mainQueue)
                             .to(employeeExchange)
                             .with(employeeRoutingKey);
    }

    @Bean
    public Binding dlqBinding(Queue deadLetterQueue, DirectExchange employeeExchange) {
        return BindingBuilder.bind(deadLetterQueue)
                             .to(employeeExchange)
                             .with(employeeRoutingKey + ".dlq");
    }

    // ---------- Message conversion & template ------------------------------

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(messageConverter());
        rt.setExchange(employeeExchange);          // default exchange for send()
        rt.setRoutingKey(employeeRoutingKey);      // default routing key
        return rt;
    }
}



//++++++++++++++++++++++++++++++++
/*package com.hrms.employee.config;

import org.springframework.amqp.core.*;
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
    private String employeeExchange;

    @Value("${hrms.rabbitmq.employee.queue}")
    private String employeeQueue;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String employeeRoutingKey;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(employeeExchange, true, false);
    }

    @Bean
    public Queue employeeQueue() {
        return QueueBuilder.durable(employeeQueue)
                .withArgument("x-dead-letter-exchange", employeeExchange)
                .withArgument("x-dead-letter-routing-key", "employee.dlq")
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(employeeQueue + ".dlq").build();
    }

    @Bean
    public Binding binding(Queue employeeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(employeeQueue).to(exchange).with(employeeRoutingKey);
    }

    @Bean
    public Binding dlqBinding(Queue deadLetterQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deadLetterQueue).to(exchange).with("employee.dlq");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(messageConverter());
        return rt;
    }
}
*/
//++++++++++++++++++++++++++++++++++++++


/*package com.hrms.employee.config;

import org.springframework.amqp.core.*;
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
    private String employeeExchange;

    @Value("${hrms.rabbitmq.employee.queue}")
    private String employeeQueue;

    @Value("${hrms.rabbitmq.employee.routing-key}")
    private String employeeRoutingKey;     // "employee.*" for topic, "employee.created" for direct

    @Bean
    public DirectExchange exchange() {      // switch to DirectExchange if you prefer concrete keys
        return new DirectExchange(employeeExchange, true, false);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(employeeQueue).build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(employeeRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf) {
        RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(messageConverter());
        return rt;
    }
}*/
