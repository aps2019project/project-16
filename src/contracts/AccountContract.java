package contracts;

import models.Account;
import models.Leaderboard;

import java.util.ArrayList;

public interface AccountContract {
	interface View {
		void setController(Controller controller);

		void showLeaderboard(ArrayList<Account> accounts);
	}

	interface Controller {
		void loadLeaderboard();
		void createAccount(String username, String password);
		void loginAccount(String username, String password);
	}
}
