PGDMP  .        	            |         
   veterinary    16.1    16.0 3    @           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            A           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            B           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            C           1262    41749 
   veterinary    DATABASE     l   CREATE DATABASE veterinary WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE veterinary;
                postgres    false            �            1259    41988    animals    TABLE       CREATE TABLE public.animals (
    id bigint NOT NULL,
    breed character varying(255),
    color character varying(255),
    date_of_birth date,
    gender character varying(255),
    name character varying(255) NOT NULL,
    species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    41987    animals_id_seq    SEQUENCE     w   CREATE SEQUENCE public.animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.animals_id_seq;
       public          postgres    false    216            D           0    0    animals_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.animals_id_seq OWNED BY public.animals.id;
          public          postgres    false    215            �            1259    41997    appointments    TABLE     �   CREATE TABLE public.appointments (
    id bigint NOT NULL,
    appointment_date timestamp(6) without time zone,
    animal_id bigint,
    doctor_id bigint
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    41996    appointments_id_seq    SEQUENCE     |   CREATE SEQUENCE public.appointments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.appointments_id_seq;
       public          postgres    false    218            E           0    0    appointments_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.appointments_id_seq OWNED BY public.appointments.id;
          public          postgres    false    217            �            1259    42004    avaible_dates    TABLE     m   CREATE TABLE public.avaible_dates (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 !   DROP TABLE public.avaible_dates;
       public         heap    postgres    false            �            1259    42003    avaible_dates_id_seq    SEQUENCE     }   CREATE SEQUENCE public.avaible_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.avaible_dates_id_seq;
       public          postgres    false    220            F           0    0    avaible_dates_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.avaible_dates_id_seq OWNED BY public.avaible_dates.id;
          public          postgres    false    219            �            1259    42011 	   customers    TABLE     �   CREATE TABLE public.customers (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    42010    customers_id_seq    SEQUENCE     y   CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.customers_id_seq;
       public          postgres    false    222            G           0    0    customers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;
          public          postgres    false    221            �            1259    42020    doctors    TABLE     �   CREATE TABLE public.doctors (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    42019    doctors_id_seq    SEQUENCE     w   CREATE SEQUENCE public.doctors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.doctors_id_seq;
       public          postgres    false    224            H           0    0    doctors_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.doctors_id_seq OWNED BY public.doctors.id;
          public          postgres    false    223            �            1259    42029    vaccines    TABLE     �   CREATE TABLE public.vaccines (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255),
    protection_finish_date date,
    protection_start_date date,
    animal_id bigint
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    42028    vaccines_id_seq    SEQUENCE     x   CREATE SEQUENCE public.vaccines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.vaccines_id_seq;
       public          postgres    false    226            I           0    0    vaccines_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.vaccines_id_seq OWNED BY public.vaccines.id;
          public          postgres    false    225            �           2604    41991 
   animals id    DEFAULT     h   ALTER TABLE ONLY public.animals ALTER COLUMN id SET DEFAULT nextval('public.animals_id_seq'::regclass);
 9   ALTER TABLE public.animals ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    42000    appointments id    DEFAULT     r   ALTER TABLE ONLY public.appointments ALTER COLUMN id SET DEFAULT nextval('public.appointments_id_seq'::regclass);
 >   ALTER TABLE public.appointments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    42007    avaible_dates id    DEFAULT     t   ALTER TABLE ONLY public.avaible_dates ALTER COLUMN id SET DEFAULT nextval('public.avaible_dates_id_seq'::regclass);
 ?   ALTER TABLE public.avaible_dates ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    42014    customers id    DEFAULT     l   ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);
 ;   ALTER TABLE public.customers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    42023 
   doctors id    DEFAULT     h   ALTER TABLE ONLY public.doctors ALTER COLUMN id SET DEFAULT nextval('public.doctors_id_seq'::regclass);
 9   ALTER TABLE public.doctors ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    42032    vaccines id    DEFAULT     j   ALTER TABLE ONLY public.vaccines ALTER COLUMN id SET DEFAULT nextval('public.vaccines_id_seq'::regclass);
 :   ALTER TABLE public.vaccines ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            3          0    41988    animals 
   TABLE DATA           f   COPY public.animals (id, breed, color, date_of_birth, gender, name, species, customer_id) FROM stdin;
    public          postgres    false    216   �9       5          0    41997    appointments 
   TABLE DATA           R   COPY public.appointments (id, appointment_date, animal_id, doctor_id) FROM stdin;
    public          postgres    false    218   8:       7          0    42004    avaible_dates 
   TABLE DATA           F   COPY public.avaible_dates (id, available_date, doctor_id) FROM stdin;
    public          postgres    false    220   �:       9          0    42011 	   customers 
   TABLE DATA           I   COPY public.customers (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    222   �:       ;          0    42020    doctors 
   TABLE DATA           G   COPY public.doctors (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    224   �;       =          0    42029    vaccines 
   TABLE DATA           l   COPY public.vaccines (id, code, name, protection_finish_date, protection_start_date, animal_id) FROM stdin;
    public          postgres    false    226   *<       J           0    0    animals_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.animals_id_seq', 26, true);
          public          postgres    false    215            K           0    0    appointments_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.appointments_id_seq', 20, true);
          public          postgres    false    217            L           0    0    avaible_dates_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.avaible_dates_id_seq', 17, true);
          public          postgres    false    219            M           0    0    customers_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.customers_id_seq', 9, true);
          public          postgres    false    221            N           0    0    doctors_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.doctors_id_seq', 6, true);
          public          postgres    false    223            O           0    0    vaccines_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.vaccines_id_seq', 18, true);
          public          postgres    false    225            �           2606    41995    animals animals_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    216            �           2606    42002    appointments appointments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    218            �           2606    42009     avaible_dates avaible_dates_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.avaible_dates
    ADD CONSTRAINT avaible_dates_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.avaible_dates DROP CONSTRAINT avaible_dates_pkey;
       public            postgres    false    220            �           2606    42018    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    222            �           2606    42027    doctors doctors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    224            �           2606    42036    vaccines vaccines_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    226            �           2606    42042 (   appointments fk95vepu86o8syqtux9gkr71bhy    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public          postgres    false    218    3475    216            �           2606    42037 #   animals fkb36lt3kj4mrbdx5btxmp4j60n    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public          postgres    false    3481    222    216            �           2606    42047 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public          postgres    false    216    3475    226            �           2606    42052 (   appointments fkmujeo4tymoo98cmf7uj3vsv76    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public          postgres    false    3483    218    224            �           2606    42057 )   avaible_dates fko7h9k2t0ec92g58sc9mb84y7t    FK CONSTRAINT     �   ALTER TABLE ONLY public.avaible_dates
    ADD CONSTRAINT fko7h9k2t0ec92g58sc9mb84y7t FOREIGN KEY (doctor_id) REFERENCES public.doctors(id);
 S   ALTER TABLE ONLY public.avaible_dates DROP CONSTRAINT fko7h9k2t0ec92g58sc9mb84y7t;
       public          postgres    false    224    220    3483            3   �   x�e���0F��,@��P`�^�T+�)�R���@�jPhQ��gY~��,/�o�4��'����ɭK���PY*Ω��8k,�41XS3�(Ў�h�y��N��z2tܳMȷ��R��j[����~��Y�����4�5�_O��'�?�B%�$�uHf������o�8Qq      5   E   x�3�4202�54�54V04�20 "��!���1���%H�D����r�f��\F��́r1z\\\ uL      7   >   x�M̻  C�:��˰�@�ܜ�d���M�L�����%�uԞ`>S�g��l.�e      9   �   x�U�A�0����0�F+��.ݺyL����=<�̤@Rf�g���09���C0#�	T�=j�k({8(SJ�<��E!U��陵#ėD.��r"'��ŬWQ��;��Ĳ[)u�����w.�͉Kb`}���bو�R<B�?t�X�      ;   �   x���1�0�z�0I$�Zy��ّ �H����{)�������v	{�B��u���G�"W�S .UQئ�C0��F�:�ڐ'�ȡ'�K3�E|7%�!k-f���w��1-�o,\E'iOe�~�}���6w
?�Z�      =   �   x�}�A� E��)� �
���!i��C]�2.]{�^�Ć8������+�x����HR'���I�a*L�+?�|xܞ~�'�L��}5a�:�]���9'뚬K2I�j����>&uk�U�~ ��\gZ���[�SZ����`���?�mh�a�� ��N\     