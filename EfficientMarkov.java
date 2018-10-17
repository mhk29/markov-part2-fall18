import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov {
	// myText is used for text generation and 
	// base Markov chooses a random starting
	
	private HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
	
	public EfficientMarkov(int order) {
		super(order);
	}
	
	public EfficientMarkov() {
		this(3);
	}
	
	
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap = new HashMap<String, ArrayList<String>>();
		
		for (int a = 0; a < text.length() - getOrder() - 1; a++) {
			
			String trainer = myText.substring(a, a + getOrder());
			ArrayList<String> theOne = new ArrayList<String>();
			String theAdd;
//			
//			if (! myMap.containsKey(trainer)) {	
//				myMap.put(trainer, new ArrayList<String>());
//			}		
			if (a == myText.length() - getOrder()) {
				
			}
			theOne = myMap.get(trainer);
			theAdd = myText.substring(a + getOrder(), a + getOrder() + 1);
			theOne.add(theAdd);
			myMap.put(trainer, theOne);
		}
		String addLast = myText.substring(myText.length() - getOrder() - 1, myText.length() - 1);
		if (! myMap.containsKey(addLast)) {	
			ArrayList<String> meIn = new ArrayList<String>();
			meIn.add(PSEUDO_EOS);
			myMap.put(addLast, meIn);
		}		
		if (myMap.containsKey(addLast)) {
			myMap.get(addLast).add(PSEUDO_EOS);
		}
	}

	
	@Override
	public ArrayList<String> getFollows(String key){ 
		if (! myMap.containsKey(key)) {
			throw new NoSuchElementException (key+" not in map");
		}
		return myMap.get(key);
	}
	
}
