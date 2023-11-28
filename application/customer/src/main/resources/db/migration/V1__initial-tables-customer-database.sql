CREATE TABLE TB_PERSON (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP
);

CREATE TABLE TB_COUNTRY (
    id SERIAL PRIMARY KEY,
    acronym VARCHAR(2) NOT NULL UNIQUE,
    country VARCHAR(50) NOT NULL UNIQUE,
    currency VARCHAR(3) NOT NULL UNIQUE,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP
);

CREATE TABLE TB_DOCUMENT_TYPE (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    id_country INT NOT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP,
    FOREIGN KEY (id_country) REFERENCES TB_COUNTRY(id)
);

CREATE TABLE TB_CUSTOMER (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(50) NOT NULL UNIQUE,
    id_person INT NOT NULL UNIQUE,
    email VARCHAR(100),
    phone VARCHAR(50) NOT NULL,
    date_birth DATE NOT NULL,
    affiliation_father INT,
    affiliation_mother INT,
    marital_status VARCHAR(10),
    nationality INT NOT NULL,
    profession VARCHAR(50),
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP,
    FOREIGN KEY (id_person) REFERENCES tb_person(id),
    FOREIGN KEY (affiliation_father) REFERENCES tb_person(id),
    FOREIGN KEY (affiliation_mother) REFERENCES tb_person(id),
    FOREIGN KEY (nationality) REFERENCES tb_country(id)
);

CREATE TABLE TB_DOCUMENT_CUSTOMER (
    id SERIAL PRIMARY KEY,
    id_customer INT NOT NULL,
    id_document_type INT NOT NULL,
    number VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP,
    FOREIGN KEY (id_customer) REFERENCES TB_CUSTOMER(id),
    FOREIGN KEY (id_document_type) REFERENCES TB_DOCUMENT_TYPE(id)
);

CREATE TABLE TB_ADDRESS (
    id SERIAL PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    number INT NOT NULL,
    complement VARCHAR(50),
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    id_country INT NOT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP,
    FOREIGN KEY (id_country) REFERENCES TB_COUNTRY(id)
);

CREATE TABLE TB_ADDRESS_CUSTOMER (
    id SERIAL PRIMARY KEY,
    id_customer INT NOT NULL UNIQUE,
    id_address INT NOT NULL,
    created_date TIMESTAMP DEFAULT NOW(),
    updated_date TIMESTAMP,
    FOREIGN KEY (id_customer) REFERENCES TB_CUSTOMER(id),
    FOREIGN KEY (id_address) REFERENCES TB_ADDRESS(id)
);