package beertech.becks.api.model;

import beertech.becks.api.controller.TransactionController;
import beertech.becks.api.repositories.CurrentAccountRepository;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.CurrentAccountService;
import beertech.becks.api.service.TransactionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public abstract class AbstractTests {

    public TransactionService transactionService;

    @Mock
    public TransactionRepository transactionRepositoryMock;

    @Mock
    public CurrentAccountRepository currentAccountRepository;

    @Mock
    public CurrentAccountService currentAccountServiceMock;

    @InjectMocks
    public TransactionController transactionController;

    public MockMvc mockMvc;

}
