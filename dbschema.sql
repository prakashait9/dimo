create table user(uid INT,uname varchar(256),email_id varchar(256),password varchar(32),role varchar(32),created_on date,updated_on date,last_visited date,PRIMARY KEY(uid));
create table preferences(uid INT);
create table person(person_id INT,name varchar(256),gender char(1),PRIMARY KEY(person_id));
create table department(dept_id INT,name varchar(256),credit_id varchar(64),PRIMARY KEY(dept_id));
create table person_roles(person_id INT,movie_id INT,role_name varchar(256),dept_id INT);
create table movie_cast(cast_id INT,character_name varchar(256),person_id INT,movie_id INT,credit_id varchar(64));
create table genre(genre_id INT,genre_name varchar(256));
create table movie_genre(movie_id INT,genre_id INT);
create table prod_country(prod_country_id INT,country_name varchar(64));
create table movie_prod_country(prod_country_id INT,movie_id INT);
create table spoken_lang(spoken_lang_id INT,language varchar(64));
create table movie_spoken_lang(spoken_lang_id INT,movie_id INT);
CREATE TABLE movies (
 movie_id INT NOT NULL,
 title VARCHAR(128) NULL,
 budget INT NULL,
 homepage VARCHAR(128) NULL,
 orig_lang VARCHAR(45) NULL,
 orig_title VARCHAR(128) NULL,
 overview TEXT(2048) NULL,
 popularity FLOAT NULL,
 release_date DATE NULL,
 revenue INT NULL,
 runtime INT NULL,
 status VARCHAR(45) NULL,
 tagline VARCHAR(128) NULL,
 vote_average FLOAT NULL,
 vote_count INT NULL,
 PRIMARY KEY (movie_id));


CREATE TABLE keywords (
 key_id INT NOT NULL,
 keyword_name VARCHAR(128) NULL,
 PRIMARY KEY (key_id));
CREATE TABLE movie_keywords (
 key_id INT NOT NULL,
 movie_id INT NOT NULL);
CREATE TABLE movie_prod_company (
 prod_company_id INT NOT NULL,
 movie_id INT NOT NULL);

CREATE TABLE prod_company (
 prod_company_id INT NOT NULL,
 name VARCHAR(128) NOT NULL,
 PRIMARY KEY (prod_company_id));
