class Sample{
    //TC:0(N*L)
    //SC:0(N*L)
    class Solution {
        class TrieNode{
            TrieNode[] children;
            List<String> startsWith;
            public TrieNode(){
                this.children=new TrieNode[26];
                this.startsWith=new ArrayList<>();
            }

        }
        TrieNode root;
        List<List<String>> result;
        private void insert(String word){
            TrieNode curr=root;
            for(char each:word.toCharArray()){
                if(curr.children[each-'a']==null){
                    curr.children[each-'a']=new TrieNode();
                }
                curr=curr.children[each-'a'];
                curr.startsWith.add(word);
            }
        }

        private List<String> starts(String prefix){
            TrieNode curr=root;
            for(char each:prefix.toCharArray()){
                if(curr.children[each-'a']!=null){
                    curr=curr.children[each-'a'];
                }else{
                    return new ArrayList<>();
                }
            }
            return curr.startsWith;
        }

        public List<List<String>> wordSquares(String[] words) {
            if(words==null || words.length==0){
                return new ArrayList<>();
            }
            root=new TrieNode();
            result=new ArrayList<>();

            for(String each:words){
                insert(each);
            }

            List<String> li=new ArrayList<>();
            for(String word:words){
                //action
                li.add(word);
                //recurse
                backtrack(li);
                //backtrack
                li.remove(li.size()-1);
            }
            return result;
        }

        private void backtrack(List<String> li){
            //base
            if(li.size()==li.get(0).length()){
                result.add(new ArrayList<>(li));
                return;
            }

            //logic
            StringBuilder sb= new StringBuilder();
            int index=li.size();
            for(String word:li){
                sb.append(word.charAt(index));
            }
            List<String> words=starts(sb.toString());
            for(String word:words){
                //action
                li.add(word);
                //recurse
                backtrack(li);
                //backtrack
                li.remove(li.size()-1);
            }
        }
    }
}