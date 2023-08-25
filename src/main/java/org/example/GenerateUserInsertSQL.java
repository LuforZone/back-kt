package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.entity.User;

public class GenerateUserInsertSQL {
    //run data for test

    public static void main(String[] args) {
        List<User> users = generateUsers(144);
        for (User user : users) {
            String sql = generateInsertSQL(user);
            System.out.println(sql);
        }
    }

    private static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setName("User " + i);
            user.setPhone(generateRandomPhoneNumber());
            user.setSex(random.nextBoolean() ? "Male" : "Female");
            user.setEmail("user" + i + "@example.com");
            user.setUuid("uuid" + i);
            users.add(user);
        }
        return users;
    }

    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("1");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    private static String generateInsertSQL(User user) {
        return String.format("INSERT INTO works (name, phone, sex, email, uuid) VALUES ('%s', '%s', '%s', '%s', '%s');",
                user.getName(), user.getPhone(), user.getSex(), user.getEmail(), user.getUuid());
    }


}
