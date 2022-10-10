CREATE TABLE tbl_user (
    id_user SERIAL,
    name_user VARCHAR(255) NOT NULL, 
    password_user VARCHAR(10) NOT NULL, 
    about_user text NOT NULL, 
    profession_user VARCHAR(190), 
    photo_user VARCHAR(255), 
    active boolean, 
    PRIMARY KEY (id_user)
);