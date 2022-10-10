CREATE TABLE tbl_foto_user (
    user_id_user int NOT NULL,
    name_file VARCHAR(160) NOT NULL, 
    description VARCHAR(160), 
    content_type VARCHAR(85) NOT NULL, 
    size int NOT NULL,
    PRIMARY KEY (user_id_user),
    FOREIGN KEY (user_id_user) REFERENCES tbl_user(id_user) 
);