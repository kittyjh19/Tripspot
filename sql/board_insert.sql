USE travel_db;

INSERT INTO board (title,content,member_id) VALUES ("title1","content1","hyomin");
INSERT INTO board (title,content,member_id) VALUES ("title2","content2","hyomin");
INSERT INTO board (title,content,member_id) VALUES ("title3","content3","hyomin");
INSERT INTO board (title,content,member_id) VALUES ("title4","content4","hyomin");
INSERT INTO board (title,content,member_id) VALUES ("title5","content5","hyomin");
COMMIT;

INSERT INTO board (title,content,member_id) VALUES ("title1","content1","user");
INSERT INTO board (title,content,member_id) VALUES ("title2","content2","user");
INSERT INTO board (title,content,member_id) VALUES ("title3","content3","user");
INSERT INTO board (title,content,member_id) VALUES ("title4","content4","user");
INSERT INTO board (title,content,member_id) VALUES ("title5","content5","user");
COMMIT;