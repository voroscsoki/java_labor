package junitlab.bank;
import junitlab.bank.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTestFixture {
    private GreatSavingsBank bank;
    private String acc1;
    private String acc2;

    @Before
    public void init() throws AccountNotExistsException {
        bank = new GreatSavingsBank();
        acc1 = bank.openAccount();
        acc2 = bank.openAccount();
        bank.deposit(acc1, 1500);
        bank.deposit(acc2, 12000);
    }
    @Test
    public void testTransfer() throws NotEnoughFundsException, AccountNotExistsException {
        bank.transfer(acc2, acc1, 3456);
        Assert.assertEquals(4956, bank.getBalance(acc1));
        Assert.assertEquals(8544, bank.getBalance(acc2));
    }
    @Test
    public void testTransferWithoutFunds() throws AccountNotExistsException {
        boolean exceptionHappened = false;
        try {
            bank.transfer(acc1, acc2, 3456);
        } catch (NotEnoughFundsException e) {
            exceptionHappened = true;
        }
        Assert.assertTrue(exceptionHappened);
    }

}
