CREATE TABLE demo.users(
user_id int NOT NULL PRIMARY KEY,
first_name varchar(50),
last_name varchar(50),
username varchar(50),
status varchar(15)
);

ALTER TABLE demo.users
ADD UNIQUE (username);

INSERT INTO demo.users VALUES (1, 'Kate', 'Nelson','KateN','active');
INSERT INTO demo.users VALUES (2, 'John', 'Mathew','JohnM','active');
INSERT INTO demo.users VALUES (3, 'Cathy', 'Mill','CathyM','active');

COMMIT;