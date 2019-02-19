package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Justin Isaac", "strugglingcollegestudent@gmail.com", 27
        )));

        accounts.put(1001, new PremiumAccount(new AccountData(
                1001, "Leona Winterbottom", "LDub@corporateindustries.com", 7184666
        )));

        accounts.put(1002, new BasicAccount(new AccountData(
                1002, "Karen Grumby", "winetime@yahoo.com", 4200
        )));

        accounts.put(1003, new BasicAccount(new AccountData(
                1003, "James Maverick", "jim@maverickhomeimprovement.com", 8227
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Swiper No Swiping.");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Whoa there, buddy! You can't withdraw " + amount + " when you have " + account.getBalance() + " in your account.");
        }
    }
}
