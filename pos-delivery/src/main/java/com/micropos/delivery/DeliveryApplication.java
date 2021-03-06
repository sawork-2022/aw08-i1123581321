package com.micropos.delivery;

import com.micropos.delivery.model.Waybill;
import com.micropos.delivery.repository.WaybillRepository;
import com.micropos.dto.OrderDto;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.util.function.Consumer;

@SpringBootApplication
public class DeliveryApplication {
    private final WaybillRepository waybillRepository;

    @Autowired
    public DeliveryApplication(WaybillRepository waybillRepository) {
        this.waybillRepository = waybillRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    public Consumer<OrderDto> generateWaybill(){
        return (var dto) -> waybillRepository.save(new Waybill(dto.getId()));
    }
}