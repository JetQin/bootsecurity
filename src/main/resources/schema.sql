drop table customer;
drop table permission_role;
drop table account_role;
drop table account;
drop table role;
drop table permission;

create table customer(ID int primary key auto_increment not null,FIRST_NAME VARCHAR(255),LAST_NAME VARCHAR(255));

create table ACCOUNT			(ACCOUNT_ID    varchar2(250) primary key not null,ACCOUNT_NAME    varchar2(250),DESCRIPTION varchar2(250));
create table ROLE				(ROLE_ID       varchar2(250) primary key not null,ROLE_NAME       varchar2(250),DESCRIPTION varchar2(250));
create table PERMISSION			(PERMISSION_ID varchar2(250) primary key not null,PERMISSION_NAME varchar2(250),DESCRIPTION varchar2(250));
create table ACCOUNT_ROLE		(ACCOUNT_ID    varchar2(250) not null,ROLE_ID varchar2(250) not null, 
			foreign key (ACCOUNT_ID) references ACCOUNT(ACCOUNT_ID),
			foreign key (ROLE_ID) references ROLE(ROLE_ID),
			primary key (ACCOUNT_ID,ROLE_ID)
			);
create table PERMISSION_ROLE	(PERMISSION_ID varchar2(250) not null,ROLE_ID varchar2(250) not null,
			foreign key (PERMISSION_ID) references PERMISSION(PERMISSION_ID),
			foreign key (ROLE_ID) references ROLE(ROLE_ID),
			primary key (PERMISSION_ID,ROLE_ID)
);
