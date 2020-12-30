drop schema if exists project1 cascade;
create schema project1;
set schema 'project1';




create table "ers_users"(
	"ers_users_id" serial primary key,
	"ers_username" text unique,
	"ers_password" text,
	"user_first_name" text,
	"user_last_name" text,
	"user_email" text unique,
	"user_role" text 
);


create table "ers_reimbursement"(
	"reimb_id" serial primary key,
	"reimb_amount" money,
	"reimb_submitted" timestamp,
	"reimb_resolved" timestamp,
	"reimb_description" text,
	"reimb_receipt" bytea,
	"reimb_status_id" int,
	"reimb_type" text,
	"reimb_resolver" int references "ers_users"("ers_users_id"),
	"reimb_author" int references "ers_users"("ers_users_id")
	
);			
						
					   
insert into "ers_users" ("ers_username","ers_password","user_first_name", "user_last_name","user_email", "user_role")
				values	('dy','1234','d','y','dy@gmail.com', 'manager'),
						('dy10','1234','d','y', 'dy@hotmail.com', 'employee'),
						('dy20','1234','d','y', 'dy@live.com', 'employee'),
						('dy30','1234','d','y', 'dy@yahoo.com', 'employee'),
						('dy40','1234','d','y', 'dy@protonmail.ch', 'employee'),
						('dy50','1234','d','y', 'dy@revature.com', 'employee');
					

									
select * from "ers_users";

--select * from "ers_reimbursement";
