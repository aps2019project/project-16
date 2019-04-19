package contracts;

import java.util.List;

public interface AccountContract {
	interface View {
		void setController(Controller controller);

		void showAccountCreationError(String message);
		void showLoginError(String message);
		void showLeaderboard(Leaderboard leaderboard);
	}

	interface Controller {
		void setView(View view);

		void loadLeaderboard();
		void createAccount(String username, String password);
		void loginAccount(String username, String password);
	}
}
