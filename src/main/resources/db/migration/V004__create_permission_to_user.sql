CREATE TABLE user_permission (
	code_user int NOT NULL,
	code_permission int NOT NULL,
	PRIMARY KEY (code_user, code_permission),
	FOREIGN KEY (code_user) REFERENCES tbl_user(id_user),
	FOREIGN KEY (code_permission) REFERENCES tbl_permission(id_permission)
);