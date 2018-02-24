package pl.kayzone.dinnerapp.control;

import pl.kayzone.dinnerapp.entity.Dinner;

public class DinnerManager {

    private Dinner dinner;
    private String connectString;
    
	public Dinner getDinner() {
		return dinner;
	}
	public void setDinner(Dinner dinner) {
		this.dinner = dinner;
	}
	public String getConnectString() {
		return connectString;
	}
	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}


}

