DROP TABLE IF EXISTS localities;
DROP TABLE IF EXISTS real_estates;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS buildings;
DROP TABLE IF EXISTS owners_real_estates;

CREATE TABLE localities (
id int auto_increment UNIQUE NOT NULL,
name varchar(50) NOT NULL,
CONSTRAINT pk_id PRIMARY KEY (id)
);

CREATE TABLE real_estates (
    id int auto_increment UNIQUE NOT NULL,
    web varchar(50),
    rs_mail varchar(50),
    rs_name varchar(50),
    rs_neighborhood varchar(50),
    rs_street varchar(50),
    tel varchar(50),
    locality_id int,
	CONSTRAINT pk_re_id PRIMARY KEY (id)
);


CREATE TABLE owners (
    id int auto_increment UNIQUE NOT NULL,
    owner_name varchar(50) NOT NULL,
    owner_dni varchar(10),
    owner_neighborhood varchar(50),
    owner_street varchar(50),
    owner_mail varchar(50),
    locality_id int,
	CONSTRAINT pk_ow_id PRIMARY KEY (id)
);


CREATE TABLE buildings (
    id int UNIQUE NOT NULL auto_increment,
    price int,
    type varchar(50) NOT NULL,
    neighborhood varchar(50),
    description varchar(100) NOT NULL,
    b_street varchar(50),
    category varchar(50) NOT NULL,
    locality_id int,
	owner_id int,
	CONSTRAINT pk_b_id PRIMARY KEY (id),
    CONSTRAINT type_pos CHECK (type > 0 AND type < 5),
    CONSTRAINT check_cat CHECK (categorie > 0 AND categorie < 3)
);


CREATE TABLE owners_real_estates (
	id int auto_increment UNIQUE NOT NULL,
    real_estate_id int,
    owner_id int,
	CONSTRAINT pk_rid_oid PRIMARY KEY (id)
);


