CREATE TABLE tbl_news (
    id_news SERIAL,
    title VARCHAR(255) NOT NULL, 
    content text NOT NULL, 
    photo_news VARCHAR(255) NOT NULL, 
    date date NOT NULL, 
    order_id int NOT NULL, 
    code_category int NOT NULL,
    code_user int NOT NULL,
    active boolean, 
    PRIMARY KEY (id_news),
    FOREIGN KEY (code_category) REFERENCES tbl_category(id_category),
	FOREIGN KEY (code_user) REFERENCES tbl_user(id_user)
);