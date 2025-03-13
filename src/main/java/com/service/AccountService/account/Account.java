package com.service.AccountService.account;

import java.util.Date;
import java.util.UUID;

public class Account extends AccountBase {
	public static String createNewAccountId() {
		Date now = new Date();
		UUID uuid = UUID.randomUUID();
		long nowInMs = now.getTime();
		String nowAsString = String.valueOf(nowInMs);
		String UUIDAsString = String.valueOf(nowInMs);
		String accountId = "K" + nowAsString + UUIDAsString;
		return accountId;
	}
}


