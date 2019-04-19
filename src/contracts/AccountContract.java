package contracts;

import models.Leaderboard;

public interface AccountContract {
	interface View {
		void setController(Controller controller);

		void showUsernameProblemError(String message);
		void showPasswordProblemError(String message);
		void loginSuccessMSG(String username);
		void showLeaderboard(Leaderboard leaderboard);
	}

	interface Controller {
		void loadLeaderboard();
		void createAccount(String username, String password);
		void loginAccount(String username, String password);
	}
}
