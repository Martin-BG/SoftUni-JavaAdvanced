package PractiacalExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr03TheVLogger {

    public static void main(String[] args) {

        Map<String, Vlogger> vloggers = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String input = reader.readLine();

            while (!"Statistics".equalsIgnoreCase(input)) {
                String[] tokens = input.split("\\s+");
                String user = tokens[0];
                if (!vloggers.containsKey(user) && "joined".equalsIgnoreCase(tokens[1])) {
                    vloggers.put(user, new Vlogger(user));
                } else if (vloggers.containsKey(user) && "followed".equalsIgnoreCase(tokens[1])) {
                    String follow = tokens[2];
                    if (vloggers.containsKey(follow) && !user.equals(follow)) {
                        vloggers.get(follow).followers.add(user);
                        vloggers.get(user).following.add(follow);
                    }
                }
                input = reader.readLine();
            }

            StringBuilder result = new StringBuilder();
            result.append(String.format("The V-Logger has a total of %d vloggers in its logs.%n", vloggers.size()));
            final int[] count = {1};
            final boolean[] isFirst = {true};
            vloggers.values().stream().sorted()
                    .forEachOrdered(
                            vlogger -> {
                                result.append(String.format("%d%s", count[0]++, vlogger.getInfo(isFirst[0])));
                                isFirst[0] = false;
                            });

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Vlogger implements Comparable<Vlogger> {
        String name;
        Set<String> followers;
        Set<String> following;

        Vlogger(String name) {
            this.name = name;
            followers = new TreeSet<>();
            following = new HashSet<>();
        }

        String getInfo(boolean showFollowers) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(". %s : %d followers, %d following%n", name, followers.size(), following.size()));
            if (showFollowers) {
                for (String follower : followers) {
                    sb.append(String.format("*  %s%n", follower));
                }
            }
            return sb.toString();
        }

        @Override
        public int compareTo(Vlogger other) {
            if (followers.size() != other.followers.size()) {
                return Integer.compare(other.followers.size(), followers.size());
            }

            return Integer.compare(following.size(), other.following.size());
        }
    }
}