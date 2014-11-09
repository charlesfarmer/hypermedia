package ca.qc.collegeahuntsic.weblab5;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;

import ca.qc.collegeahuntsic.weblab5.exception.MagasinException;
import ca.qc.collegeahuntsic.weblab5.exception.service.ServiceException;
import ca.qc.collegeahuntsic.weblab5.util.MagasinCreateur;
import ca.qc.collegeahuntsic.weblab5.bean.*;

public class UnitTests {

	public UnitTests() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			
			MagasinCreateur magasin = new MagasinCreateur(args[0], args[1], args[2], args[3]);
			
			try {
				
				unitTestClient(magasin);
				unitTestProfil(magasin);
				unitTestStock(magasin);
				unitTestProduit(magasin);
				unitTestLignePanier(magasin);
				unitTestAchat(magasin);
				unitTestLigneFacture(magasin);
				
			} catch (MagasinException | ServiceException | InterruptedException e) {
				e.printStackTrace();
				magasin.rollback();
			}
		} catch (MagasinException e) {
			e.printStackTrace();
		}
	}

	private static void unitTestLigneFacture(MagasinCreateur magasin) throws ServiceException, MagasinException {
		LigneFactureBean ligneFactureBean = new LigneFactureBean();
		
		AchatBean achatBean = new AchatBean();
		ClientBean clientBean = new ClientBean();
		clientBean.setEmail("a@a.a");
		clientBean.setPassword("1234");
		ProfilBean profilBean = new ProfilBean();
		profilBean.setNom("Georges");
		profilBean.setPrenom("Power");
		profilBean = magasin.getProfilService().add(magasin.getConnexion(), profilBean);
		clientBean.setProfilBean(profilBean);
		clientBean = magasin.getClientService().add(magasin.getConnexion(), clientBean);
		achatBean.setClientBean(clientBean);
		achatBean.setDateAchat(new Timestamp(System.currentTimeMillis()));
		achatBean = magasin.getAchatService().add(magasin.getConnexion(), achatBean);
		ligneFactureBean.setAchatBean(achatBean);
		
		ProduitBean produitBean = new ProduitBean();
		produitBean.setDescription("meow");
		produitBean.setNom("shamWOW");
		StockBean stockBean = new StockBean();
		stockBean.setPrix(new BigDecimal(10));
		stockBean.setQuantite(5);
		stockBean.setRabais(new BigDecimal(0));
		stockBean = magasin.getStockService().add(magasin.getConnexion(), stockBean);
		produitBean.setStockBean(stockBean);
		produitBean = magasin.getProduitService().add(magasin.getConnexion(), produitBean);
		ligneFactureBean.setProduitBean(produitBean);
		
		ligneFactureBean.setQuantite(5678);
		ligneFactureBean.setPrix(ligneFactureBean.getProduitBean().getStockBean().getPrix());
		
		ligneFactureBean = magasin.getLigneFactureService().add(magasin.getConnexion(), ligneFactureBean);
		
		System.out.println("\n\n##### ADD \n\n" + ligneFactureBean.toString());
		
		ligneFactureBean = magasin.getLigneFactureService().get(magasin.getConnexion(), ligneFactureBean);
		
		System.out.println("\n\n##### READ \n\n" + ligneFactureBean.toString());
		
		ligneFactureBean.setQuantite(9876);
		magasin.getLigneFactureService().update(magasin.getConnexion(), ligneFactureBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + ligneFactureBean.toString());
		
		System.out.println("\n\n##### FIND BY ACHAT\n\n");
		for(LigneFactureBean b : magasin.getLigneFactureService().findByAchat(magasin.getConnexion(), achatBean)){
			System.out.println(b.toString());
		}
		
		magasin.getLigneFactureService().delete(magasin.getConnexion(), ligneFactureBean);
		magasin.getAchatService().delete(magasin.getConnexion(), achatBean);
		magasin.getProduitService().delete(magasin.getConnexion(), produitBean);
		magasin.getClientService().delete(magasin.getConnexion(), clientBean);
		magasin.getStockService().delete(magasin.getConnexion(), stockBean);
		magasin.getProfilService().delete(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
		
	}

	private static void unitTestAchat(MagasinCreateur magasin) throws ServiceException, InterruptedException {
		AchatBean achatBean = new AchatBean();
		
		ClientBean clientBean = new ClientBean();
		clientBean.setEmail("a@a.a");
		clientBean.setPassword("1234");
		ProfilBean profilBean = new ProfilBean();
		profilBean.setNom("Georges");
		profilBean.setPrenom("Power");
		profilBean = magasin.getProfilService().add(magasin.getConnexion(), profilBean);
		clientBean.setProfilBean(profilBean);
		clientBean = magasin.getClientService().add(magasin.getConnexion(), clientBean);
		
		achatBean.setClientBean(clientBean);
		achatBean.setDateAchat(new Timestamp(System.currentTimeMillis()));
		
		achatBean = magasin.getAchatService().add(magasin.getConnexion(), achatBean);
		
		System.out.println("\n\n##### ADD \n\n" + achatBean.toString());
		
		achatBean = magasin.getAchatService().get(magasin.getConnexion(), achatBean);
		
		System.out.println("\n\n##### READ \n\n" + achatBean.toString());
		
		Thread.sleep(1000);
		achatBean.setDateAchat(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("\n\n##### UPDATE \n\n" + achatBean.toString());
		
		magasin.getAchatService().delete(magasin.getConnexion(), achatBean);
		magasin.getClientService().delete(magasin.getConnexion(), clientBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
		
	}

	private static void unitTestLignePanier(MagasinCreateur magasin) throws ServiceException {
		LignePanierBean lignePanierBean = new LignePanierBean();
		
		ClientBean clientBean = new ClientBean();
		clientBean.setEmail("a@a.a");
		clientBean.setPassword("1234");
		ProfilBean profilBean = new ProfilBean();
		profilBean.setNom("Georges");
		profilBean.setPrenom("Power");
		profilBean = magasin.getProfilService().add(magasin.getConnexion(), profilBean);
		clientBean.setProfilBean(profilBean);
		clientBean = magasin.getClientService().add(magasin.getConnexion(), clientBean);
		
		ProduitBean produitBean = new ProduitBean();
		produitBean.setDescription("meow");
		produitBean.setNom("shamWOW");
		StockBean stockBean = new StockBean();
		stockBean.setPrix(new BigDecimal(10));
		stockBean.setQuantite(5);
		stockBean.setRabais(new BigDecimal(0));
		stockBean = magasin.getStockService().add(magasin.getConnexion(), stockBean);
		produitBean.setStockBean(stockBean);
		produitBean = magasin.getProduitService().add(magasin.getConnexion(), produitBean);
		
		lignePanierBean.setClientBean(clientBean);
		lignePanierBean.setProduitBean(produitBean);
		lignePanierBean.setQuantite(12);
		
		magasin.getLignePanierService().add(magasin.getConnexion(), lignePanierBean);
		
		System.out.println("\n\n##### ADD \n\n" + lignePanierBean.toString());
		
		lignePanierBean = magasin.getLignePanierService().get(magasin.getConnexion(), lignePanierBean);
		
		System.out.println("\n\n##### READ \n\n" + lignePanierBean.toString());
		
		lignePanierBean.setQuantite(60);
		
		magasin.getLignePanierService().update(magasin.getConnexion(), lignePanierBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + lignePanierBean.toString());
		
		magasin.getLignePanierService().add(magasin.getConnexion(), lignePanierBean);
		magasin.getLignePanierService().add(magasin.getConnexion(), lignePanierBean);
		magasin.getLignePanierService().add(magasin.getConnexion(), lignePanierBean);
		
		System.out.println("\n\n##### FIND BY CLIENT \n\n");
		for(LignePanierBean b : magasin.getLignePanierService().findByClient(magasin.getConnexion(), lignePanierBean.getClientBean())){
			System.out.println(b.toString());
		}
		
		magasin.getLignePanierService().deleteByClient(magasin.getConnexion(), clientBean);
		
		System.out.println("\n\n##### DELETE BY CLIENT \n\n");
		
		magasin.getLignePanierService().delete(magasin.getConnexion(), lignePanierBean);
		magasin.getProduitService().delete(magasin.getConnexion(), produitBean);
		magasin.getStockService().delete(magasin.getConnexion(), stockBean);
		magasin.getClientService().delete(magasin.getConnexion(), clientBean);
		magasin.getProfilService().delete(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
	}

	private static void unitTestProduit(MagasinCreateur magasin) throws ServiceException {
		ProduitBean produitBean = new ProduitBean();
		produitBean.setDescription("meow");
		produitBean.setNom("shamWOW");
		StockBean stockBean = new StockBean();
		stockBean.setPrix(new BigDecimal(10));
		stockBean.setQuantite(5);
		stockBean.setRabais(new BigDecimal(0));
		stockBean = magasin.getStockService().add(magasin.getConnexion(), stockBean);
		produitBean.setStockBean(stockBean);
		
		produitBean = magasin.getProduitService().add(magasin.getConnexion(), produitBean);
		
		System.out.println("\n\n##### ADD \n\n" + produitBean.toString());
		
		produitBean = magasin.getProduitService().get(magasin.getConnexion(), produitBean);
		
		System.out.println("\n\n##### READ \n\n" + produitBean.toString());
		
		produitBean.setDescription("miaou");
		produitBean.setNom("shamROCK");
		
		magasin.getProduitService().update(magasin.getConnexion(), produitBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + produitBean.toString());
		
		magasin.getProduitService().delete(magasin.getConnexion(), produitBean);
		magasin.getStockService().delete(magasin.getConnexion(), stockBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
	}

	private static void unitTestStock(MagasinCreateur magasin) throws ServiceException {
		StockBean stockBean = new StockBean();
		stockBean.setPrix(new BigDecimal(10));
		stockBean.setQuantite(5);
		stockBean.setRabais(new BigDecimal(0));
	
		magasin.getStockService().add(magasin.getConnexion(), stockBean);
		
		System.out.println("\n\n##### ADD \n\n" + stockBean.toString());
		
		stockBean = magasin.getStockService().get(magasin.getConnexion(), stockBean);
		
		System.out.println("\n\n##### READ \n\n" + stockBean.toString());
		
		stockBean.setPrix(new BigDecimal(20));
		stockBean.setQuantite(15);
		stockBean.setRabais(new BigDecimal(99));
		magasin.getStockService().update(magasin.getConnexion(), stockBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + stockBean.toString());
		
		magasin.getStockService().delete(magasin.getConnexion(), stockBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
	}

	private static void unitTestProfil(MagasinCreateur magasin) throws ServiceException {
		ProfilBean profilBean = new ProfilBean();
		profilBean.setNom("Georges");
		profilBean.setPrenom("Power");
		
		profilBean = magasin.getProfilService().add(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n##### ADD \n\n" + profilBean.toString());
		
		profilBean = magasin.getProfilService().get(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n##### READ \n\n" + profilBean.toString());
		
		profilBean.setNom("Lucie");
		profilBean.setPrenom("Dynamite");
		magasin.getProfilService().update(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + profilBean.toString());
		
		magasin.getProfilService().delete(magasin.getConnexion(), profilBean);
		
		System.out.println("\n\n[][][] DELETED \n\n");
	}

	private static void unitTestClient(MagasinCreateur magasin) throws ServiceException {
		ClientBean clientBean = new ClientBean();
		clientBean.setEmail("a@a.a");
		clientBean.setPassword("1234");
		ProfilBean profilBean = new ProfilBean();
		profilBean.setNom("Georges");
		profilBean.setPrenom("Power");
		profilBean = magasin.getProfilService().add(magasin.getConnexion(), profilBean);
		clientBean.setProfilBean(profilBean);
		
		clientBean = magasin.getClientService().add(magasin.getConnexion(), clientBean);
		
		System.out.println("\n\n##### ADD \n\n" + clientBean.toString());
		
		clientBean = magasin.getClientService().get(magasin.getConnexion(), clientBean); 
		
		System.out.println("\n\n##### READ \n\n" + clientBean.toString());
		
		clientBean.setEmail("b@b.b");
		clientBean.setPassword("9999");
		magasin.getClientService().update(magasin.getConnexion(), clientBean);
		
		System.out.println("\n\n##### UPDATE \n\n" + clientBean.toString());
		
		clientBean = magasin.getClientService().findByEmail(magasin.getConnexion(), clientBean.getEmail());
		
		System.out.println("\n\n##### FIND BY EMAIL \n\n" + clientBean.toString());
		
		magasin.getClientService().delete(magasin.getConnexion(), clientBean);
		magasin.getProfilService().delete(magasin.getConnexion(), clientBean.getProfilBean());
		
		System.out.println("\n\n[][][] DELETED \n\n");
	}
}
