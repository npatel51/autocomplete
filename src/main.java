public class main {
    public static void main(String[] args){
        String[] sentences = {"a","abc","abbc"};
        int[] frequency = {3,4,2};
        AutoComplete ac = new AutoComplete(sentences,frequency);
        System.out.println(ac.input('a'));
    }
}
