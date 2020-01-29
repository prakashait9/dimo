use movie;


CREATE TABLE person (
    id INT,
    name VARCHAR(256),
    gender CHAR(1),
    PRIMARY KEY (id)
);

CREATE TABLE user (
    uid INT,
    first_name varchar(50),
    last_name varchar(50),
    email_id VARCHAR(60),
    password VARCHAR(32),
    role VARCHAR(32),
    created_on DATE,
    updated_on DATE,
    last_visited DATE,
    PRIMARY KEY (uid)
);





CREATE TABLE movies (
    id INT NOT NULL,
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
    PRIMARY KEY (id)
);



CREATE TABLE department (
    id INT,
    name VARCHAR(256),
    credit_id VARCHAR(64),
    PRIMARY KEY (id)
);




CREATE TABLE person_roles (
    person_id INT,
    movie_id INT,
    role_name VARCHAR(256),
    dept_id INT,
    CONSTRAINT fk_person FOREIGN KEY (person_id)
        REFERENCES person (id),
    CONSTRAINT fk_movies FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_department FOREIGN KEY (dept_id)
        REFERENCES department (id)
);


CREATE TABLE movie_cast (
    cast_id INT,
    character_name VARCHAR(256),
    person_id INT,
    movie_id INT,
    credit_id VARCHAR(64),
    CONSTRAINT fk_person_1 FOREIGN KEY (person_id)
        REFERENCES person (id),
    CONSTRAINT fk_movies_1 FOREIGN KEY (movie_id)
        REFERENCES movies (id)
);


CREATE TABLE genre (
    id INT,
    genre_name VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE movie_genre (
    movie_id INT,
    genre_id INT,
    CONSTRAINT fk_movies_2 FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_genre FOREIGN KEY (genre_id)
        REFERENCES genre (id)
);


CREATE TABLE prod_country (
    id INT,
    country_name VARCHAR(64),
    PRIMARY KEY (id)
);


CREATE TABLE movie_prod_country (
    prod_country_id INT,
    movie_id INT,
    CONSTRAINT fk_movies_3 FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_prod_country FOREIGN KEY (prod_country_id)
        REFERENCES prod_country (id)
);


CREATE TABLE spoken_lang (
    id INT,
    language VARCHAR(64),
    PRIMARY KEY (id)
);




CREATE TABLE movie_spoken_lang (
    spoken_lang_id INT,
    movie_id INT,
    CONSTRAINT fk_movies_4 FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_spoken_lang FOREIGN KEY (spoken_lang_id)
        REFERENCES spoken_lang (id)
);


CREATE TABLE keywords (
    id INT NOT NULL,
    keyword_name VARCHAR(128) NULL,
    PRIMARY KEY (id)
);


CREATE TABLE movie_keywords (
    key_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT fk_movies_5 FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_keywords FOREIGN KEY (key_id)
        REFERENCES keywords (id)
);


CREATE TABLE prod_company (
    id INT NOT NULL,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE movie_prod_company (
    prod_company_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT fk_movies_6 FOREIGN KEY (movie_id)
        REFERENCES movies (id),
    CONSTRAINT fk_prod_company FOREIGN KEY (prod_company_id)
        REFERENCES prod_company (id)
);



create table preferences(
	user_id int,
    language_id int,
	production_id int,
	genre_id int,

     CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES person (id),
    CONSTRAINT fk_spoken_lang_1 FOREIGN KEY (language_id)
        REFERENCES spoken_lang (id),
    CONSTRAINT fk_production_1 FOREIGN KEY (production_id)
        REFERENCES prod_company (id),
         CONSTRAINT fk_genre_2 FOREIGN KEY (genre_id)
        REFERENCES genre (id)


);







