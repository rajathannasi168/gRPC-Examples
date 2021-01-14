package com.vinsguru.client;

import com.vinsguru.models.Balance;
import com.vinsguru.models.BalanceCheckRequest;
import com.vinsguru.models.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {
private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6561).usePlaintext().build();
        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);

    }

    @Test
    public void balanceTest(){
        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder().setAccountNumber(10).build();

        Balance balance=this.blockingStub.getBalance(balanceCheckRequest);
        System.out.println("Received ..."+balance.getAmount());
    }

}
;