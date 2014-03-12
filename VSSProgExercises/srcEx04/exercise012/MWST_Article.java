package exercise012;

import java.io.Serializable;
import java.math.BigDecimal;

public class MWST_Article implements Serializable {
	private static final long serialVersionUID = 4416655274987471424L;
	String name;
	BigDecimal value;

	public MWST_Article(String aName, BigDecimal aValue) {
		super();
		name = aName;
		value = aValue;
	}
}
