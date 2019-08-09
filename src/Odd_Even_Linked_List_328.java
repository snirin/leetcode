public class Odd_Even_Linked_List_328 {

    public static void main(String[] args) {
        check(new int[]{1, 2, 3, 4, 5, 6});
//        check(new int[]{1, 2}, 1);
    }

    static void check(int[] nums) {
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

        ListNode res = new Solution().oddEvenList(head);

//        if (expected != result) {
//            System.out.println("" + prices + "        " + expected + "          " + result);
//        }
    }

    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode oddsHead = head;
            ListNode oddsTail = head;
            ListNode evensHead = head.next;
            ListNode evensTail = head.next;

            ListNode node = head.next.next;
            boolean isOdd = true;
            while (node != null) {
                ListNode nextNode = node.next;

                if (isOdd) {
                    oddsTail.next = node;
                    oddsTail = node;
                } else {
                    evensTail.next = node;
                    evensTail = node;
                }

                node = nextNode;
                isOdd = !isOdd;
            }

            oddsTail.next = evensHead;
            return oddsHead;
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

