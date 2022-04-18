

public class Sender {
    public static void main(String[] args) {
        System.out.println(args);

        final Chat chatSender = new Chat("sender");
        while (true) {
            chatSender.send();
        }
    }
}
