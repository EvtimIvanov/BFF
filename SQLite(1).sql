/*Create DATABASE BankAccounts;

USE BankAccounts;*/


CREATE TABLE users(
	id int AUTO_INCREMENT not NULL,
	username varchar(255),
  	email varchar(255),
  	phone_number varchar(15),
  	PRIMARY KEY(id)
  
);

CREATE TABLE accounts(
	id int AUTO_INCREMENT not NULL,
  	account_balance double,
  	user_id int not NULL,  
    PRIMARY KEY(id),
  	FOREIGN KEY(user_id) REFERENCES users(id)

  
);



Insert  into users(id,username,email,phone_number) Values(1,"Ivan","ivan@abv.bg","0896111111");
Insert  into users(id,username,email,phone_number) Values(2,"Milen","milen@abv.bg","0896145111");
Insert  into users(id,username,email,phone_number) Values(3,"Andon","andon@abv.bg","0896111781");
Insert  into users(id,username,email,phone_number) Values(4,"Kostadin","kosta@abv.bg","0896110011");


Insert  into accounts(id,account_balance,user_id) Values(1,2000,1);
Insert  into accounts(id,account_balance,user_id) Values(2,3000,1);
Insert  into accounts(id,account_balance,user_id) Values(4,4500,3);



Select users.id as user_id,accounts.id as account_id, account_balance
From accounts
Left JOIN users
On users.id = accounts.user_id

Insert INTO users(id,username,email,phone_number) Select 5,username,email,phone_number From users Where username  LIKE "Ivan"

/*
Select * from accounts
*/


