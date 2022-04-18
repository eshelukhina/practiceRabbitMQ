import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public final class Chat {

    private Channel channel;
    private final Scanner scanner = new Scanner(System.in);
    private final String username;

    public Chat(final String username) {
        this.username = username;
    }

    public static Chat connect(String username, Consumer consumer) {
        final Chat chat = new Chat(username);

        try {
            chat.getChannel().basicConsume("chat", consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chat;
    }

    public void send() {
        System.out.print(username + " - ");
        final String message = scanner.nextLine();

        String s = username + " - " + message;
        try {
            getChannel().basicPublish("", "chat", null, s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Channel getChannel() {
        if (channel == null) {
            try {
                channel = MyConnectionFactory.getChannel();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        return channel;
    }
}
