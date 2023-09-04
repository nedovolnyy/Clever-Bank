--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

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

--
-- Name: bank; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.bank (
    id bigint NOT NULL,
    name character varying(200) NOT NULL
);


--
-- Name: Bank_Id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Bank_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Bank_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Bank_Id_seq" OWNED BY public.bank.id;


--
-- Name: bill; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.bill (
    id bigint NOT NULL,
    "bankId" bigint NOT NULL,
    "userId" bigint NOT NULL,
    balance double precision NOT NULL,
    currency character varying(3) NOT NULL,
    "dateOfOpening" date NOT NULL,
    "dateOfExpiration" date NOT NULL,
    iban character varying(28) NOT NULL,
    "isGetPercent" boolean DEFAULT false NOT NULL
);


--
-- Name: Bill_BankId_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Bill_BankId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Bill_BankId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Bill_BankId_seq" OWNED BY public.bill."bankId";


--
-- Name: Bill_Id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Bill_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Bill_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Bill_Id_seq" OWNED BY public.bill.id;


--
-- Name: Bill_UserId_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Bill_UserId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Bill_UserId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Bill_UserId_seq" OWNED BY public.bill."userId";


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.transaction (
    id bigint NOT NULL,
    "typeOfTransaction" character varying(50) NOT NULL,
    "timeOfTransaction" timestamp with time zone NOT NULL,
    "senderBillId" bigint NOT NULL,
    "receiverBillId" bigint NOT NULL,
    summ double precision NOT NULL,
    description character varying(500)
);


--
-- Name: Transaction_Id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Transaction_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Transaction_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Transaction_Id_seq" OWNED BY public.transaction.id;


--
-- Name: Transaction_ReceiverBillId_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Transaction_ReceiverBillId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Transaction_ReceiverBillId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Transaction_ReceiverBillId_seq" OWNED BY public.transaction."receiverBillId";


--
-- Name: Transaction_SenderBillId_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."Transaction_SenderBillId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: Transaction_SenderBillId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."Transaction_SenderBillId_seq" OWNED BY public.transaction."senderBillId";


--
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    "fullName" character varying(200) NOT NULL
);


--
-- Name: User_Id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public."User_Id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: User_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public."User_Id_seq" OWNED BY public."user".id;


--
-- Name: bank id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bank ALTER COLUMN id SET DEFAULT nextval('public."Bank_Id_seq"'::regclass);


--
-- Name: bill id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill ALTER COLUMN id SET DEFAULT nextval('public."Bill_Id_seq"'::regclass);


--
-- Name: bill bankId; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill ALTER COLUMN "bankId" SET DEFAULT nextval('public."Bill_BankId_seq"'::regclass);


--
-- Name: bill userId; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill ALTER COLUMN "userId" SET DEFAULT nextval('public."Bill_UserId_seq"'::regclass);


--
-- Name: transaction id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction ALTER COLUMN id SET DEFAULT nextval('public."Transaction_Id_seq"'::regclass);


--
-- Name: transaction senderBillId; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction ALTER COLUMN "senderBillId" SET DEFAULT nextval('public."Transaction_SenderBillId_seq"'::regclass);


