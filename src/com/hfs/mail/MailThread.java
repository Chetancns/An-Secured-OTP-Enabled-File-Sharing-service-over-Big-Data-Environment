package com.hfs.mail;

import java.util.List;

public class MailThread implements Runnable {

	Thread t;
	String body;
	String sub;
	List<String> rcv;

	public MailThread(String body, String sub, List<String> rcv) {
		this.body = body;
		this.sub = sub;
		this.rcv = rcv;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {

		SendMail sm = new SendMail();
		sm.send(rcv, sub, body);

	}

}
