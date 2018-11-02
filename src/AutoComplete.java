import java.util.ArrayList;
import java.util.List;

class AutoComplete {
    Trie root;
    StringBuilder sb;

    public AutoComplete(String[] sentences, int[] times) {
        root = new Trie();
        sb = new StringBuilder();
        // add words to trie
        for (int i = 0; i < times.length; ++i) {
            root.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {  // reset -- end of the sentence
            root.insert(sb.toString(), 1);
            sb.setLength(0);
            return res;
        }
        sb.append(c);
        String s = sb.toString();
        return root.topK(s, 3); // top 3 frequent sentences
    }
}
