package com.corporation.corInfo.domain;

import com.corporation.manager.domain.corporation_information;

public class CorInfoDTO {
	private corporation_information corInfo;

	public CorInfoDTO(corporation_information corInfo) {

		this.corInfo = corInfo;
	}

	public corporation_information getCorInfo() {
		return corInfo;
	}

	public void setCorInfo(corporation_information corInfo) {
		this.corInfo = corInfo;
	}

	@Override
	public String toString() {
		return "LinkDTO [corInfo=" + corInfo + "]";
	}

}
