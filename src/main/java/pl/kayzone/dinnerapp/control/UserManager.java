package pl.kayzone.dinnerapp.control;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import pl.kayzone.dinnerapp.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserManager extends BaseManager implements Serializable {

    private List<User> userList;
    private Query<User> queryUser;

    public void addUser(User u) {
        u.setActive(true);
        userList.add(u);
    }

    public void save() {
        for (User u : userList) {
            super.save(u);
        }
    }

    public void removeUser(List<User> users) {
        UpdateOperations<User> operationUsers;
        for (User u : users) {
            queryUser = super.getDatastore().createQuery(User.class).field("_id").equal(u.getId());

            operationUsers = super.getDatastore().createUpdateOperations(User.class).set("isActive", false);
            super.getDatastore().update(queryUser, operationUsers);
        }
    }

    public List<User> findActive(String key, String value) {

        Query<User> query = super.getDatastore().createQuery(User.class);
        query.and(query.criteria("isActive").equal(true),
                query.criteria(key).equal(value));

        List<User> result = query.asList();
        if (result.size() > 0) {
            return result;
        } else {
            result.add(new User("Brak wyników wyszukania", " ", " ", "00"));
            return result;
        }

    }

    public List<User> find(String key, String value) {

        Query<User> query = super.getDatastore().createQuery(User.class);
        query.criteria(key).equal(value);
        return query.asList();
    }

    public List<User> getUsersList() {
        this.queryUser = super.getDatastore().createQuery(User.class);
        return this.queryUser.asList();
    }

    public UserManager(String conn) {
        super(conn);
        this.userList = new ArrayList<>();
        this.queryUser = super.getDatastore().createQuery(User.class);
    }

    public UserManager(User user, String conn) {
        this(conn);
        setUserInList(user);
        setConnectionString(conn);
    }

    public List<User> getUsers() {
        return userList;
    }

    public void setUsers(ArrayList<User> user) {
        this.userList = user;
    }

    public void setUserInList(User u) {
        this.userList.add(u);
    }

    public User getUser(int i) {
        return userList.get(i);
    }
}


