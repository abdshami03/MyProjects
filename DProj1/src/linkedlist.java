import java.time.LocalDate;
import java.util.Date;

public class linkedlist<T extends Comparable<T>> {
	year<T> yearlinkedlist;

	public linkedlist() {
		this.yearlinkedlist = new year<>();
		this.yearlinkedlist.setNext(this.yearlinkedlist);
	}

	private year findyear(int year) {
		if (yearlinkedlist == null) {
			yearlinkedlist = new year(year);
			yearlinkedlist.setNext(yearlinkedlist);
			return yearlinkedlist;
		}

		year curr = yearlinkedlist;
		while (curr.getNext() != yearlinkedlist) {
			if (curr.getY() == year) {
				return curr;
			}
			curr = curr.getNext();
		}

		year newyear = new year(year);
		newyear.setNext(yearlinkedlist.getNext());
		yearlinkedlist.setNext(newyear);
		return newyear;
	}

	private year findPrevYear(year head, int year) {
		year current = head;

		while (current != null && current.getNext() != head) {
			year nextYear = current.getNext();
			if (nextYear.getY() == year) {
				return current;
			}
			current = nextYear;
		}

		return null;
	}

	private month findmonth(year year, int month) {
		month mnode = year.getMonth();
		if (mnode == null) {
			mnode = new month(month);
			mnode.setNext(mnode);
			year.setMonth(mnode);
			return mnode;
		}
		month curr = mnode;
		while (curr.getNext() != mnode) {
			if (curr.getM() == month) {
				return curr;
			}
			curr = curr.getNext();
		}
		month newmonth = new month(month);
		newmonth.setNext(mnode.getNext());
		mnode.setNext(newmonth);
		if (month == 1) {
			year.setMonth(newmonth);
		}
		return newmonth;
	}
	 private Records findDay(month mnode, int day) {
	        day curr = mnode.getDay();
	        while (curr != null) {
	            if (curr.getD() == day) {
	                return curr.getR();
	            }
	            curr = curr.getNext();
	        }

	        return null;
	    }

	private void insertDay(month monthNode, int day, Records data) {
		day newday = new day();
		newday.setR(data);

		day curr = monthNode.getDay();
		day prev = null;

		while (curr != null && curr.getD() < day) {
			prev = curr;
			curr = curr.getNext();
		}

		if (prev == null || prev.getD() > day) {
			newday.setNext(monthNode.getDay());
			monthNode.setDay(newday);
		} else if (prev.getD() == day) {
			prev.setR(data);
		} else {
			prev.setNext(newday);
			newday.setNext(curr);
		}
	}

	private boolean deleteDay(month mnode, int day, year ynode) {
		day curr = mnode.getDay();
		day prev = null;

		while (curr != null && curr.getD() != day) {
			prev = curr;
			curr = curr.getNext();
		}

		if (curr == null) {
			return false;
		}
		if (prev != null) {
			prev.setNext(curr.getNext());
		} else {
			mnode.setDay(curr.getNext());
			if (curr.getNext() == null) {
				if (mnode.getNext() == mnode) {
					if (yearlinkedlist.getNext() == yearlinkedlist) {
						yearlinkedlist = null;
					} else {
						year prevYear = findPrevYear(yearlinkedlist, ynode.getY());
						if (prevYear != null) {
							prevYear.setNext(ynode.getNext());
							if (yearlinkedlist == ynode) {
								yearlinkedlist = ynode.getNext();
							}
						}
					}
				}
			}
		}
		return true;
	}

	public void insert(Records record) {
		LocalDate date = ((Records) record).getDate();
		int yvalue = date.getYear();
		int mvalue = date.getMonthValue();
		int dvalue = date.getDayOfMonth();

		year ynode = findyear(yvalue);
		month mnode = findmonth(ynode, mvalue);
		insertDay(mnode, dvalue, record);
	}

	public boolean delete(LocalDate date) {
		int yvalue = date.getYear();
		int mvalue = date.getMonthValue();
		int dvalue = date.getDayOfMonth();

		year<T> yearNode = findyear(yvalue);
		if (yearNode == null) {
			return false;
		}

		month<T> monthNode = findmonth(yearNode, mvalue);
		if (monthNode == null) {
			return false;
		}

		return deleteDay(monthNode, dvalue);
	}

	private boolean deleteDay(month mnode, int day) {
		day<Records> curr = mnode.getDay();
		day<Records> prev = null;

		while (curr != null && curr.getR().getDate().getDayOfMonth() != day) {
			prev = curr;
			curr = curr.getNext();
		}

		if (curr == null) {
			return false;
		}

		if (prev != null) {
			prev.setNext(curr.getNext());
		} else {
			mnode.setDay(curr.getNext());
		}

		return true;
	}

	
}
