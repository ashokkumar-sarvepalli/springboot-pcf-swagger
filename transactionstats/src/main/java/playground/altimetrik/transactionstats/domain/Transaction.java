package playground.altimetrik.transactionstats.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class Transaction {
	
	private UUID uuid;
	
	
	@ApiModelProperty(value="The amount of the transaction")
	private double amount;
	
	
	@ApiModelProperty(value="The TimeStamp of the transaction in yyyy-MM-ddTHH:mm:ss.SSSZ format , example value = 2019-01-19T18:20:02.225Z")
	private String time;
	
	
	
	private ZonedDateTime offsetTime;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@JsonIgnore
	public ZonedDateTime getZonedDateTime() {
		return offsetTime;
	}

	public void setZonedDateTime(ZonedDateTime offsetTime) {
		this.offsetTime = offsetTime;
	}
	
	
	

}
