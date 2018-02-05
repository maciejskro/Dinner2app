package pl.kayzone.dinnerapp;

import pl.kayzone.dinnerapp.control.MenuItemManager;
import pl.kayzone.dinnerapp.control.UserManager;
import pl.kayzone.dinnerapp.entity.MenuDinner;
import pl.kayzone.dinnerapp.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class DemoDinner {

    public static void main(String[] args) {


        String conn = "mongodb://localhost:27017/dinner";
        System.out.println( " co robimy ?  au : user add , ar : user remove  ,da : dinner add  ");

        User user;
        UserManager um = new UserManager(conn);

        Scanner scan = new Scanner(System.in);

//        for (int i = 0 ; i< 2; i++) {
//            user = createUser(scan);
//            um.addUser(user);
//        }
//        um.save();
        List<User> result = um.findActive("name","gryzzly");
        if (result.size() >0 | result != null ) {
            System.out.println(" wynik wyszukannia  :" + result.toString());
            um.removeUser(result);
        } else {
            System.out.println("brak wyników wyszukania");
        }
        List<String> menulist = new ArrayList<>();
        for (int i =0 ; i< 6; i++) {
            System.out.print("Pozycja menu :");
            menulist.add(scan.nextLine());
        }

        MenuItemManager mdm = new MenuItemManager(conn);
        mdm.add(new MenuDinner(new Date(), menulist ));


       //DinnerManager dm = new DinnerManager();
//        List<User> userlist = null;
//        if (um.getUsersList().size() >0 ) {
//            userlist = um.getUsersList();
//        }
//
//        for (User u: userlist) {
//            System.out.println(" object: " + u.toString());
//        }

    }

    private static User createUser(Scanner scan) {
        User u;
        System.out.print( "Podaj nazwę usera :");
        String name = scan.nextLine();

        System.out.print("Podaj imie user :");
        String firstname  = scan.nextLine();

        System.out.print("Podaj nazwisko user:");
        String surname = scan.nextLine();

        System.out.print("Podaj konto  bankowe");
        String account = scan.nextLine();
        u = new User(name, firstname, surname, account);

        return u;
    }
}
