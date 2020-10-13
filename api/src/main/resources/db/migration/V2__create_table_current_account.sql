create table if not exists CURRENT_ACCOUNT
(
    account_id      numeric not null PRIMARY KEY,
    hash_value      varchar(60) UNIQUE,
    account_balance numeric null
);
