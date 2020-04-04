package RandomSolutions.JavaFundamentals.Exam2019August03Group1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pr03MessagesManager {

    public static void main(String[] args) throws IOException {
        final Map<String, User> users = new HashMap<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        final int capacity = Integer.parseInt(reader.readLine());

        processMessages(users, capacity, reader);

        System.out.printf("Users count: %d%n%s%n",
                users.size(),
                users.values().stream()
                        .sorted(Comparator
                                .comparingInt(User::getReceived).reversed()
                                .thenComparing(User::getName))
                        .map(User::info)
                        .collect(Collectors.joining(System.lineSeparator())));
    }

    private static void processMessages(Map<String, User> users, int capacity, BufferedReader reader) throws IOException {
        for (String input = reader.readLine(); !"Statistics".equals(input); input = reader.readLine()) {
            String[] tokens = input.split("=");
            String command = tokens[0];
            switch (command) {
            case "Add":
                String username = tokens[1];
                int sent = Integer.parseInt(tokens[2]);
                int received = Integer.parseInt(tokens[3]);
                users.putIfAbsent(username, new User(username, sent, received));
                break;
            case "Message":
                User sender = users.get(tokens[1]);
                User receiver = users.get(tokens[2]);
                if (sender != null && receiver != null) {
                    sender.send();
                    receiver.receive();
                    Stream.of(sender, receiver)
                            .filter(user -> user.getTotalMessages() >= capacity)
                            .forEach(user -> {
                                System.out.printf("%s reached the capacity!%n", user.getName());
                                users.remove(user.getName());
                            });
                }
                break;
            case "Empty":
                String target = tokens[1];
                if ("All".equals(target)) {
                    users.clear();
                } else {
                    users.remove(target);
                }
            }
        }
    }

    private static class User {
        private final String name;
        private int sent;
        private int received;

        public User(String name, int sent, int received) {
            this.name = name;
            this.sent = sent;
            this.received = received;
        }

        public int getReceived() {
            return received;
        }

        public String getName() {
            return name;
        }

        public void send() {
            sent++;
        }

        public void receive() {
            received++;
        }

        public String info() {
            return String.format("%s - %d", name, getTotalMessages());
        }

        public int getTotalMessages() {
            return sent + received;
        }
    }
}
