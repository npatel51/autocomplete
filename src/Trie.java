import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Map;

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String s, int freq) {
        TrieNode temp = root;
        for (int i = 0; i < s.length(); ++i) {
            if (!temp.hasChild(s.charAt(i))) {
                temp.setChild(s.charAt(i));
            }
            temp = temp.getChild(s.charAt(i));
        }
        temp.setEnd(temp.getFreq() + freq);
    }

    public List<String> top3(String s) { // prefix
        List<String> res = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );
        TrieNode temp = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!temp.hasChild(s.charAt(i))) return res;
            sb.append(s.charAt(i));
            temp = temp.getChild(s.charAt(i));
        }
        dfs(temp, pq, sb);
        int size = pq.size() - 1;
        for (int i = 0; i <= size; ++i)
            res.add("");
        while (!pq.isEmpty()) {
            res.set(size, pq.poll().getKey());
            size--;
        }
        return res;
    }

    public void dfs(TrieNode node, PriorityQueue<Map.Entry<String, Integer>> pq, StringBuilder sb) {
        if (node == null) return;
        if (node.isEndOfTheWord()) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put(sb.toString(), node.getFreq());
            pq.add(map.entrySet().iterator().next());
            if (pq.size() > 3) {
                pq.poll();
            }
        }
        for (char ch = 0; ch < 128; ++ch) {
            if (node.hasChild(ch)) {
                sb.append(ch);
                dfs(node.getChild(ch), pq, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }
}