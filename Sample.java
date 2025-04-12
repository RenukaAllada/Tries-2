class Sample{
    /********************PROBLEM-1******************/
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

    /********************PROBLEM-2******************/
    //TC:0(N*L)
    //SC:0(1)
    class Solution {
        public List<Boolean> camelMatch(String[] queries, String pattern) {
            if(queries.length==0 || queries==null ){
                return new ArrayList<>();
            }
            List<Boolean> result=new ArrayList<>();


            for(String query:queries){
                int i=0; //pattern pointer
                boolean flag=true;
                for(int j=0;j<query.length();j++){
                    char c=query.charAt(j);
                    if(i<pattern.length() && (c==pattern.charAt(i))){
                        i++;
                    }
                    else if(Character.isUpperCase(c)){
                        flag=false;
                        break;
                    }
                }
                if(flag==true && i==pattern.length()){
                    result.add(true);
                }else{
                    result.add(false);
                }
            }
            return result;
        }
    }

    /********************PROBLEM-3*****************/
    //TC:0(NLOGK)
//SC:0(N)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            if(nums==null ||nums.length==0){
                return new int[]{};
            }
            HashMap<Integer,Integer> count=new HashMap<>();
            int[] result=new int[k];
            for(int i=0;i<nums.length;i++){
                count.put(nums[i],count.getOrDefault(nums[i],0)+1);
            }
            PriorityQueue<Integer> heap=new PriorityQueue<>((a,b)->count.get(a)-count.get(b));
            for(int key:count.keySet()){
                heap.add(key);
                if(heap.size()>k){
                    heap.poll();
                }
            }

            for(int i=0;i<k;i++){
                result[i]=heap.poll();
            }
            return result;
        }
    }
}