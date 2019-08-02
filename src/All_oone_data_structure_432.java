import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class All_oone_data_structure_432 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class AllOne {

    private static class Node {
        Node prev;
        Node next;
        int value;
        Set<String> keys = new LinkedHashSet();

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (this.prev == null) {
                Node elem = this;
                while (elem != null) {
                    sb.append("{" + elem.value + ": " + elem.keys + "} ");
                    elem = elem.next;
                }
            } else if (this.next == null) {
                Node elem = this;
                while (elem != null) {
                    sb.append("{" + elem.value + ": " + elem.keys + "} ");
                    elem = elem.prev;
                }
            }

            return sb.toString();
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node head;
    Node tail;

    /** Initialize your data structure here. */
    public AllOne() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = map.get(key);
        if (node == null) {
            if (head == null || head.value > 1) {
                node = new Node();
                node.value = 1;
                node.keys.add(key);
                node.prev = null;

                if (head == null) {
                    tail = node;
                } else {
                    head.prev = node;
                    node.next = head;
                }
                head = node;
            } else { //head.value == 1
                head.keys.add(key);
            }
            map.put(key, head);
        } else {
            if (node == tail) {
                if (node.keys.size() == 1) {
                    node.value++;
                } else {
                    node.keys.remove(key);
                    Node node1 = addNextNode(node);
                    node1.keys.add(key);
                    map.put(key, node1);
                    tail = node1;
                }
            } else if (node.next.value == node.value + 1) {
                node.next.keys.add(key);
                map.put(key, node.next);

                if (node.keys.size() == 1) {
                    node.next.prev = node.prev;
                    if (head == node) {
                        head = node.next;
                    } else {
                        node.prev.next = node.next;
                    }
                } else {
                    node.keys.remove(key);
                }
            } else if (node.keys.size() == 1) {
                node.value++;
            } else {
                node.keys.remove(key);
                Node node1 = addNextNode(node);
                node1.keys.add(key);
                map.put(key, node1);
            }
        }
    }

    private Node addNextNode(Node node) {
        Node node1 = new Node();
        node1.value = node.value + 1;
        node1.prev = node;
        node1.next = node.next;
        node.next = node1;

        if (node1.next != null) {
            node1.next.prev = node1;
        }

        return node1;
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = map.get(key);
        if (node == null) {
            return;
        }

        if (node.value == 1) {
            if (node.keys.size() == 1) {
                remove(node);
            } else {
                node.keys.remove(key);
            }
            map.remove(key);
        } else if (node == head) {
            if (node.keys.size() == 1) {
                node.value--;
            } else {
                node.keys.remove(key);
                Node node1 = addPreviosNode(node);
                node1.keys.add(key);
                map.put(key, node1);
                head = node1;
            }
        } else if (node.prev.value == node.value - 1) {
            node.prev.keys.add(key);
            map.put(key, node.prev);
            if (node.keys.size() == 1) {
                remove(node);
            } else {
                node.keys.remove(key);
            }
        } else {
            //
            if (node.keys.size() == 1) {
                node.value--;
            } else {
                node.keys.remove(key);
                Node node1 = addPreviosNode(node);
                node1.keys.add(key);
                map.put(key, node1);
            }
        }
    }

    private Node addPreviosNode(Node node) {
        Node node1 = new Node();
        node1.value = node.value - 1;
        node1.prev = node.prev;
        node1.next = node;
        node.prev = node1;

        if (node1.prev != null) {
            node1.prev.next = node1;
        }

        return node1;
    }

    private void remove(Node node) {
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        if (node == head) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }


    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail == null ? "" : tail.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head == null ? "" : head.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */