import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;

public class Receiver {

    public static void main(String[] args) {
        final Consumer consumer = new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {}

            @Override
            public void handleCancelOk(String consumerTag) {}

            @Override
            public void handleCancel(String consumerTag) {}

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {}

            @Override
            public void handleRecoverOk(String consumerTag) {}

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String s = new String(body);
                System.out.println(s);
            }

        };

        final Chat chatReceiver = Chat.connect("receiver", consumer);
    }
}
