package com.emids.hipqg.service;

import com.emids.hipqg.model.CustomerModel;

public interface QuoteService {
	public Double calculateQuote(CustomerModel customerModel);
}
