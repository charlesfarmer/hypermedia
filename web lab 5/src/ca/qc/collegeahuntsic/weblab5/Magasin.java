
package ca.qc.collegeahuntsic.weblab5;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import ca.qc.collegeahuntsic.weblab5.bean.AchatBean;
import ca.qc.collegeahuntsic.weblab5.bean.ClientBean;
import ca.qc.collegeahuntsic.weblab5.bean.LigneFactureBean;
import ca.qc.collegeahuntsic.weblab5.bean.LignePanierBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProduitBean;
import ca.qc.collegeahuntsic.weblab5.bean.ProfilBean;
import ca.qc.collegeahuntsic.weblab5.bean.StockBean;
import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;

public class Magasin {

    public static void main(String[] args) {

        try {

            MagasinCreateur gestionMagasin = new MagasinCreateur(args[0],
                args[1],
                args[2],
                args[3]);

            try {

                AchatBean achatBean = new AchatBean();
                ClientBean clientBean = new ClientBean();
                ProfilBean profilBean = new ProfilBean();
                LignePanierBean lignePanierBean = new LignePanierBean();
                StockBean stockBean = new StockBean();
                ProduitBean produitBean = new ProduitBean();
                LigneFactureBean ligneFactureBean = new LigneFactureBean();

                String nom = "a", prenom = "aa";
                String email = "a@a.a", password = "abcd";
                BigDecimal prix = new BigDecimal(10);
                BigDecimal rabais = prix;
                int quantite = 6;
                String description = "description lala", nomProduit = "nom du produit 2000";

                profilBean.setNom(nom);
                profilBean.setPrenom(prenom);

                clientBean.setEmail(email);
                clientBean.setPassword(password);
                clientBean.setProfilBean(profilBean);

                stockBean.setPrix(prix);
                stockBean.setQuantite(quantite);
                stockBean.setRabais(rabais);

                produitBean.setDescription(description);
                produitBean.setNom(nomProduit);
                produitBean.setStockBean(stockBean);

                lignePanierBean.setClientBean(clientBean);
                lignePanierBean.setProduitBean(produitBean);
                lignePanierBean.setQuantite(quantite);

                achatBean.setClientBean(clientBean);
                achatBean.setDateAchat(new Timestamp(System.currentTimeMillis()));

                ligneFactureBean.setAchatBean(achatBean);
                ligneFactureBean.setProduitBean(produitBean);
                ligneFactureBean.setQuantite(quantite);
                ligneFactureBean.setPrix(produitBean.getStockBean().getPrix());

                profilBean = gestionMagasin.getProfilService().add(gestionMagasin.getConnexion(),
                    profilBean);
                clientBean = gestionMagasin.getClientService().add(gestionMagasin.getConnexion(),
                    clientBean);
                stockBean = gestionMagasin.getStockService().add(gestionMagasin.getConnexion(),
                    stockBean);
                produitBean = gestionMagasin.getProduitService().add(gestionMagasin.getConnexion(),
                    produitBean);
                achatBean = gestionMagasin.getAchatService().add(gestionMagasin.getConnexion(),
                    achatBean);
                lignePanierBean = gestionMagasin.getLignePanierService().add(gestionMagasin.getConnexion(),
                    lignePanierBean);
                ligneFactureBean = gestionMagasin.getLigneFactureService().add(gestionMagasin.getConnexion(),
                    ligneFactureBean);

                gestionMagasin.getProfilService().update(gestionMagasin.getConnexion(),
                    profilBean);
                gestionMagasin.getClientService().update(gestionMagasin.getConnexion(),
                    clientBean);
                gestionMagasin.getStockService().update(gestionMagasin.getConnexion(),
                    stockBean);
                gestionMagasin.getProduitService().update(gestionMagasin.getConnexion(),
                    produitBean);
                gestionMagasin.getAchatService().update(gestionMagasin.getConnexion(),
                    achatBean);
                gestionMagasin.getLignePanierService().update(gestionMagasin.getConnexion(),
                    lignePanierBean);
                gestionMagasin.getLigneFactureService().update(gestionMagasin.getConnexion(),
                    ligneFactureBean);

                System.out.println(profilBean.toString());
                System.out.println(clientBean.toString());
                System.out.println(stockBean.toString());
                System.out.println(produitBean.toString());
                System.out.println(achatBean.toString());
                System.out.println(lignePanierBean.toString());
                System.out.println(ligneFactureBean.toString());

                // *
                gestionMagasin.getLignePanierService().delete(gestionMagasin.getConnexion(),
                    lignePanierBean);
                gestionMagasin.getLigneFactureService().delete(gestionMagasin.getConnexion(),
                    ligneFactureBean);
                gestionMagasin.getProduitService().delete(gestionMagasin.getConnexion(),
                    produitBean);
                gestionMagasin.getStockService().delete(gestionMagasin.getConnexion(),
                    stockBean);
                gestionMagasin.getAchatService().delete(gestionMagasin.getConnexion(),
                    achatBean);
                gestionMagasin.getClientService().delete(gestionMagasin.getConnexion(),
                    clientBean);
                gestionMagasin.getProfilService().delete(gestionMagasin.getConnexion(),
                    profilBean);

                // */

                gestionMagasin.getConnexion().commit();
                gestionMagasin.close();

            } catch(
                ServiceException
                | SQLException e) {

                e.printStackTrace();
                gestionMagasin.rollback();
            }
        } catch(MagasinException e1) {
            e1.printStackTrace();
        }
    }

}
