PGDMP      -            	    |            ai_assistant_db    16.3    16.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    32853    ai_assistant_db    DATABASE     �   CREATE DATABASE ai_assistant_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Chinese (Simplified)_China.936';
    DROP DATABASE ai_assistant_db;
                postgres    false            �            1259    41047 	   t_wx_user    TABLE       CREATE TABLE public.t_wx_user (
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
    DROP TABLE public.t_wx_user;
       public         heap    postgres    false            �           0    0    TABLE t_wx_user    COMMENT     8   COMMENT ON TABLE public.t_wx_user IS '用户信息表';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.openid    COMMENT     I   COMMENT ON COLUMN public.t_wx_user.openid IS '小程序用户的openid';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.nickname    COMMENT     ?   COMMENT ON COLUMN public.t_wx_user.nickname IS '用户昵称';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.avatar_url    COMMENT     A   COMMENT ON COLUMN public.t_wx_user.avatar_url IS '用户头像';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.gender    COMMENT     E   COMMENT ON COLUMN public.t_wx_user.gender IS '性别 0-男、1-女';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.country    COMMENT     >   COMMENT ON COLUMN public.t_wx_user.country IS '所在国家';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.province    COMMENT     9   COMMENT ON COLUMN public.t_wx_user.province IS '省份';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.city    COMMENT     5   COMMENT ON COLUMN public.t_wx_user.city IS '城市';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.language    COMMENT     9   COMMENT ON COLUMN public.t_wx_user.language IS '语种';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.create_time    COMMENT     I   COMMENT ON COLUMN public.t_wx_user.create_time IS '创建/注册时间';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.update_time    COMMENT     B   COMMENT ON COLUMN public.t_wx_user.update_time IS '更新时间';
          public          postgres    false    217            �           0    0    COLUMN t_wx_user.mobile    COMMENT     =   COMMENT ON COLUMN public.t_wx_user.mobile IS '手机号码';
          public          postgres    false    217            �            1259    41046    lev_wx_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.lev_wx_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.lev_wx_user_id_seq;
       public          postgres    false    217                        0    0    lev_wx_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.lev_wx_user_id_seq OWNED BY public.t_wx_user.id;
          public          postgres    false    216            �            1259    32867    t_user    TABLE     �   CREATE TABLE public.t_user (
    user_id bigint NOT NULL,
    phone text NOT NULL,
    password text NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.t_user;
       public         heap    postgres    false                       0    0    COLUMN t_user.user_id    COMMENT     7   COMMENT ON COLUMN public.t_user.user_id IS '用户ID';
          public          postgres    false    215                       0    0    COLUMN t_user.phone    COMMENT     6   COMMENT ON COLUMN public.t_user.phone IS '手机号';
          public          postgres    false    215                       0    0    COLUMN t_user.password    COMMENT     6   COMMENT ON COLUMN public.t_user.password IS '密码';
          public          postgres    false    215                       0    0    COLUMN t_user.create_time    COMMENT     ?   COMMENT ON COLUMN public.t_user.create_time IS '创建时间';
          public          postgres    false    215                       0    0    COLUMN t_user.update_time    COMMENT     ?   COMMENT ON COLUMN public.t_user.update_time IS '修改时间';
          public          postgres    false    215            V           2604    49238    t_wx_user id    DEFAULT     n   ALTER TABLE ONLY public.t_wx_user ALTER COLUMN id SET DEFAULT nextval('public.lev_wx_user_id_seq'::regclass);
 ;   ALTER TABLE public.t_wx_user ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �          0    32867    t_user 
   TABLE DATA           T   COPY public.t_user (user_id, phone, password, create_time, update_time) FROM stdin;
    public          postgres    false    215   W       �          0    41047 	   t_wx_user 
   TABLE DATA           �   COPY public.t_wx_user (id, openid, nickname, avatar_url, gender, country, province, city, language, create_time, update_time, mobile) FROM stdin;
    public          postgres    false    217   �                  0    0    lev_wx_user_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.lev_wx_user_id_seq', 1, false);
          public          postgres    false    216            [           2606    49240    t_wx_user lev_wx_user_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.t_wx_user
    ADD CONSTRAINT lev_wx_user_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.t_wx_user DROP CONSTRAINT lev_wx_user_pkey;
       public            postgres    false    217            Y           2606    32875    t_user t_user_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (user_id);
 <   ALTER TABLE ONLY public.t_user DROP CONSTRAINT t_user_pkey;
       public            postgres    false    215            �   1   x�34000�442c##]C]C+S+c=#C#\�\1z\\\ ���      �   d  x�]��N�PFח��h���{;�X)a�*��X
�K���{ݠ�]]h$F}���11�|��|9��F(�@d��"��~�ps��O3�c�l3Z��(���Jk�&i� @UBUJ8E�k����X�@��K
V^�QaD�����.o�F������=�uy�~~��_��'d�a0Vy>��GV4�F���ǻ�t<�� b~����֙h݆���B9v\M�u
�%�U�2�� 4rU�9l+a;�If!p��;��`�V�b��[�i�F߬myE�f�~�	庩��B@v�#�-n����H�>.N������,Y��wZ��T�8��0� �Hx��ťR�o���y     