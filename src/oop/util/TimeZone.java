package oop.util;

public class TimeZone {
	
	private String[] timezones = {	"(GMT -11:00) Midway Island, Samoa",
											"(GMT -10:00) Hawaii",
											"(GMT -9:00) Alaska",
											"(GMT -8:00) Pacific Time (US and Canada)",
											"(GMT -7:00) Mountain Time (US and Canada)",
											"(GMT -6:00) Central Time (US and Canada), Mexico City",					
											"(GMT -5:00) Eastern Time (US and Canada), Bogota, Lima",
											"(GMT -4:30) Caracas",
											"(GMT -4:00) Atlantic Time (Canada), La Paz, Santiago",
											"(GMT -3:30) Newfoundland",
											"(GMT -3:00) Brazil, Buenos Aires, Georgetown",
											"(GMT -2:00) Mid-Atlantic",
											"(GMT -1:00) Azores, Cape Verde Islands",
											"(GMT) Western Europe Time, London, Lisbon, Casablanca",										
											"(GMT +1:00) Brussels, Copenhagen, Madrid, Paris",
											"(GMT +2:00) Kaliningrad, South Africa",
											"(GMT +3:00) Baghdad, Riyadh, Moscow, St. Petersburg",
											"(GMT +3:30) Tehran",
											"(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi",
											"(GMT +4:30) Kabul",
											"(GMT +5:00) Ekaterinburg, Islamabad, Karachi, Tashkent",
											"(GMT +5:30) Mumbai, Kolkata, Chennai, New Delhi",
											"(GMT +5:45) Kathmandu",										
											"(GMT +6:00) Almaty, Dhaka, Colombo",
											"(GMT +6:30) Yangon, Cocos Islands",
											"(GMT +7:00) Bangkok, Hanoi, Jakarta",
											"(GMT +8:00) Beijing, Perth, Singapore, Hong Kong",
											"(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk",
											"(GMT +9:30) Adelaide, Darwin",
											"(GMT +10:00) Eastern Australia, Guam, Vladivostok",
											"(GMT +11:00) Magadan, Solomon Islands, New Caledonia",
											"(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka"
										};
	private String[] shortTimezones = {	"-11:00",
												"-10:00",
												"-9:00",
												"-8:00",
												"-7:00",
												"-6:00",					
												"-5:00",
												"-4:30",
												"-4:00",
												"-3:30",
												"-3:00",
												"-2:00",
												"-1:00",
												"+0:00",										
												"+1:00",
												"+2:00",
												"+3:00",
												"+3:30",
												"+4:00",
												"+4:30",
												"+5:00",
												"+5:30",
												"+5:45",										
												"+6:00",
												"+6:30",
												"+7:00",
												"+8:00",
												"+9:00",
												"+9:30",
												"+10:00",
												"+11:00",
												"+12:00"
	};
	private int size = timezones.length;
	public static int defaultTimezone = 15;
	private int currentTimezone;
	
	public TimeZone(int currentTimezone){
		this.currentTimezone = currentTimezone;
	}
	public TimeZone(String shortTimezone){
		this.currentTimezone = getOrdinal(shortTimezone);
	}
	public int getCurrentTimezone() {
		return currentTimezone;
	}
	public String getShortTimezone(){
		return shortTimezones[currentTimezone];
	}
	public String getTimezone(){
		return timezones[currentTimezone];
	}
    
	public int getSize() {
		return size;
	}
	public String[] getTimezones() {
		return timezones;
	}
	public String[] getShortTimezones() {
		return shortTimezones;
	}

	
	public String getByOrdinal(int ordinal){
		if (ordinal<shortTimezones.length){
			return timezones[ordinal];
		}else{
			return timezones[defaultTimezone];
		}
	}

	public int getOrdinal(String sT){
		for (int i=0; i<shortTimezones.length; i++){
			if (shortTimezones[i].compareTo(sT)==0){
				return i;
			}
		}
		return defaultTimezone;
	}
	public String getByShortenTimezone(String sT){
		for (int i=0; i<shortTimezones.length; i++){
			if (shortTimezones[i].compareTo(sT)==0){
				return timezones[i];
			}
		}
		return timezones[defaultTimezone];
	}

}
