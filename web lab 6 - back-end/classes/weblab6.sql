drop sequence seq_id_membre;
drop sequence seq_id_produit;
drop sequence seq_id_vitrine;
drop sequence seq_id_lignevitrine;
drop sequence seq_id_categorie;
drop sequence seq_id_marchand;
drop sequence seq_id_favori;

drop table membre       cascade constraints purge;
drop table produit      cascade constraints purge;
drop table vitrine      cascade constraints purge;
drop table lignevitrine cascade constraints purge;
drop table categorie    cascade constraints purge;
drop table marchand     cascade constraints purge;
drop table favori       cascade constraints purge;

create sequence seq_id_membre       start with 1 increment by 1;
create sequence seq_id_produit      start with 1 increment by 1;
create sequence seq_id_vitrine      start with 1 increment by 1;
create sequence seq_id_lignevitrine start with 1 increment by 1;
create sequence seq_id_categorie    start with 1 increment by 1;
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

create table categorie (
  IDCategorie   NUMBER,
  MembreID      NUMBER,
  Name          VARCHAR2(100),
  constraint pk_categorie         primary key (IDCategorie),
  constraint fk_categorie_membre  foreign key (MembreID) references membre(IDMembre) on delete cascade
);

create table lignevitrine (
  IDLigneVitrine  NUMBER,
  ProduitID       NUMBER,
  VitrineID       NUMBER,
  CategorieID     NUMBER,
  constraint pk_lignevitrine            primary key (IDLigneVitrine),
  constraint fk_lignevitrine_produit    foreign key (ProduitID)   references produit(IDProduit)     on delete cascade,
  constraint fk_lignevitrine_vitrine    foreign key (VitrineID)   references vitrine(IDVitrine)     on delete cascade,
  constraint fk_lignevitrine_categorie  foreign key (CategorieID) references categorie(IDCategorie) on delete cascade
);

create table favori (
  IDFavori    NUMBER,
  ProduitID   NUMBER,
  MembreID    NUMBER,
  constraint pk_favori          primary key (IDFavori),
  constraint fk_favori_produit  foreign key (ProduitID) references produit(IDProduit) on delete cascade,
  constraint fk_favori_membre   foreign key (MembreID)  references membre(IDMembre)   on delete cascade
);