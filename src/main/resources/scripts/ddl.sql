DROP TABLE IF EXISTS exchange_rate;
CREATE TABLE exchange_rate(
                      id uuid DEFAULT random_uuid() PRIMARY KEY,
                      currency_origin VARCHAR(200) not null,
                      currency_destiny VARCHAR(200) NOT NULL,
                      exchange_amount DECIMAL(10,4) NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users(
                      id uuid DEFAULT random_uuid() PRIMARY KEY,
                      user_name VARCHAR(200) not null,
                      password VARCHAR(60) NOT NULL,
                      enabled VARCHAR(1),
                      rol  VARCHAR(200) not null
);


insert into exchange_rate(currency_origin,currency_destiny,exchange_amount) values('PEN','USD',4.0935);
insert into exchange_rate(currency_origin,currency_destiny,exchange_amount) values('USD','PEN',3.0567);
insert into exchange_rate(currency_origin,currency_destiny,exchange_amount) values('EUR','PEN',4.3083);
insert into exchange_rate(currency_origin,currency_destiny,exchange_amount) values('PEN','EUR',5.1057);


insert into users(user_name,password,enabled,rol) values('jpecho','$2a$10$59vDEEv3/xtH16loBP8r.uCSQKMC6elbkEKTQ0swFW0fMkKvopzOC','1','ROLE_ADMIN');
