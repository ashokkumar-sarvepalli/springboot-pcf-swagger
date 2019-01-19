package playground.altimetrik.transactionstats.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {
	
	
	public static ZonedDateTime convertStringToZoneDateTime(String value) {
		Instant instant = Instant.parse(value);
		ZonedDateTime zoneDateTime = instant.atZone(ZoneId.of("UTC"));
		return zoneDateTime;
		
	}
	
	
	

}
