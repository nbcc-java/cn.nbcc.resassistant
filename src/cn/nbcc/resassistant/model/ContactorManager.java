package cn.nbcc.resassistant.model;

import java.util.*;

public class ContactorManager {

	private static final ContactorManager INSTANCE = new ContactorManager();
	private Collection<Contactor> contactors;
	private ContactorManager() {
	}
	public static ContactorManager getInstance() {
		return INSTANCE;
	}
	public Contactor[] getContactors(){
		if (contactors==null) {
			loadContactors();
		}
		return (Contactor[])contactors.toArray(new Contactor[contactors.size()]);
	}

	private void loadContactors() {
		contactors = new HashSet<Contactor>();
		contactors.add(new Contactor("001", "ÐíÓ×Ã÷", "13967803534", "xuyouming@nbcc.cn",true));
		contactors.add(new Contactor("002", "½¯ÇÙ", "13967803534", "jiangqing@nbcc.cn"));
	}

	public Contactor getDefaultContactor(){
		for (Contactor contactor : contactors) {
			if (contactor.isDefaultContactor()) {
				return contactor;
			}
		}
		return null;
	}
}
