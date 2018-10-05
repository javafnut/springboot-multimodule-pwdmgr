
insert into app_profile (id,created_date,active, account_locked, last_logindtm, login, modified_date, pwd_file_name,is_deleted)
values(10001,sysdate(),true,true,sysdate(),'user1',sysdate(),'file.txt',false);
insert into app_profile (id,created_date,active, account_locked, last_logindtm, login, modified_date, pwd_file_name,is_deleted)
values(10002,sysdate(),true,true,sysdate(),'user2',sysdate(),'file.txt',false);
insert into app_profile (id,created_date,active, account_locked, last_logindtm, login, modified_date, pwd_file_name,is_deleted)
values(10003,sysdate(),true,true,sysdate(),'user3',sysdate(),'file.txt',false);
insert into app_profile (id,created_date,active, account_locked, last_logindtm, login, modified_date, pwd_file_name,is_deleted)
values(10004,sysdate(),true,true,sysdate(),'user4',sysdate(),'file.txt',false);
insert into app_profile (id,created_date,active, account_locked, last_logindtm, login, modified_date, pwd_file_name,is_deleted)
values(10005,sysdate(),true,true,sysdate(),'user5',sysdate(),'file.txt',false);

insert into user (id,created_date,email,first_name,last_name,modified_date,password,user_salt,app_profile_id,is_deleted)
values (10001,sysdate(),'foo@foo.com','Todd','McDee',sysdate(),'ABCD45','ABCD46',10001,false); 
insert into user (id,created_date,email,first_name,last_name,modified_date,password,user_salt,app_profile_id,is_deleted)
values (10002,sysdate(),'foo@foo.com','Todd','McDee',sysdate(),'ABCD45','ABCD46',10002,false); 
insert into user (id,created_date,email,first_name,last_name,modified_date,password,user_salt,app_profile_id,is_deleted)
values (10003,sysdate(),'foo@foo.com','Todd','McDee',sysdate(),'ABCD45','ABCD46',10003,false); 
insert into user (id,created_date,email,first_name,last_name,modified_date,password,user_salt,app_profile_id,is_deleted)
values (10004,sysdate(),'foo@foo.com','Todd','McDee',sysdate(),'ABCD45','ABCD46',10004,false); 
insert into user (id,created_date,email,first_name,last_name,modified_date,password,user_salt,app_profile_id,is_deleted)
values (10005,sysdate(),'foo@foo.com','Todd','McDee',sysdate(),'ABCD45','ABCD46',10005,false); 

insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10001,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo.com','https://foo.com',10001);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10002,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo2.com','https://foo.com',10001);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10003,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo3.com','https://foo.com',10001);

insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10004,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo.com','https://foo.com',10002);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10005,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo2.com','https://foo.com',10002);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10006,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo3.com','https://foo.com',10002);

insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10007,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo.com','https://foo.com',10003);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10008,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo2.com','https://foo.com',10003);
insert into site (id,category_name,created_date,login,modified_date,notes,site_pwd,name,siteurl,app_profile_id)
values(10009,'ROOT',sysdate(),'myLogin',sysdate(),'no notes','abcd45','foo3.com','https://foo.com',10003);
