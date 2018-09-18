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
    char[] a = new char[n];  // dth column character array
    int[] aux = new int[n];
    for (int i = 0; i < n; i++) {
      key[i] = i; // initiate index
    }    
    sortByLoop(s, a, 0, n - 1, 0, aux);
  }
  
  // loop version, base on MSD algorithm
  private void sortByLoop(String s, char[] a, int lo, int hi, int d, int[] aux) {
    List<int[]> track = new LinkedList<int[]>();
    int[] item = new int[3];
    item[0] = lo;
    item[1] = hi;
    item[2] = d;
    track.add(item);
    while (!track.isEmpty()) {      
      int[] e = track.remove(0);
      lo = e[0];
      hi = e[1];
      d = e[2];
      if (d > n - 1) 
        return;
      if (hi <= lo + CUTOFF) {
        insertionSort(s, lo, hi, d);
        continue;
      }    
      
      // build the dth column character array
      for (int i = lo; i <= hi; i++) {
        char c = charAt(s, d, key[i]);
        a[i] = c;
      }
      
      // compute frequency counts
      int[] count = new int[R + 2];
      for (int i = lo; i <= hi; i++) {
        char c = charAt(s, d, key[i]);
        count[c + 2]++ ;
      }
      d++;
      
      // transform counts to indices
      for (int r = 0; r < R + 1; r++) {
        count[r + 1] += count[r];
      }
     
      // distribute
      for (int i = lo; i <= hi; i++) {
        int c = a[i];
        aux[count[c + 1]++] = key[i];
      }  
     
      // update 
      for (int i = lo; i <= hi; i++) {
        key[i] = aux[i - lo];
      }      
      
      // record lo and hi
      for (int r = 0; r < R + 1; r++) {
        int l = lo + count[r];
        int h = lo + count[r + 1] - 1;
        if (h <= l)
          continue;
        item = new int[3];
        item[0] = l;
        item[1] = h;
        item[2] = d;
        track.add(item);
      }         
    }   
  }
  
  // recursively version
  private void sort(String s, char[] a, int lo, int hi, int d, int[] aux) {
    if (d > n - 1) 
      return;
    if (hi <= lo + CUTOFF) {
      insertionSort(s, lo, hi, d);
      return;
    }
    
    // build the dth column character array
    for (int i = lo; i <= hi; i++) {
      char c = charAt(s, d, key[i]);
      a[i] = c;
    }
    
    // compute frequency counts
    int[] count = new int[R + 2];
    for (int i = lo; i <= hi; i++) {
      char c = charAt(s, d, key[i]);
      count[c + 2]++ ;
    }
    
    // transform counts to indices
    for (int r = 0; r < R + 1; r++) {
      count[r + 1] += count[r];
    }
    
    // distribute
    for (int i = lo; i <= hi; i++) {
      int c = a[i];
      aux[count[c + 1]++] = key[i];
    }
    
    // update 
    for (int i = lo; i <= hi; i++) {
      key[i] = aux[i - lo];
    }
    
    // recursively
    for (int r = 0; r < R; r++) {
      sort(s, a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
    }
  } 
  
  //get a char from input string
  private char charAt(String s, int d, int shift) {
    assert d >= 0 && d <= n - 1;
    assert shift >= 0 && shift <= n - 1;
   
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
