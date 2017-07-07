create schema secbones;

create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on secbones.* to 'springuser'@'localhost';

use secbones;

select * from user;
select * from role;
select * from user_roles;

INSERT INTO user (enabled, password, username)
VALUES (1, "123456", "bini");
INSERT INTO user (enabled, password, username)
VALUES (1, "123456", "beti");

INSERT INTO role (role)
VALUES ("jobSeeker");
INSERT INTO role (role)
VALUES ("recruiters");

INSERT INTO user_roles (user_id,role_id)
VALUES (2,2);
INSERT INTO user_roles (user_id,role_id)
VALUES (1,1);


DELETE FROM person_table
WHERE email='negatu@gmail.com';
Commit;


select * from person_table;
select * from education_table;
select * from experiance_model;
select * from skills_model;