package pl.kayzone.dinnerapp;

import pl.kayzone.dinnerapp.control.MenuItemManager;
import pl.kayzone.dinnerapp.control.UserManager;
import pl.kayzone.dinnerapp.entity.MenuDinner;
import pl.kayzone.dinnerapp.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DemoDinner {

	public static void main(String[] args) {

        String conn = "mongodb://localhost:27017/dinner";
        System.out.println(" co robimy ?  au : user add , ar : user remove  ,da : dinner add  ");

        
        @SuppressWarnings({ "unused" })
		User user;
        UserManager um = new UserManager(conn);

        
        try(Scanner scan = new Scanner(System.in) ) {

//        for (int i = 0 ; i< 2; i++) {
//            user = createUser(scan);
//            um.addUser(user);
//        }
//        um.save();
        List<User> result = um.findActive("name", "gryzzly");
        if (result.size() > 0 | result != null) {
            System.out.println(" wynik wyszukannia  :" + result.toString());
            um.removeUser(result);
        } else {
            System.out.println("brak wyników wyszukania");
        }
        HashMap<String,Double> menulist = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            System.out.print("Pozycja menu :");
            String item = ""+(i+1)+". "+ scan.nextLine();
            System.out.print("Cena ");
            Double price;
            try {
                price = Double.parseDouble(scan.nextLine().trim().replace(',','.'));
            } catch (Exception e) {
                price = 0.0;
            }

            menulist.put(item, price);
        }
      
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        MenuItemManager mdm = new MenuItemManager(conn);
        mdm.add(new MenuDinner(LocalDateTime.of(2018,3,5, 10,2,22,334897), menulist));


        //DinnerManager dm = new DinnerManager();
//        List<User> userlist = null;
//        if (um.getUsersList().size() >0 ) {
//            userlist = um.getUsersList();
//        }
//
//        for (User u: userlist) {
//            System.out.println(" object: " + u.toString());
//        }

        LocalDateTime localDateTime = LocalDateTime.parse("2018-02-16T00:00:00");

        ZoneId zoneId = ZoneId.of("UTC");
        System.out.println("ZoneId: " + zoneId  + " data: "  + localDateTime);

        System.out.println("ZonedDateTime: " + ZonedDateTime.of(localDateTime, zoneId));
        System.out.println("ZonedDateTimeParse: " + ZonedDateTime.parse("2018-02-04T10:15:30+02:00[Europe/Warsaw]"));

        MenuDinner md = mdm.find(localDateTime);
        System.out.println("menu na dzień \n" +
                localDateTime + " to  \n" +
                md.getWeekDayName()+ " \n" + md.toString() );
       // mdm.remove(md);
        System.out.println(mdm.findListMenu(localDateTime).toString());
        }
        catch (Exception e) {
        	e.printStackTrace();
        }


    }

    @SuppressWarnings("unused")
	private static User createUser(Scanner scan) {
        User u;
        System.out.print("Podaj nazwę usera :");
        String name = scan.nextLine();

        System.out.print("Podaj imie user :");
        String firstname = scan.nextLine();

        System.out.print("Podaj nazwisko user:");
        String surname = scan.nextLine();

        System.out.print("Podaj konto  bankowe");
        String account = scan.nextLine();
        u = new User(name, firstname, surname, account);

        return u;
    }
}
