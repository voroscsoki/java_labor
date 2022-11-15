package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import org.junit.Assert;
import org.junit.Test;

public class BankTest {

    @Test
    public void testOpenAccount() throws AccountNotExistsException {
        FirstNationalBank bank = new FirstNationalBank();
        String result = bank.openAccount();
        Assert.assertEquals(bank.getBalance(result), 0);
    }
    @Test
    public void testUniqueAccount(){
        FirstNationalBank bank = new FirstNationalBank();
        String result1 = bank.openAccount();
        String result2 = bank.openAccount();
        Assert.assertNotEquals(result1, result2);
    }
    @Test
    public void testInvalidAccount() {
        boolean exceptionHappened = false;
        FirstNationalBank bank = new FirstNationalBank();
        try {
            bank.deposit("123", 500);
        } catch (AccountNotExistsException e) {
            exceptionHappened = true;
        }
        Assert.assertTrue(exceptionHappened);
    }
    @Test
    public void testDeposit() throws AccountNotExistsException {
        FirstNationalBank bank = new FirstNationalBank();
        String account = bank.openAccount();
        bank.deposit(account, 2000);
        Assert.assertEquals(2000, bank.getBalance(account));
    }

}
