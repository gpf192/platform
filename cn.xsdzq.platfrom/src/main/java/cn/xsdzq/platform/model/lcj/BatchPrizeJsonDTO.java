package cn.xsdzq.platform.model.lcj;

public class BatchPrizeJsonDTO {
	private String batchPrizeJson;

	public String getBatchPrizeJson() {
		return batchPrizeJson;
	}

	public void setBatchPrizeJson(String batchPrizeJson) {
		this.batchPrizeJson = batchPrizeJson;
	}

	public BatchPrizeJsonDTO(String batchPrizeJson) {
		super();
		this.batchPrizeJson = batchPrizeJson;
	}

	public BatchPrizeJsonDTO() {
		super();
	}

	@Override
	public String toString() {
		return "BatchPrizeJsonDTO [batchPrizeJson=" + batchPrizeJson + "]";
	}
	
}
