package bespoke.config.kafka;

import bespoke.services.RuleEngineService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class KafkaConsumers {
    @Autowired private RuleEngineService ruleEngineService;
    @Autowired private ObjectMapper objectMapper;

    @KafkaListener(topics = "topic.one", containerFactory = "kafkaListenerContainerFactory")
    public void init(ConsumerRecord<String, byte[]> record) {
        //Retrieve notification configuration
        InitRuleEngineDto dto = convert(record.value(), InitRuleEngineDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.trigger(dto);
        }
    }

    @KafkaListener(topics = "topic.two", containerFactory = "kafkaListenerContainerFactory")
    public void retrieve(ConsumerRecord<String, byte[]> record) {
        RuleEngineDto dto = convert(record.value(), RuleEngineDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.retrieve(dto);
        }
    }

    @KafkaListener(topics = "topic.three", containerFactory = "kafkaListenerContainerFactory")
    public void findPopulation(ConsumerRecord<String, byte[]> record) {
        RuleEngineTwoDto dto = convert(record.value(), RuleEngineTwoDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.getCohort(dto);
        }
    }

    @KafkaListener(topics = "topic.four", containerFactory = "kafkaListenerContainerFactory")
    public void findCohortPage(ConsumerRecord<String, byte[]> record) {
        RuleEngineThreeDto dto = convert(record.value(), RuleEngineThreeDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.getCohortPage(dto);
        }
    }

    @KafkaListener(topics = "topic.five", containerFactory = "kafkaListenerContainerFactory")
    public void processCohortPage(ConsumerRecord<String, byte[]> record) {
        RuleEngineFourDto dto = convert(record.value(), RuleEngineFourDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.processCohortPage(dto);
        }
    }

    @KafkaListener(topics = "topic.six", containerFactory = "kafkaListenerContainerFactory")
    public void persistCohortPageInfo(ConsumerRecord<String, byte[]> record) {
        RuleEngineFiveDto dto = convert(record.value(), RuleEngineFiveDto.class);
        if (Objects.isNull(dto)) {
            System.out.println("JIALAT!");
        } else {
            ruleEngineService.persistCohortPageInfo(dto);
        }
    }

    private <T> T convert(byte[] payload, Class<T> klass) {
        try {
            return this.objectMapper.readValue(payload, klass);
        } catch (IOException e) {
            System.out.println("Unable to parse to "+InitRuleEngineDto.class.getName());
            return null;
        }
    }
}
