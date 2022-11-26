package Services.Kafka

import io.smallrye.reactive.messaging.annotations.Blocking
import io.smallrye.reactive.messaging.kafka.Record
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

class NotificationCourseUpdateConsumer {

    private final Logger log = LoggerFactory.getLogger(NotificationCourseUpdateConsumer.class);

    @Blocking
    @Incoming("kursus-in")
    public void consume(Record<Integer,String> kursus){
        log.info("Message Icoming: "+kursus.value());
    }

}
