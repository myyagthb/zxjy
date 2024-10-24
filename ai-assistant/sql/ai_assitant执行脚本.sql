--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-10-17 11:15:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 41047)
-- Name: t_wx_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_wx_user (
    id bigint NOT NULL,
    openid character varying(28),
    nickname character varying(100),
    avatar_url character varying(2000),
    gender smallint,
    country character varying(100),
    province character varying(100),
    city character varying(100),
    language character varying(100),
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    mobile character varying(50),
    CONSTRAINT lev_wx_user_gender_check CHECK ((gender = ANY (ARRAY[0, 1])))
);


ALTER TABLE public.t_wx_user OWNER TO postgres;

--
-- TOC entry 4846 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE t_wx_user; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.t_wx_user IS '用户信息表';


--
-- TOC entry 4847 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.openid; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.openid IS '小程序用户的openid';


--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.nickname; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.nickname IS '用户昵称';


--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.avatar_url; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.avatar_url IS '用户头像';


--
-- TOC entry 4850 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.gender; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.gender IS '性别 0-男、1-女';


--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.country; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.country IS '所在国家';


--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.province; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.province IS '省份';


--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.city; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.city IS '城市';


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.language; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.language IS '语种';


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.create_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.create_time IS '创建/注册时间';


--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.update_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.update_time IS '更新时间';


--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN t_wx_user.mobile; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_wx_user.mobile IS '手机号码';


--
-- TOC entry 216 (class 1259 OID 41046)
-- Name: lev_wx_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lev_wx_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.lev_wx_user_id_seq OWNER TO postgres;

--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 216
-- Name: lev_wx_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lev_wx_user_id_seq OWNED BY public.t_wx_user.id;


--
-- TOC entry 215 (class 1259 OID 32867)
-- Name: t_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_user (
    user_id bigint NOT NULL,
    phone text NOT NULL,
    password text NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.t_user OWNER TO postgres;

--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN t_user.user_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_user.user_id IS '用户ID';


--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN t_user.phone; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_user.phone IS '手机号';


--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN t_user.password; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_user.password IS '密码';


--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN t_user.create_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_user.create_time IS '创建时间';


--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN t_user.update_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_user.update_time IS '修改时间';


--
-- TOC entry 4692 (class 2604 OID 49238)
-- Name: t_wx_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_wx_user ALTER COLUMN id SET DEFAULT nextval('public.lev_wx_user_id_seq'::regclass);


--
-- TOC entry 4697 (class 2606 OID 49240)
-- Name: t_wx_user lev_wx_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_wx_user
    ADD CONSTRAINT lev_wx_user_pkey PRIMARY KEY (id);


--
-- TOC entry 4695 (class 2606 OID 32875)
-- Name: t_user t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (user_id);


-- Completed on 2024-10-17 11:15:55

--
-- PostgreSQL database dump complete
--

