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


CREATE DATABASE sampledb WITH owner=postgres encoding='UTF-8' TEMPLATE template0;


--
-- TOC entry 185 (class 1259 OID 24663)
-- Name: person_example; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE person_example (
    first_name text,
    last_name text,
    birth_date date
);


ALTER TABLE person_example OWNER TO postgres;

--
-- TOC entry 2117 (class 0 OID 24663)
-- Dependencies: 185
-- Data for Name: person_example; Type: TABLE DATA; Schema: public; Owner: postgres
--



-- Completed on 2018-03-16 00:42:42

--
-- PostgreSQL database dump complete
--