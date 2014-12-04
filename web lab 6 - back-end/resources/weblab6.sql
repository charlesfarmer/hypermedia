drop sequence seq_id_membre;
drop sequence seq_id_produit;
drop sequence seq_id_vitrine;
drop sequence seq_id_ligne_vitrine;
drop sequence seq_id_marchand;
drop sequence seq_id_favori;

drop table membre       cascade constraints purge;
drop table produit      cascade constraints purge;
drop table vitrine      cascade constraints purge;
drop table lignevitrine cascade constraints purge;
drop table marchand     cascade constraints purge;
drop table favori       cascade constraints purge;

create sequence seq_id_membre       start with 1 increment by 1;
create sequence seq_id_produit      start with 1 increment by 1;
create sequence seq_id_vitrine      start with 1 increment by 1;
create sequence seq_id_ligne_vitrine start with 1 increment by 1;
create sequence seq_id_marchand     start with 1 increment by 1;
create sequence seq_id_favori       start with 1 increment by 1;

create table membre (
  IDMembre    NUMBER,
  Username    VARCHAR2(100),
  Password    VARCHAR2(100),
  Email       VARCHAR2(100),
  constraint pk_membre          primary key (IDMembre)
);

create table marchand (
  IDMarchand  NUMBER,
  MembreID    NUMBER,
  Name        VARCHAR2(100),
  LogoURI     VARCHAR2(255),
  constraint pk_marchand        primary key (IDMarchand),
  constraint fk_marchand_membre foreign key (MembreID) references membre(IDMembre) on delete cascade
);

create table produit (
  IDProduit   NUMBER,
  MarchandID  NUMBER,
  Title       VARCHAR2(100),
  URL         VARCHAR2(255),
  Price       NUMBER,
  imgURI      VARCHAR2(255),
  constraint pk_produit         primary key (IDProduit),
  constraint fk_produit_magasin foreign key (MarchandID) references marchand(IDMarchand) on delete cascade
);

create table vitrine (
  IDVitrine   NUMBER,
  MembreID    NUMBER,
  Title       VARCHAR2(100),
  DateAdded   TIMESTAMP,
  constraint pk_vitrine         primary key (IDVitrine),
  constraint fk_vitrine_membre  foreign key (MembreID) references membre(IDMembre) on delete cascade
);

create table lignevitrine (
  IDLigneVitrine  NUMBER,
  ProduitID       NUMBER,
  VitrineID       NUMBER,
  constraint pk_lignevitrine            primary key (IDLigneVitrine),
  constraint fk_lignevitrine_produit    foreign key (ProduitID)   references produit(IDProduit)     on delete cascade,
  constraint fk_lignevitrine_vitrine    foreign key (VitrineID)   references vitrine(IDVitrine)     on delete cascade
);

create table favori (
  IDFavori    NUMBER,
  ProduitID   NUMBER,
  MembreID    NUMBER,
  constraint pk_favori          primary key (IDFavori),
  constraint fk_favori_produit  foreign key (ProduitID) references produit(IDProduit) on delete cascade,
  constraint fk_favori_membre   foreign key (MembreID)  references membre(IDMembre)   on delete cascade
);

insert into membre(IDMembre, Username, Password, Email) values (SEQ_ID_MEMBRE.nextval, 'Roger', 'Rabbit', 'rr@acme.co');
insert into membre(IDMembre, Username, Password, Email) values (SEQ_ID_MEMBRE.nextval, 'Jessica', 'Rabbit', 'jr@acme.co');
insert into marchand(IDMarchand, MembreID, Name, LogoURI) values (SEQ_ID_MARCHAND.nextval, 1, 'ACME', 'Roger/img/acme.jpg');
insert into marchand(IDMarchand, MembreID, Name, LogoURI) values (SEQ_ID_MARCHAND.nextval, 1, 'ACME2', 'Roger/img/acme.jpg');
insert into marchand(IDMarchand, MembreID, Name, LogoURI) values (SEQ_ID_MARCHAND.nextval, 1, 'ACME3', 'Roger/img/acme.jpg');
insert into produit(IDProduit, MarchandID, Title, URL, Price, imgURI) values (SEQ_ID_PRODUIT.nextval, 1, 'Un beau marteau', 'img/marteau.jpg', 99.99, 'Roger/img/acme.jpg');
insert into produit(IDProduit, MarchandID, Title, URL, Price, imgURI) values (SEQ_ID_PRODUIT.nextval, 1, 'Une ceinture', 'img/marteau.jpg', 6.99, 'Roger/img/acme.jpg');
insert into vitrine(IDVitrine, MembreID, Title, DateAdded) values (SEQ_ID_VITRINE.nextval, 1, 'Ma premiere vitrine', SYSDATE);
insert into vitrine(IDVitrine, MembreID, Title, DateAdded) values (SEQ_ID_VITRINE.nextval, 1, 'Ma deuxieme vitrine', SYSDATE);
insert into vitrine(IDVitrine, MembreID, Title, DateAdded) values (SEQ_ID_VITRINE.nextval, 1, 'Ma troisieme vitrine', SYSDATE);
insert into lignevitrine(IDLigneVitrine, ProduitID, VitrineID) values (SEQ_ID_LIGNE_VITRINE.nextval, 1, 1);
insert into lignevitrine(IDLigneVitrine, ProduitID, VitrineID) values (SEQ_ID_LIGNE_VITRINE.nextval, 1, 1);
insert into lignevitrine(IDLigneVitrine, ProduitID, VitrineID) values (SEQ_ID_LIGNE_VITRINE.nextval, 1, 1);
insert into lignevitrine(IDLigneVitrine, ProduitID, VitrineID) values (SEQ_ID_LIGNE_VITRINE.nextval, 1, 1);
insert into favori(IDFavori, ProduitID, MembreID) values (SEQ_ID_FAVORI.nextval, 1, 2);
insert into favori(IDFavori, ProduitID, MembreID) values (SEQ_ID_FAVORI.nextval, 2, 2);
insert into favori(IDFavori, ProduitID, MembreID) values (SEQ_ID_FAVORI.nextval, 2, 2);