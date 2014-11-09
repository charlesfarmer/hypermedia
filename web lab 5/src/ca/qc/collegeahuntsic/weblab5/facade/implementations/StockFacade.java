package ca.qc.collegeahuntsic.weblab5.facade.implementations;

import ca.qc.collegeahuntsic.weblab5.facade.interfaces.IStockFacade;
import ca.qc.collegeahuntsic.weblab5.service.interfaces.IStockService;

public class StockFacade extends Facade implements IStockFacade {

	private IStockService service;

	public StockFacade(IStockService service) {
		setService(service);
	}

	private IStockService getService() {
		return service;
	}

	private void setService(IStockService service) {
		this.service = service;
	}
}
