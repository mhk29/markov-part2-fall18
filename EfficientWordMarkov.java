import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

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
		
		for (int a = 0; a < text.length() - getOrder() + 1; a++) {
			
			WordGram trainer = new WordGram(myText, a, getOrder());
			ArrayList<String> theOne = new ArrayList<String>();
			String theAdd;
			
		
			
			if (a == myText.length - getOrder()) {
				theAdd = PSEUDO_EOS;
			}
			else {
				theAdd = myText[a + getOrder()];
			}
			
			if (! myMap.containsKey(trainer)) {	
				myMap.put(trainer, new ArrayList<String>());
			}
			
			theOne = myMap.get(trainer);
			theOne.add(theAdd);
			myMap.put(trainer, theOne);
		}
//		WordGram addsLast = new WordGram(myText, text.length() - getOrder(), getOrder());
//		if (! myMap.containsKey(addLast)) {	
//			myMap.put(addLast, new ArrayList<String>());
//		}		
//		ArrayList<String> meIn = myMap.get(addLast);
//		meIn.add(PSEUDO_EOS);
//		myMap.put(addLast, meIn);

		
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key) {
		if (! myMap.containsKey(key)) {
			throw new NoSuchElementException (key+" not in map");
		}
		return myMap.get(key);
	}
}