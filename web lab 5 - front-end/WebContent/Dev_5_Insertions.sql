insert into Profil values (SEQ_ID_PROFIL.NEXTVAL, 'Tavares','Antonie');
insert into Profil values (SEQ_ID_PROFIL.NEXTVAL, 'Brunet','Falix');
insert into Profil values (SEQ_ID_PROFIL.NEXTVAL, 'Farmer','Chuck');
insert into Profil values (SEQ_ID_PROFIL.NEXTVAL, 'Toor','Root');

insert into Client values (SEQ_ID_CLIENT.NEXTVAL, 'BigD@love.com','987654321',1);
insert into Client values (SEQ_ID_CLIENT.NEXTVAL, 'BigHeart@love.com','987654321',2);
insert into Client values (SEQ_ID_CLIENT.NEXTVAL, 'BigHands@love.com','987654321',3);
insert into Client values (SEQ_ID_CLIENT.NEXTVAL, 'root@magasin','toor',4);

insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,4.99,0);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,350.99,0);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,49.99,0);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,19.99,0);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,299.99,0);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,1499.99,30);
insert into Stock values (SEQ_ID_STOCK.NEXTVAL,  100,9000.00,0);

insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Céréales Kellogs (R)','Cest bon pour la santé, miam!',1);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Console Xbox','Jouez avec vos nombreaux 3 amis!',2);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Bottes de cowboy','Prenez vous pour un vrai homme du far west et faites vous arrêter par la police',3);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Harry Potter de lordre du phénix','Harry Potter passe lannée la plus plate de sa vie à Poudlard',4);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Bague en or','En or',5);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Tondeuse 360 noscope','Vous pouvez couper votre gazon, même les yeux fermés!',6);
insert into Produit values (SEQ_ID_PRODUIT.NEXTVAL,'Générateur de sons dapplaudissements','Vous en avez besoin, cest juste que vous ne le savez pas encore',7);

commit;