--
-- Name: transaction receiverBillId; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction ALTER COLUMN "receiverBillId" SET DEFAULT nextval('public."Transaction_ReceiverBillId_seq"'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public."User_Id_seq"'::regclass);


--
-- Data for Name: bank; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.bank (id, name) VALUES (1, 'Clever-Bank');
INSERT INTO public.bank (id, name) VALUES (2, 'Priorbank');
INSERT INTO public.bank (id, name) VALUES (3, 'Belagroprombank');
INSERT INTO public.bank (id, name) VALUES (4, 'Belpromstroy bank');
INSERT INTO public.bank (id, name) VALUES (5, 'Paritet bank');
INSERT INTO public.bank (id, name) VALUES (6, 'MMMbank');


--
-- Data for Name: bill; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (47, 4, 9, 12, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000003258', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (1, 1, 1, 78.21, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000005465', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (3, 2, 1, 342.32, 'EUR', '2019-07-01', '2028-07-01', 'BY43800000000000000000005123', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (5, 2, 2, 2.12, 'BYN', '2018-08-01', '2027-08-01', 'BY43800000000000000000002242', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (4, 5, 2, 36, 'BYN', '2015-02-01', '2024-02-01', 'BY45800000000000000000002323', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (2, 1, 2, 21499, 'USD', '2017-03-01', '2025-03-01', 'BY44800000000000000000002512', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (7, 5, 3, 234, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000002231', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (8, 1, 6, 61, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000006378', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (9, 4, 11, 1517, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006382', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (10, 2, 8, 11, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000008774', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (11, 3, 21, 13, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000004566', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (12, 5, 4, 235, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000008849', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (13, 3, 9, 2351, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000007915', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (14, 1, 6, 1241, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000007823', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (15, 4, 18, 16, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000007891', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (16, 4, 15, 6, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000006440', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (17, 3, 8, 6, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000006814', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (18, 2, 8, 146, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000001486', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (19, 3, 4, 134, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000008414', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (20, 2, 7, 1616, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000003547', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (21, 1, 16, 1717, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006934', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (22, 1, 11, 1818, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006992', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (23, 5, 12, 185, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000007185', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (24, 3, 19, 123, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000009817', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (25, 5, 10, 1234, 'UAH', '2016-02-01', '2024-02-01', 'BY44800000000000000000008452', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (26, 3, 7, 134, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000009642', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (27, 4, 6, 3456, 'UAH', '2016-02-01', '2024-02-01', 'BY44800000000000000000004699', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (28, 2, 5, 123, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000003447', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (29, 3, 12, 6456, 'UAH', '2016-02-01', '2024-02-01', 'BY44800000000000000000001471', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (30, 5, 14, 134, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000003251', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (31, 1, 17, 14, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000002496', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (32, 3, 21, 1234, 'EUR', '2016-02-01', '2024-02-01', 'BY44800000000000000000002541', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (33, 1, 20, 632, 'UAH', '2016-02-01', '2024-02-01', 'BY44800000000000000000000247', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (34, 3, 13, 661, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000000477', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (35, 1, 14, 14, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000000287', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (36, 2, 18, 134, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000000687', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (37, 2, 13, 134, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000000254', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (38, 1, 10, 14134, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006841', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (39, 1, 14, 14, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000002011', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (40, 2, 15, 5, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006448', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (41, 3, 17, 2341, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006488', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (42, 5, 14, 3, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000002554', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (43, 5, 1, 4, 'UAH', '2016-02-01', '2024-02-01', 'BY44800000000000000000003145', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (44, 2, 6, 0, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000004156', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (45, 5, 3, 23, 'BYN', '2016-02-01', '2024-02-01', 'BY44800000000000000000006188', false);
INSERT INTO public.bill (id, "bankId", "userId", balance, currency, "dateOfOpening", "dateOfExpiration", iban, "isGetPercent") VALUES (46, 2, 7, 23, 'USD', '2016-02-01', '2024-02-01', 'BY44800000000000000000002145', false);


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.transaction (id, "typeOfTransaction", "timeOfTransaction", "senderBillId", "receiverBillId", summ, description) VALUES (1, 'Благотворительность', '2023-02-23 00:00:00+03', 2, 1, 22.5, 'На лечение');
INSERT INTO public.transaction (id, "typeOfTransaction", "timeOfTransaction", "senderBillId", "receiverBillId", summ, description) VALUES (2, 'Перевод', '2023-02-22 00:00:00+03', 2, 2, 789.54, 'За корову');


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public."user" (id, "fullName") VALUES (1, 'Валеев Руслан Ринатович');
INSERT INTO public."user" (id, "fullName") VALUES (2, 'Смоляков Вячеслав Никифорович');
INSERT INTO public."user" (id, "fullName") VALUES (3, 'Петров Валерий Федорович');
INSERT INTO public."user" (id, "fullName") VALUES (4, 'Крот Артём Константинович');
INSERT INTO public."user" (id, "fullName") VALUES (5, 'Савин Григорий Емельянович');
INSERT INTO public."user" (id, "fullName") VALUES (6, 'Соловец Андрей Петрович');
INSERT INTO public."user" (id, "fullName") VALUES (7, 'Вангог Винцент Валерьевич');
INSERT INTO public."user" (id, "fullName") VALUES (8, 'Анатольев Артём Леонидович');
INSERT INTO public."user" (id, "fullName") VALUES (9, 'Семечкин Василий Петрович');
INSERT INTO public."user" (id, "fullName") VALUES (10, 'Щелкарин Зураб Вахтангович');
INSERT INTO public."user" (id, "fullName") VALUES (11, 'Кашин Олег Вадимович');
INSERT INTO public."user" (id, "fullName") VALUES (12, 'Лемех Потап Павлович');
INSERT INTO public."user" (id, "fullName") VALUES (13, 'Воротилов Гаврила Андреевич');
INSERT INTO public."user" (id, "fullName") VALUES (14, 'Маговкин Вениамин Васильевич');
INSERT INTO public."user" (id, "fullName") VALUES (15, 'Раневская Фаина Аркадьевна');
INSERT INTO public."user" (id, "fullName") VALUES (16, 'Аронова Анастасия Олеговна');
INSERT INTO public."user" (id, "fullName") VALUES (17, 'Кобец Ирина Павловна');
INSERT INTO public."user" (id, "fullName") VALUES (18, 'Валеева Валентина Казимировна');
INSERT INTO public."user" (id, "fullName") VALUES (19, 'Важнова Татьяна Дмитриевна');
INSERT INTO public."user" (id, "fullName") VALUES (20, 'Онегина Рината Андреевна');
INSERT INTO public."user" (id, "fullName") VALUES (21, 'Никифоров Павел Иванович');
INSERT INTO public."user" (id, "fullName") VALUES (22, 'Скороход Борис Емельянович');


--
-- Name: Bank_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Bank_Id_seq"', 6, true);


--
-- Name: Bill_BankId_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Bill_BankId_seq"', 1, false);


--
-- Name: Bill_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Bill_Id_seq"', 47, true);


--
-- Name: Bill_UserId_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Bill_UserId_seq"', 1, false);


--
-- Name: Transaction_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Transaction_Id_seq"', 2, true);


--
-- Name: Transaction_ReceiverBillId_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Transaction_ReceiverBillId_seq"', 1, false);


--
-- Name: Transaction_SenderBillId_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Transaction_SenderBillId_seq"', 1, false);


--
-- Name: User_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."User_Id_seq"', 22, true);


--
-- Name: bank Bank_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bank
    ADD CONSTRAINT "Bank_pkey" PRIMARY KEY (id);


--
-- Name: bill Bill_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT "Bill_pkey" PRIMARY KEY (id);


--
-- Name: transaction Transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT "Transaction_pkey" PRIMARY KEY (id);


--
-- Name: user User_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- Name: bill FK_Bank_Bill; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT "FK_Bank_Bill" FOREIGN KEY ("bankId") REFERENCES public.bank(id);


--
-- Name: transaction FK_ReceiverBill_Transaction; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT "FK_ReceiverBill_Transaction" FOREIGN KEY ("receiverBillId") REFERENCES public.bill(id);


--
-- Name: transaction FK_SenderBill_Transaction; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT "FK_SenderBill_Transaction" FOREIGN KEY ("senderBillId") REFERENCES public.bill(id);


--
-- Name: bill FK_User_Bill; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bill
    ADD CONSTRAINT "FK_User_Bill" FOREIGN KEY ("userId") REFERENCES public."user"(id);


--
-- PostgreSQL database dump complete
--

