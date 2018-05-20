DROP TABLE IF EXISTS universe.users;
CREATE TABLE universe.users (
	username character varying(100) NOT NULL PRIMARY KEY,
	encoded_password character varying(255),
	system_user_flag boolean NOT NULL DEFAULT false,
	admin_user_flag boolean NOT NULL DEFAULT false
);

INSERT INTO universe.users(username, encoded_password, system_user_flag, admin_user_flag) VALUES ('system', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c', true, false) ;
INSERT INTO universe.users(username, encoded_password, system_user_flag, admin_user_flag) VALUES ('admin1', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c', false, true) ;
INSERT INTO universe.users(username, encoded_password, system_user_flag, admin_user_flag) VALUES ('user1', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c', false, false) ;
INSERT INTO universe.users(username, encoded_password, system_user_flag, admin_user_flag) VALUES ('user2', 'f4a80198c110f29506d4d799963f22a3d1630eb2f5dd58791022a505c847315a62f6d20e8bdbcb0c', false, false) ;

ALTER TABLE universe.customers ADD username character varying(100) NOT NULL DEFAULT 'system';
ALTER TABLE universe.customers ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (username) REFERENCES universe.users;