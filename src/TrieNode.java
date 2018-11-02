import java.util.Map;
import java.util.HashMap;

class TrieNode {
    private Map<Character, TrieNode> child = new HashMap<>();
    private boolean isEnd; // end of the word
    private int freq; // times it occurred in the data

    public void setChild(char c) {
        child.put(c, new TrieNode());
    }

    public TrieNode getChild(char c) {
        return child.getOrDefault(c, null);
    }

    public void setEnd(int freq) {
        this.freq = freq;
        this.isEnd = true;
    }

    public boolean hasChild(char c) {
        return child.containsKey(c);
    }

    public boolean isEndOfTheWord() {
        return isEnd;
    }

    public int getFreq() {
        return freq;
    }
}