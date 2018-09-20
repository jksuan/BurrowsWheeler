import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import edu.princeton.cs.algs4.BinaryStdIn;

public class CircularSuffixArray {
  
  private static final int R      = 256; // alphabet size of extended ASCII
  private static final int CUTOFF =  15;  // cutoff to insertion sort
  
  private int n;
  private int[] key; // index
  
  /**
   * Circular suffix array of s
   * 
   * @param <code>s</code> - input string 
   */
  public CircularSuffixArray(String s) {
    if (s == null) throw new IllegalArgumentException("Input string must be not null");
    n = s.length();
    key = new int[n];
    // char[] a = new char[n];  // dth column character array
    for (int i = 0; i < n; i++) {
      key[i] = i; // initiate index
    }    
    sort(s, 0, n - 1, 0);
  }
    
  // recursively version
  private void sort(String s, int lo, int hi, int d) {
    if (d > n - 1) 
      return;
    if (hi <= lo + CUTOFF) {
      insertionSort(s, lo, hi, d);
      return;
    }
    
    int lt = lo, gt = hi;
    int v = charAt(s, d, key[lt]);
    int i = lo + 1;
    while (i <= gt) {
      int t = charAt(s, d, key[i]);
      if (t < v) 
        exch(lt++, i++);
      else if (t > v) 
        exch(i, gt--);
      else              
        i++;
    }

    // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
    sort(s, lo, lt-1, d);
    sort(s, lt, gt, d+1);
    sort(s, gt+1, hi, d);
  } 
  
  //get a char from input string
  private char charAt(String s, int d, int shift) {
    int i = (d + shift) % n;
    return s.charAt(i);
  }
   
  // insertion sort a[lo..hi], starting at character d
  // do not sort a[lo..hi] explicitly
  private void insertionSort(String s, int lo, int hi, int d) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(s, j, j-1, d); j--)
        exch(j, j-1);
  }

  // exchange key[i] and key[j] if a[i] < a[j]
  private void exch(int i, int j) {
    int temp = key[i];
    key[i] = key[j];
    key[j] = temp;
  }

  // is c1 less than c2, starting at character d
  // c1 = a[v], c2 = a[w] at character d
  private boolean less(String s, int v, int w, int d) { 
    for (int i = d; i < length(); i++) {
      char c1 = charAt(s, i, index(v));
      char c2 = charAt(s, i, index(w));
      if (c1 < c2) return true; 
      if (c1 > c2) return false;
    }
    return false;
  }  
  
  /**
   * Return the length of input string
   * 
   * @return <code>n</code> - length of input string
   */
  public int length() {
    return n;
  }
  
  
  /**
   * Returns index of the original suffix that appears <em>ith</em> in the sorted array
   * 
   * @param <code>i</code> the <em>ith</em> sorted array
   * @return index of the original suffix that appears <em>ith</em> in the sorted array
   * @throws IllegalArgumentException if <code>i</code> is not 
   *         between <code>0</code> and <code>length()</code> − <code>1</code>
   */
  public int index(int i) {
    if (i < 0 || i > length() - 1) 
      throw new IllegalArgumentException("Argument i must be between 0 and length() - 1");
    return key[i];
  }
   
  // Unit testing (required)
  public static void main(String[] args) {
    String s = BinaryStdIn.readString();
    CircularSuffixArray suffix = new CircularSuffixArray(s);
    for (int i = 0; i < s.length(); i++) {
      System.out.println(suffix.index(i));
    }
    System.out.println("END");
  }
}
