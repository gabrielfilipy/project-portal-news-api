CREATE TABLE tbl_category (
    id_category SERIAL,
    title VARCHAR(60) NOT NULL,
    description VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    active boolean, 
    PRIMARY KEY (id_category)
);