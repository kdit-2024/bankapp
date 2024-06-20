insert into user_tb(username, password, email, fullname) values('ssar', '1234', 'ssar@nate.com', '쌀');
insert into user_tb(username, password, email, fullname) values('cos', '1234', 'cos@nate.com', '코스');

insert into account_tb(balance, number, password, user_id) values(900, '1111', '1234', 1);
insert into account_tb(balance, number, password, user_id) values(1000, '2222', '1234', 1);
insert into account_tb(balance, number, password, user_id) values(1100, '3333', '1234', 2);

insert into history_tb(withdraw_account_id, deposit_account_id, amount, withdraw_balance, deposit_balance, created_at)
values(1, 3, 100, 900, 1100, now());
insert into history_tb(withdraw_account_id, deposit_account_id, amount, withdraw_balance, deposit_balance, created_at)
values(2, 3, 100, 900, 1200, now());
insert into history_tb(withdraw_account_id, deposit_account_id, amount, withdraw_balance, deposit_balance, created_at)
values(3, 1, 100, 1100, 1000, now());
insert into history_tb(withdraw_account_id, deposit_account_id, amount, withdraw_balance, deposit_balance, created_at)
values(1, 2, 100, 900, 1000, now());
insert into history_tb(withdraw_account_id, deposit_account_id, amount, withdraw_balance, deposit_balance, created_at)
values(1, 2, 100, 800, 1100, now());


