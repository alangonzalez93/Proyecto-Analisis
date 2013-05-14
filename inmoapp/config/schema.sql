CREATE TABLE localities (
id smallint(100) auto_increment UNIQUE NOT NULL,
name varchar(50) NOT NULL,
CONSTRAINT pk_id PRIMARY KEY (id)
);

CREATE TABLE real_estates (
    id smallint(100) auto_increment UNIQUE NOT NULL,
    web varchar(50),
    rs_mail varchar(50),
    rs_name varchar(50),
    rs_neighborhood varchar(50),
    rs_street varchar(50),
    tel int,
    locality_id smallint(100),
	CONSTRAINT pk_re_id PRIMARY KEY (id)
);


CREATE TABLE owners (
    id smallint(100) auto_increment UNIQUE NOT NULL,
    owner_name varchar(50) NOT NULL,
    owner_neighborhood varchar(50),
    owner_street varchar(50),
    owner_mail varchar(50),
    locality_id smallint(100),
	CONSTRAINT pk_ow_id PRIMARY KEY (id)
);


CREATE TABLE buildings (
    id smallint(100) UNIQUE NOT NULL auto_increment,
    price float,
    type int NOT NULL,
    neighborhood varchar(50),
    description varchar(100) NOT NULL,
    b_street varchar(50),
    categorie int NOT NULL,
    locality_id smallint(100),
	owner_id smallint(100),
	CONSTRAINT pk_b_id PRIMARY KEY (id),
    CONSTRAINT type_pos CHECK (type > 0 AND type < 5),
    CONSTRAINT check_cat CHECK (categorie > 0 AND categorie < 3)
);


CREATE TABLE owners_real_estates (
	id smallint(100) auto_increment UNIQUE NOT NULL,
    real_estate_id smallint(100),
    owner_id smallint(100),
	CONSTRAINT pk_rid_oid PRIMARY KEY (id)
);


