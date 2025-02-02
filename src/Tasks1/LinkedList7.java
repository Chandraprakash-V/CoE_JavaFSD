package Tasks1;
import java.util.*;

class LinkedList7 {
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public boolean hasCycle(Node head) {
        if (head == null) return false;
        
        Node slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            if (i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        LinkedList7 list = new LinkedList7();
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // Creating a cycle

        System.out.println("Cycle detected: " + list.hasCycle(head));
        
        String s = "cbaebabacd", p = "abc";
        System.out.println("Anagram indices: " + findAnagrams(s, p));
    }
}
