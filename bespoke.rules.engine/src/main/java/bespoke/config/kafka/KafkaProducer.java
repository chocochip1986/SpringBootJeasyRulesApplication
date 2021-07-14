package bespoke.config.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<K,V> {
    @Autowired
    KafkaTemplate<K, V> kafkaTemplate;

    public void sendMessage(ProducerRecord<K, V> record) {
        kafkaTemplate.send(record);
    }

    public void sendMessage(String topic, V message) {
        kafkaTemplate.send(topic, message);
    }
}
