--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

-- Started on 2018-03-16 00:42:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


-- CREATE ROLE pol_v4 SUPERUSER CREATEDB NOCREATEROLE NOINHERIT LOGIN PASSWORD '12345678' ;
-- CREATE DATABASE pol_v4 WITH owner=pol_v5 encoding='UTF-8' TEMPLATE template0;

CREATE TABLE pps.referencia_pago (
	referencia_pago_id int4 NOT NULL,
	usuario_id int4 NULL,
	cuenta_id int4 NULL,
	codigo_referencia_pago varchar(100) NOT NULL,
	tipo_referencia_pago varchar(50) NOT NULL,
	descripcion varchar(255) NOT NULL,
	estado varchar(50) NOT NULL,
	pagador_nombre varchar(255) NULL,
	pagador_email varchar(255) NULL,
	solicitud_tarjeta_id varchar(36) NULL,
	fecha_creacion timestamptz NOT NULL,
	fecha_vencimiento timestamptz NULL,
	multipago bool NOT NULL,
	lease int8 NOT NULL,
	unidad_negocio varchar(30) NOT NULL,
	tipo_creacion varchar(50) NOT NULL DEFAULT 'EMAIL'::character varying,
	frecuencia_recordatorio varchar(30) NULL,
	fecha_proximo_recordatorio timestamptz NULL,
	referencia varchar(255) NULL,
	migrado bool NULL,
	inventario_tarjeta_cobranza_id varchar(36) NULL,
	fecha_asociacion timestamptz NOT NULL,
	CONSTRAINT referencia_pago_pkey PRIMARY KEY (referencia_pago_id)
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE pps.referencia_pago OWNER TO pol_v4;


CREATE TABLE pps.referencia_pago_parametro_extra (
	referencia_pago_parametro_extra_id int4 NOT NULL,
	tipo varchar(64) NOT NULL,
	valor varchar(255) NOT NULL,
	nombre varchar(64) NOT NULL,
	CONSTRAINT referencia_pago_parametro_extra_pkey PRIMARY KEY (referencia_pago_parametro_extra_id,nombre),
	CONSTRAINT referencia_pago_parametro_extra_fkey FOREIGN KEY (referencia_pago_parametro_extra_id) REFERENCES pps.referencia_pago(referencia_pago_id)
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE pps.referencia_pago_parametro_extra OWNER TO pol_v4;


CREATE TABLE pps.referencia_pago_valor_adicional (
	referencia_pago_valor_adicional_id int4 NOT NULL,
	moneda_iso_4217 varchar(3) NOT NULL,
	valor numeric(19,2) NOT NULL,
	nombre varchar(64) NOT NULL,
	CONSTRAINT referencia_pago_valor_adicional_pkey PRIMARY KEY (referencia_pago_valor_adicional_id,nombre),
	CONSTRAINT referencia_pago_valor_adicional_fkey FOREIGN KEY (referencia_pago_valor_adicional_id) REFERENCES pps.referencia_pago(referencia_pago_id)
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE pps.referencia_pago_valor_adicional OWNER TO pol_v4;