public class SegmentTreesPractice {

    //sum segment tree
    public static class SegmentTreeSum{
        private int[] tree;
        private int[] nums;
        private int n;

        SegmentTreeSum(int[] nums) {
            n = nums.length;
            this.nums = nums;
            // Edge case: Handle empty array to prevent crash
            if (n > 0) {
                tree = new int[4 * n];
                buildST(nums, 0, 0, n - 1);
            }
        }

        private void buildST(int[] nums, int idx, int startIdx, int endIdx) {
            if(startIdx == endIdx) {
                tree[idx] = nums[startIdx];
                return;
            }
            int mid = startIdx + (endIdx - startIdx) / 2; //Prevents overflow
            buildST(nums, (idx*2)+1, startIdx, mid);
            buildST(nums, (idx*2)+2, mid+1, endIdx);
            tree[idx] = tree[(idx*2)+1] + tree[(idx*2)+2];
        }

        private int getSumRange(int idx, int startIdx, int endIdx, int queryStartIdx, int queryEndIdx) {
            //No Overlap
            if(queryEndIdx < startIdx || endIdx < queryStartIdx) {
                return 0;
            } 
            //Total Overlap
            else if(queryStartIdx <= startIdx && endIdx <= queryEndIdx) {
                return tree[idx];
            } 
            //Partial Overlap
            else {
                int mid = startIdx + (endIdx - startIdx) / 2;
                int leftSum = getSumRange((idx*2)+1, startIdx, mid, queryStartIdx, queryEndIdx);
                int rightSum = getSumRange((idx*2)+2, mid+1, endIdx, queryStartIdx, queryEndIdx);
                return leftSum + rightSum; 
            }
        }

        public int getSum(int start, int end) {
            if(n == 0) return 0;
            if(start < 0 || end >= n || start > end) {
                System.out.println("invalid range");
                return Integer.MIN_VALUE;
            };
            return getSumRange(0, 0, n-1, start, end);
        }

        private void setValue(int idx, int startIdx, int endIdx, int i, int newVal) {
            if(startIdx == endIdx) {
                tree[idx] = newVal;
                return;
            }

            int mid = startIdx + (endIdx - startIdx) / 2;
            if(i <= mid) {
                setValue((idx*2)+1, startIdx, mid, i, newVal);
            } else {
                setValue((idx*2)+2, mid+1, endIdx, i, newVal);
            }

            //update
            tree[idx] = tree[(idx*2)+1] + tree[(idx*2)+2];
        }

        public void update(int idx, int val) {
            if (n == 0 || idx < 0 || idx >= n) return;
            nums[idx] = val;
            setValue(0, 0, n-1, idx, val);
        }

        public void printTree() {
            for(int i : tree) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        public void printArray() {
            for(int i : nums) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
       
    //min segment tree
    public static class SegmentTreeMin{
        private int[] tree;
        private int[] nums;
        private int n;

        SegmentTreeMin(int[] nums) {
            n = nums.length;
            this.nums = nums;
            // Edge case: Handle empty array to prevent crash
            if (n > 0) {
                tree = new int[4 * n];
                buildST(nums, 0, 0, n - 1);
            }
        }

        private void buildST(int[] nums, int idx, int startIdx, int endIdx) {
            if(startIdx == endIdx) {
                tree[idx] = nums[startIdx];
                return;
            }
            int mid = startIdx + (endIdx - startIdx) / 2; //Prevents overflow
            buildST(nums, (idx*2)+1, startIdx, mid);
            buildST(nums, (idx*2)+2, mid+1, endIdx);
            tree[idx] = Math.min(tree[(idx*2)+1] , tree[(idx*2)+2]);
        }

        private int getMinRange(int idx, int startIdx, int endIdx, int queryStartIdx, int queryEndIdx) {
            //No Overlap
            if(queryEndIdx < startIdx || endIdx < queryStartIdx) {
                return Integer.MAX_VALUE;
            } 
            //Total Overlap
            else if(queryStartIdx <= startIdx && endIdx <= queryEndIdx) {
                return tree[idx];
            } 
            //Partial Overlap
            else {
                int mid = startIdx + (endIdx - startIdx) / 2;
                int leftSum = getMinRange((idx*2)+1, startIdx, mid, queryStartIdx, queryEndIdx);
                int rightSum = getMinRange((idx*2)+2, mid+1, endIdx, queryStartIdx, queryEndIdx);
                return  Math.min(leftSum, rightSum);
            }
        }

        public int getMin(int start, int end) {
            if(n == 0) return 0;
            if(start < 0 || end >= n || start > end) {
                System.out.println("Invalid range");
                return Integer.MAX_VALUE;
            }
            return getMinRange(0, 0, n-1, start, end);
        }

        private void setValue(int idx, int startIdx, int endIdx, int i, int newVal) {
            if(startIdx == endIdx) {
                tree[idx] = newVal;
                return;
            }

            int mid = startIdx + (endIdx - startIdx) / 2;
            if(i <= mid) {
                setValue((idx*2)+1, startIdx, mid, i, newVal);
            } else {
                setValue((idx*2)+2, mid+1, endIdx, i, newVal);
            }

            //update
            tree[idx] = Math.min(tree[(idx*2)+1] , tree[(idx*2)+2]);
        }

        public void update(int idx, int val) {
            if (n == 0 || idx < 0 || idx >= n) return;
            nums[idx] = val;
            setValue(0, 0, n-1, idx, val);
        }

        public void printTree() {
            for(int i : tree) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        public void printArray() {
            for(int i : nums) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
     

    public static void main(String[] args) {
        // int[] arr = {1,2,3,4,5,6,7,8};
        // SegmentTreeSum st = new SegmentTreeSum(arr);
        // st.printTree();
        // st.printArray();
        // System.out.println(st.getSum(2,5));
        // st.update(2, 2);
        // st.printTree();
        // st.printArray();
        // System.out.println(st.getSum(2,5));

        // int[] arr1 = {6,8,-1,2,17,1,3,2,4};
        // SegmentTreeMin minst = new SegmentTreeMin(arr1);
        // minst.printTree();
        // System.out.println(minst.getMin(1, 7));
        // System.out.println(minst.getMin(3, 4));
        // minst.update(2, 20);
        // minst.printTree();
        // minst.printArray();
        // System.out.println(minst.getMin(1, 7));
        // System.out.println(minst.getMin(1, 3));

        int[] arr = {1,2,3,4,5,6};

        SegmentTreeSum st = new SegmentTreeSum(arr);
        st.printTree();
        
        
    }
}
