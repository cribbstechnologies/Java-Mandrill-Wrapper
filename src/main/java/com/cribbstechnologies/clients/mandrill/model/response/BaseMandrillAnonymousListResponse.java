package com.cribbstechnologies.clients.mandrill.model.response;

import java.util.List;

public class BaseMandrillAnonymousListResponse<T> extends BaseMandrillResponse {
	
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
