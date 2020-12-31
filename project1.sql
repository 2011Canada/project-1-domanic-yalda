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
	"reimb_status" text,
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
					

insert into "ers_reimbursement" ("reimb_amount","reimb_submitted","reimb_resolved", "reimb_description", "reimb_receipt", "reimb_status", "reimb_type", "reimb_resolver", "reimb_author" )		
						values   ('1000', '2020-12-25 12:12:12', '2020-12-29 10:02:11', 'christmas bonus???', null, 'DENIED','other',1,4),
								 ('100', '2020-12-12 09:12:12','2020-12-19 10:02:42' , 'business trip lodging', null, 'APPROVED','lodging',1,3),
								 ('300', '2020-12-14 12:42:52', null , 'company christmas dinner', null, 'PENDING','food', null ,2);
								
								
									
select * from "ers_users"; -- allusers
select * from "ers_reimbursement" er left join "ers_users" eu on er.reimb_author = eu.ers_users_id; -- reimbursement and the user that sent it 
select * from ers_reimbursement er where reimb_author = 3; -- test find author (user that sent the claim)