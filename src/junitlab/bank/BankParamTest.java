package junitlab.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junitlab.bank.impl.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class BankParamTest {
    long amount;
    long rounded;

    public BankParamTest(long amount, long rounded) {
        this.amount = amount;
        this.rounded = rounded;
    }
    @Test
    public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {
        GreatSavingsBank bank = new GreatSavingsBank();
        String acc = bank.openAccount();
        bank.deposit(acc, 10000);
        bank.withdraw(acc, amount);
        Assert.assertEquals(10000-rounded, bank.getBalance(acc));
    }

    @Parameterized.Parameters
    public static List<Object[]> parameters() {
        List<Object[]> params = new ArrayList<Object[]>();
        params.add(new Object[] {1100, 1100});
        params.add(new Object[] {1101, 1100});
        params.add(new Object[] {1149, 1100});
        params.add(new Object[] {1150, 1200});
        params.add(new Object[] {1151, 1200});
        params.add(new Object[] {1199, 1200});
        params.add(new Object[] {1200, 1200});
        return params;
    }
}
