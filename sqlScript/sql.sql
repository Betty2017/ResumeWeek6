create schema betidb_resume;

create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on betidb_resume.* to 'springuser'@'localhost';

use betidb_resume;

select * from user;
select * from role;
select * from user_roles;

INSERT INTO user (enabled, password, username)
VALUES (1, "123456", "bini");
INSERT INTO user (enabled, password, username)
VALUES (1, "123456", "beti");


INSERT INTO role (role)
VALUES ("user");
INSERT INTO role (role)
VALUES ("admin");

INSERT INTO user_roles (user_id,role_id)
VALUES (2,2);
INSERT INTO user_roles (user_id,role_id)
VALUES (1,1);

select * from person_table;
select * from education_table;
select * from experiance_model;
select * from skills_model;