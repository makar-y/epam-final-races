/**
 * Epam ET Final Project
 * Horseraces
 * March 2017
 * Group #10
 * Instructor Ihar Blinou
 * Student Makary Malinouski
 */
package by.malinouski.horserace.command.receiver;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import by.malinouski.horserace.constant.PathConsts;
import by.malinouski.horserace.constant.RequestMapKeys;
import by.malinouski.horserace.dao.UserDao;
import by.malinouski.horserace.exception.DaoException;
import by.malinouski.horserace.logic.entity.Entity;
import by.malinouski.horserace.logic.entity.User;

/**
 * @author makarymalinouski
 *
 */
public class DeleteAccountReceiver extends CommandReceiver {

	/**
	 * @param requestMap
	 */
	public DeleteAccountReceiver(Map<String, Object> requestMap) {
		super(requestMap);
	}

	/* (non-Javadoc)
	 * @see by.malinouski.horserace.command.receiver.CommandReceiver#act()
	 */
	@Override
	public Optional<? extends Entity> act() {
		User user = (User) requestMap.get(RequestMapKeys.USER);
		String[] passArr = (String[]) requestMap.get(RequestMapKeys.PASSWORD);
		String password = passArr[0];
		
		UserDao dao = new UserDao();
		try {
			if (dao.findUser(user.getLogin(), password)) {
				dao.deleteUser(user);
				logger.debug("deleting user");
				requestMap.put(RequestMapKeys.RESULT, 
						"User deleted. You will not be able to login again. (need to localize)");
				requestMap.put(RequestMapKeys.REDIRECT_PATH, PathConsts.LOGOUT);
			} else {
				logger.debug("not deleting user");
				requestMap.put(RequestMapKeys.RESULT, "Coulnd't delete (need to localize this)");
				requestMap.put(RequestMapKeys.REDIRECT_PATH, PathConsts.HOME);

			}
		} catch (DaoException e) {
			logger.error("Exception while deleting user " + e.getMessage());
			requestMap.put(RequestMapKeys.REDIRECT_PATH, PathConsts.HOME);
		} 
		
		return Optional.empty();
	}

}
