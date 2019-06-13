package contracts;

import exception.AccountExistsException;
import exception.InvalidCredentialsException;
import models.Account;

import java.util.ArrayList;

public interface AccountContract {
	interface View {
		void setController(Controller controller);

		void showLeaderboard(ArrayList<Account> accounts);
	}

	interface Controller {
		void loadLeaderboard();
		void createAccount(String username, String password) throws AccountExistsException;
		void loginAccount(String username, String password) throws InvalidCredentialsException;
		void saveGameData();
		void loadAccounts();
	}
}
