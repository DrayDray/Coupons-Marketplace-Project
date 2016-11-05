package com.sharon.couponsystem.utils;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

// The class extends XmlAdpter which has 2 methods, marshal and unmarshal
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
	
	// marshal method receive sql.timestamp and transfer it into java.util.Date
	// which have default ctor and can be used with jackson
	@Override
	public Date marshal(Timestamp v) {
		return new Date(v.getTime());
	}
	
	// unmarshal do the exact opposite from marshal method
	// it takes java.util.date and turns it into sql.timestamp
	// which can be used by our database
	@Override
	public Timestamp unmarshal(Date v) {
		return new Timestamp(v.getTime());
	}
}
