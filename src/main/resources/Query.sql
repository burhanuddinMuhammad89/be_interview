-- public.anggota definition

-- Drop table

-- DROP TABLE public.anggota;

CREATE TABLE public.anggota (
	id bigserial NOT NULL,
	alamat varchar(200) NULL,
	created_at date NULL,
	nama_anggota varchar(50) NULL,
	nik varchar(17) NULL,
	status int4 NULL,
	tanggal_lahir date NULL,
	tempat_lahir varchar(17) NULL,
	CONSTRAINT anggota_pkey PRIMARY KEY (id),
	CONSTRAINT uk_12qpvnvsm0ino08lux1shkff4 UNIQUE (nik)
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