create schema betidb_resume;

create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on secbones.* to 'springuser'@'localhost';

use betidb_resume;
describe user;


select * from user;
select * from role;
select * from user_roles;

INSERT INTO user (email,enabled,first_name,last_name, password, username )
VALUES ("beti@gmail.com",1,"beti","beti" ,"password", "beti");
INSERT INTO user (email,enabled,first_name,last_name, password, username )
VALUES ("bini@gmail.com",1,"bini","bini" ,"password", "bini");

INSERT INTO role (role)
VALUES ("ADMIN");
INSERT INTO role (role)
VALUES ("USER");

INSERT INTO user_roles (user_id,role_id)
VALUES (2,2);
INSERT INTO user_roles (user_id,role_id)
VALUES (1,1);


DELETE FROM user
WHERE email='beti@gmail.com';
Commit;


select * from person_table;
select * from education_table;
select * from experiance_model;
select * from skills_model;