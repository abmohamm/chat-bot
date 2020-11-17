package com.app.samples.chatbot.chatbotapp.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Chatbot.
 */
public class Chatbot {
	
	/** The customer text. */
	private String customerText;
	
	/** The representative text. */
	private String representativeText;
	
	/** The result text. */
	private String resultText;
	
	/**
	 * Gets the result text.
	 *
	 * @return the result text
	 */
	public String getResultText() {
		return resultText;
	}

	/**
	 * Sets the result text.
	 *
	 * @param resultText the new result text
	 */
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	/**
	 * Instantiates a new chatbot.
	 */
	public Chatbot() {
		super();
	}
	
	/**
	 * Instantiates a new chatbot.
	 *
	 * @param customerText the customer text
	 * @param representativeText the representative text
	 */
	public Chatbot(String customerText, String representativeText) {
		super();
		this.customerText = customerText;
		this.representativeText = representativeText;
	}
	
	/**
	 * Gets the customer text.
	 *
	 * @return the customer text
	 */
	public String getCustomerText() {
		return customerText;
	}

	/**
	 * Sets the customer text.
	 *
	 * @param customerText the new customer text
	 */
	public void setCustomerText(String customerText) {
		this.customerText = customerText;
	}

	/**
	 * Gets the representative text.
	 *
	 * @return the representative text
	 */
	public String getRepresentativeText() {
		return representativeText;
	}

	/**
	 * Sets the representative text.
	 *
	 * @param representativeText the new representative text
	 */
	public void setRepresentativeText(String representativeText) {
		this.representativeText = representativeText;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerText == null) ? 0 : customerText.hashCode());
		result = prime * result + ((representativeText == null) ? 0 : representativeText.hashCode());
		result = prime * result + ((resultText == null) ? 0 : resultText.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chatbot other = (Chatbot) obj;
		if (customerText == null) {
			if (other.customerText != null)
				return false;
		} else if (!customerText.equals(other.customerText))
			return false;
		if (representativeText == null) {
			if (other.representativeText != null)
				return false;
		} else if (!representativeText.equals(other.representativeText))
			return false;
		if (resultText == null) {
			if (other.resultText != null)
				return false;
		} else if (!resultText.equals(other.resultText))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Chatbot [customerText=" + customerText + ", representativeText=" + representativeText + ", resultText="
				+ resultText + "]";
	}
}
