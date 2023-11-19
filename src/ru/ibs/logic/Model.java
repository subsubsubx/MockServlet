package ru.ibs.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();
        model.put(1, new User("Jakiro", "Main", 699.99));
        model.put(2, new User("Фыва", "Самарский", 1234));
        model.put(3, new User("Leroy", "Jenkins", 5678.91));

    }

    public void addUser(int id, User user) {
        model.put(id, user);
    }

    public Map<Integer, User> getUserList() {
        return model;
    }
}
