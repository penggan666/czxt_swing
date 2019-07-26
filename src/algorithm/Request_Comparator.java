package algorithm;

import java.util.Comparator;

import models.Request_;

public class Request_Comparator {
	public static Comparator<Request_> comparator=new Comparator<Request_>() {

		@Override
		public int compare(Request_ o1, Request_ o2) {
			return o1.getPc()-o2.getPc();
		}
	};
}
