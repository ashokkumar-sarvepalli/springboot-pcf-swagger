package playground.altimetrik.transactionstats.enumerator;

public enum StatisticsTypeEnum {
	
	ALL("All"),MIN("Min"),MAX("Max"),AVG("Avg"),SUM("sum");
	
	private StatisticsTypeEnum(String value){
		this.value = value;
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
