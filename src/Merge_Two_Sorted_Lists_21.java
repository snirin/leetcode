public class Merge_Two_Sorted_Lists_21 {

    public static void main(String[] args) {
        check(new int[]{1, 2, 4}, new int[]{1, 3, 4});
//        check(new int[]{1, 2}, 1);
    }

    static void check(int[] nums1, int[] nums2) {
        ListNode res = new Solution().mergeTwoLists(getListNode(nums1), getListNode(nums2));

//        if (expected != result) {
//            System.out.println("" + prices + "        " + expected + "          " + result);
//        }
    }

    private static ListNode getListNode(int[] nums) {
        ListNode head = null;
        ListNode node = null;
        for (int num : nums) {
            ListNode newNode = new ListNode(num);
            if (head == null) {
                head = newNode;
            } else {
                node.next = newNode;
            }
            node = newNode;
        }
        return head;
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode head = l1;
            ListNode node = l1;
            boolean isOdd = true;
            while (l1 != null || l2 != null) {

                ListNode l = isOdd ? l1 : l2;

                if (l != null) {
                    ListNode next = l.next;
//                    if ()
                }

//                ListNode nextL1 = null;
//                if (l1 != null) {
//                    nextL1 = l1.next;
//                }

//                if (l2 != null) {
//                    node.next = l2;
//                    l2 = l2.next;
//                    node = node.next;
//                }
//
//                if (l1 != null) {
//                    node.next = l1;
//                    l1 = l1.next;
//                    node = node.next;
//                }
            }

            return head;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

