CREATE TABLE tbl_foto_news (
    news_id_news int NOT NULL,
    name_file VARCHAR(160) NOT NULL, 
    description VARCHAR(160), 
    content_type VARCHAR(85) NOT NULL, 
    size int NOT NULL,
    PRIMARY KEY (news_id_news),
    FOREIGN KEY (news_id_news) REFERENCES tbl_news(id_news) 
);