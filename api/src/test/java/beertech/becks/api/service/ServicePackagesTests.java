package beertech.becks.api.service;

import beertech.becks.api.service.transaction.TransactionServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TransactionServiceTest.AllTests.class
})
public class ServicePackagesTests {
}
