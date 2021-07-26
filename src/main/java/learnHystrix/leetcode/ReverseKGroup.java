package learnHystrix.leetcode;

public class ReverseKGroup {

    /*  给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
        k是一个正整数，它的值小于或等于链表的长度。
        如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
            nice to have：
                1. 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
                2. 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode sample() {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        return new ListNode(1, n2);
    }

    public static void printList(ListNode head) {
        ListNode index = head;
        System.out.print(index.val);
        while (index.next != null) {
            index = index.next;
            System.out.print(index.val);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        printList(sample());
        // 12345
        ReverseKGroup obj = new ReverseKGroup();
        // test case 1:
        // 12345
        // 21435
        // 32145
        // 43215
        System.out.println("test case 1");
        printList(obj.reverseKGroup(sample(), 1));
        printList(obj.reverseKGroup(sample(), 2));
        printList(obj.reverseKGroup(sample(), 3));
        printList(obj.reverseKGroup(sample(), 4));
        // test case 2:
        // 12345
        // 21435
        // 41235
        // 32145
        System.out.println("test case 2");
        ListNode round1 = obj.reverseKGroup(sample(), 1);
        printList(round1);
        ListNode round2 = obj.reverseKGroup(round1, 2);
        printList(round2);
        ListNode round3 = obj.reverseKGroup(round2, 3);
        printList(round3);
        ListNode round4 = obj.reverseKGroup(round3, 4);
        printList(round4);
    }

    /*
     * 12345, 2
     * 21 43 5
     * len -> 5
     * group_size -> 2
     * group_len -> 2
     * reverse_group(5, false, next -> null): 5
     * reverse_group(34, true, next -> 5): 4
     * reverse_group(12, true, next -> 4): 2
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        int listLen = 0;
        // compute groupLen
        ListNode cursor = head;
        while (cursor.next != null) {
            listLen += 1;
            cursor = cursor.next;
        }
        int groupSize = listLen / k + 1;
        System.out.print("groupSize:" + groupSize);
        System.out.println("k:" + k);
        //
        cursor = head;
        ListNode finalHead = null;
        ListNode lastTail = null;
        for (int i = 0; i < groupSize; i++) {
            if (i < groupSize - 1) {
                ListNode nextCursor = groupNext(cursor, k);
                if (finalHead == null) {
                    finalHead = reverseGroup(cursor, true, groupNext(cursor, k), k);
                    lastTail = groupNext(finalHead, k - 1);
                } else {
                    lastTail.next = reverseGroup(cursor, true, groupNext(cursor, k), k);
                    lastTail = groupNext(lastTail.next, k - 1);
                }
                cursor = nextCursor;
            }
            if (i == groupSize - 1) {
                lastTail.next = reverseGroup(cursor, false, null, k);
            }
        }
        return finalHead;
    }

    public ListNode groupNext(ListNode head, int groupLen) {
        ListNode cursor = head;
        while (groupLen >= 1) {
            groupLen -= 1;
            cursor = cursor.next;
        }
        return cursor;
    }

    /*
     * 1234
     * head -> 1
     * 2.next -> head
     * 1.next -> 2.next
     * tail -> 1
     * 3.next -> head
     * */
    public ListNode reverseGroup(ListNode head, boolean isDoReverse, ListNode next, int groupLen) {
        if (isDoReverse == false) {
            return head;
        }
        ListNode groupHead = head;
        ListNode groupCursor = head;
        while (groupLen > 1) {
            groupLen -= 1;
            ListNode nextCursor = groupCursor.next.next;
            groupCursor.next.next = groupHead;
            groupHead = groupCursor.next;
            groupCursor.next = nextCursor;
        }
        groupCursor.next = next;
        return groupHead;
    }
}
