import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientWordMarkov extends BaseWordMarkov {
	
	private HashMap<WordGram, ArrayList<String>> myMap = new HashMap<WordGram, ArrayList<String>>();
	
	public EfficientWordMarkov(int order) {
		super(order);
	}
	
	public EfficientWordMarkov() {
		this(2);
		myRandom = new Random(RANDOM_SEED);
	}

	@Override
	public void setTraining(String text) {
		myWords = text.split("\\s+");
		myMap = new HashMap<WordGram, ArrayList<String>>();
		
		for (int a = 0; a < myWords.length - getOrder() + 1; a++) {
			
			WordGram trainer = new WordGram(myWords, a, getOrder());
			ArrayList<String> theOne = new ArrayList<String>();
			String theAdd;
			
		
			
			if (a == myWords.length - getOrder()) {
				theAdd = PSEUDO_EOS;
			}
			else {
				theAdd = myWords[a + getOrder()];
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