CREATE TABLE universe.users (
	username character varying(100) NOT NULL PRIMARY KEY,
	encoded_password character varying(255)
);

INSERT INTO universe.users(username, encoded_password) VALUES ('system', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c') ;
INSERT INTO universe.users(username, encoded_password) VALUES ('user1', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c') ;
INSERT INTO universe.users(username, encoded_password) VALUES ('user2', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c') ;

ALTER TABLE universe.customers ADD username character varying(100) NOT NULL DEFAULT 'system';
ALTER TABLE universe.customers ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (username) REFERENCES universe.users;