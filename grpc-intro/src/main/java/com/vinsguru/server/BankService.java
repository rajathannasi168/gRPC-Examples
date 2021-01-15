package com.vinsguru.server;

import com.vinsguru.models.*;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        Balance balance = Balance.newBuilder().setAmount(AccountDatabase.getBalance(accountNumber)).build();
        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithDrawRequest request, StreamObserver<Money> responseObserver) {
        int accountnumber= request.getAccountNumber();
        int amount = request.getAmount();
        int balance = AccountDatabase.getBalance(accountnumber);

        for (int i = 0; i < amount/10 ; i++) {
            Money money = Money.newBuilder().setValue(10).build();
            responseObserver.onNext(money);
            AccountDatabase.detectBalance(accountnumber,10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}
