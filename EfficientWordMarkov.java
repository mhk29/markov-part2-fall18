import java.util.ArrayList;
import java.util.HashMap;

public class EfficientWordMarkov extends BaseWordMarkov {
	
	private HashMap<WordGram, ArrayList<String>> myMap = new HashMap<WordGram, ArrayList<String>>();
	
	public EfficientWordMarkov(int order) {
		super(order);
	}
	
	public EfficientWordMarkov() {
		this(3);
	}

	@Override
	public void setTraining(String text) {
		String[] myText = text.split("\\s");
		myMap = new HashMap<WordGram, ArrayList<String>>();
		
		for (int a = 1; a < text.length() - getOrder() - 1; a++) {
			
			WordGram trainer = new WordGram(myText, a, getOrder());
			ArrayList<String> theOne = new ArrayList<String>();
			String theAdd;
			
			if (! myMap.containsKey(trainer)) {	
				myMap.put(trainer, new ArrayList<String>());
			}		
			theOne = myMap.get(trainer);
			theAdd = myText[a + getOrder()];
			theOne.add(theAdd);
			myMap.put(trainer, theOne);
		}
		WordGram addLast = new WordGram(myText, text.length() - getOrder(), getOrder());
		if (! myMap.containsKey(addLast)) {	
			myMap.put(addLast, new ArrayList<String>());
		}		
		ArrayList<String> meIn = myMap.get(addLast);
		meIn.add(PSEUDO_EOS);
		myMap.put(addLast, meIn);

		
	}
	
}