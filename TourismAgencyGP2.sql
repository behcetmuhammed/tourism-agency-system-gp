PGDMP                      |            TourismAgencyGP2    15.7    16.3 "    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    17504    TourismAgencyGP2    DATABASE     �   CREATE DATABASE "TourismAgencyGP2" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 "   DROP DATABASE "TourismAgencyGP2";
                postgres    false            �            1259    17505    hotel    TABLE     �  CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying(55) NOT NULL,
    adress character varying(100) NOT NULL,
    mail character varying(75) NOT NULL,
    phone_number character varying(11) NOT NULL,
    star text NOT NULL,
    park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    17510    hotel_pension    TABLE     �   CREATE TABLE public.hotel_pension (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type character varying(50) NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false            �            1259    17513    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    17516    otel_otel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            �            1259    17517    otel_pension_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    17518    otel_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    17519    reservation    TABLE     h  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count integer NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17524    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    17525    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying(50) NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    safe_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17528    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    17529    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17534    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224                      0    17505    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, adress, mail, phone_number, star, park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    214   �(                 0    17510    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    215   2*                 0    17513    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    216   �*                 0    17519    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    220   �+                  0    17525    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, safe_box, projection) FROM stdin;
    public          postgres    false    222   �,       "          0    17529    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    224   �-       *           0    0    otel_otel_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.otel_otel_id_seq', 11, true);
          public          postgres    false    217            +           0    0    otel_pension_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.otel_pension_id_seq', 34, true);
          public          postgres    false    218            ,           0    0    otel_season_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.otel_season_id_seq', 27, true);
          public          postgres    false    219            -           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 30, true);
          public          postgres    false    221            .           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 18, true);
          public          postgres    false    223            /           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 15, true);
          public          postgres    false    225            �           2606    17536    hotel_pension otel_pension_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT otel_pension_pkey PRIMARY KEY (id);
 I   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT otel_pension_pkey;
       public            postgres    false    215                       2606    17538    hotel otel_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT otel_pkey PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.hotel DROP CONSTRAINT otel_pkey;
       public            postgres    false    214            �           2606    17540    hotel_season otel_season_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT otel_season_pkey PRIMARY KEY (id);
 G   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT otel_season_pkey;
       public            postgres    false    216            �           2606    17542    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    220            �           2606    17544    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    222            �           2606    17546    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    224               w  x�eQKn�0]�O�5R		�-����e�T!;A�#�1z�PV��RE��7zo޼�`�$n1�o�1I�z9&��Y1I\�`+ݤ����1t:P��G���Ṑ�>�*=����*�Lß��l2�ł8�Z�}IЃ%S����R^Sв�O�|)Ws"q/�ð_;R�#��(p����Rjʺ�N@Z��D��a#!|(�t��Iأ����F|�hX�==�9���S������;[���h2u��`�0/T��%+aQ]�ymc�3ap/4��42cE�������ab:���˵*d��b���>9�71
��M`�M=��s���������g�$�,*���_�k����6~�άȪK��g         �   x�}���0�g�aP��w��� ��jD�Hm���	RF���|������i4W�����d�^��q=R�g�tk����M~m�9��A��˹=R$��o�zd���K�"s �;X���Henh6GY��������7�2���j�PHXA*�Af8���yy�         �   x�u��!�3�BƏ��K��#����fo8�+@�4h����,AECI�~^�
:�=��D#�-X�GW��Nv�q�mP�1zk�S��F*�� ����O�:c$�����cܨ9��L)v³:b��e��J��>�ej{N"�B�?���<N�           x�}��j�0�k�]Z,Y��]d0��,�5[[�~J�e�J	D1�r|L�d�77�~�.Xk�������q�ʰ/����ݟ+����I�a@�����B��ê:����CL�2�6h�U���ρ$"eZ߇\k9�����d�?�V@>�X���0����n8O+�k�"4�唫����1-�y�9z��؇�x��� ��<�K���%O��!2��{Qȷ�.�8*��U�e���P�����LiA^�43��Q��_�a����ak������          �   x�u�1�0Eg�� �I݄��ʄDХܟ��)IUd5C���ls$f��@��s}�v�axс�Z����)%��āΏ���F~r�Tl"yb�M)�
���R	�B>44� Af�TpgC�Onqa0q�X5k������r{hj��He����H�V`+&Yyx���j	V�PI�bVf�Pt�M�U�eW�^n��-�rk�������h.{c��}�      "   9   x�3�tL����4426�0��9]sr�+SS!�0�!PEeJ"��)��Y�lB� i�     