-- public.anggota definition

-- Drop table

-- DROP TABLE public.anggota;

CREATE TABLE public.anggota (
	id bigserial NOT NULL,
	alamat varchar(200) NULL,
	created_at date NULL,
	email varchar(50) NULL,
	nama_anggota varchar(50) NULL,
	nik varchar(17) NULL,
	"password" varchar(250) NULL,
	status int4 NULL,
	tanggal_lahir date NULL,
	tempat_lahir varchar(17) NULL,
	user_name varchar(50) NULL,
	CONSTRAINT anggota_pkey PRIMARY KEY (id),
	CONSTRAINT uk_12qpvnvsm0ino08lux1shkff4 UNIQUE (nik)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id serial4 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	email varchar(50) NULL,
	"password" varchar(120) NULL,
	username varchar(20) NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.transaksi definition

-- Drop table

-- DROP TABLE public.transaksi;

CREATE TABLE public.transaksi (
	id bigserial NOT NULL,
	"action" varchar(10) NULL,
	amount int4 NULL,
	created_at timestamp NULL,
	no_transaksi varchar(20) NULL,
	nik_anggota varchar(17) NULL,
	CONSTRAINT transaksi_pkey PRIMARY KEY (id),
	CONSTRAINT fk56bhvtohs4c4jynj1fphccrrj FOREIGN KEY (nik_anggota) REFERENCES public.anggota(nik)
);


-- public.user_roles definition

-- Drop table

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles (
	user_id serial4 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkexop6fptfi7geugglonaeg0bu FOREIGN KEY (user_id) REFERENCES public.anggota(id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id serial4 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);