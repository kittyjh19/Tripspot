DROP DATABASE IF EXISTS semi;
CREATE database semi;

use semi;

DROP TABLE IF EXISTS test;
CREATE TABLE test(
         num bigint PRIMARY KEY AUTO_INCREMENT,
         id varchar(20) UNIQUE NOT NULL
);

INSERT test VALUES (NULL, "test1");
INSERT test VALUES (NULL, "test2");
COMMIT;
