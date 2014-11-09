drop table Client       cascade constraints purge;
drop table Profil       cascade constraints purge;
drop table Produit      cascade constraints purge;
drop table LignePanier  cascade constraints purge;
drop table Achat        cascade constraints purge;
drop table Stock        cascade constraints purge;
drop table LigneFacture cascade constraints purge;

drop sequence SEQ_ID_CLIENT;
drop sequence SEQ_ID_PROFIL;
drop sequence SEQ_ID_PRODUIT;
drop sequence SEQ_ID_LIGNEPANIER;
drop sequence SEQ_ID_ACHAT;
drop sequence SEQ_ID_STOCK;
drop sequence SEQ_ID_LIGNEFACTURE;

create sequence SEQ_ID_CLIENT       start with 1 increment by 1;
create sequence SEQ_ID_PROFIL       start with 1 increment by 1;
create sequence SEQ_ID_PRODUIT      start with 1 increment by 1;
create sequence SEQ_ID_LIGNEPANIER  start with 1 increment by 1;
create sequence SEQ_ID_ACHAT        start with 1 increment by 1;
create sequence SEQ_ID_STOCK        start with 1 increment by 1;
create sequence SEQ_ID_LIGNEFACTURE start with 1 increment by 1;

create table Profil (
    IDProfil    number(10),  
    Nom         varchar2(20) not null,  
    Prenom      varchar2(20) not null,
    constraint PK_Profil primary key(IDProfil)
);

create table Client (
    IDClient    number(10),
    Email       varchar2(50) not null,
    Passwd      varchar2(30) not null,
    ProfilID    number(10),
    constraint PK_Client primary key(IDClient),
    constraint FK_Client_Profil foreign key(ProfilID) references Profil(IDProfil)
);

create table Stock (
    IDStock     number(10),
    Quantite    number(5) not null,
    Prix        number(6,2) not null,
    Rabais      number(6,2) not null,
    constraint PK_Stock primary key(IDStock)
);

create table Produit (
    IDProduit   number(10),
    Nom         varchar2(50) not null,
    Description varchar2(128) not null,
    StockID     number(10),
    constraint PK_Produit primary key(IDProduit),
    constraint FK_Produit_Stock foreign key(StockID) references Stock(IDStock)
);

create table LignePanier (
    IDLignePanier   number(10),
    ClientID        number(10),
    ProduitID       number(10),
    Quantite        number(5) not null,
    constraint PK_LignePanier primary key(IDLignePanier),
    constraint FK_LignePanier_Client foreign key(ClientID) references Client(IDClient),
    constraint FK_LignePanier_Produit foreign key(ProduitID) references Produit(IDProduit)
);

create table Achat (
    IDAchat     number(10),
    ClientID    number(10),
    DateAchat   date not null,
    constraint PK_Achat primary key(IDAchat),
    constraint FK_Achat_Client foreign key(ClientID) references Client(IDClient)
);

create table LigneFacture (
    IDLigneFacture  number(10),
    AchatID         number(10),
    ProduitID       number(10),
    Quantite        number(5) not null,
    Prix            number(6,2) not null,
    constraint PK_LigneFacture primary key(IDLigneFacture),
    constraint FK_LigneFacture_Achat foreign key(AchatID) references Achat(IDAchat),
    constraint FK_LigneFacture_Produit foreign key(ProduitID) references Produit(IDProduit)
);
