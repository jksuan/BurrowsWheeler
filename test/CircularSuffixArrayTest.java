import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class CircularSuffixArrayTest {

  @Test
  public void index() {
    String s = "ABRACADABRA!"; // AAAAAAAAAAA!      BinaryStdIn.readString();
    Stopwatch sw = new Stopwatch();
    CircularSuffixArray suffix = new CircularSuffixArray(s);
    StdOut.println("Elapsed time: " + sw.elapsedTime());
    assertEquals(11, suffix.index(0));
    assertEquals(10, suffix.index(1));
    assertEquals(7, suffix.index(2));
    assertEquals(0, suffix.index(3));
    assertEquals(3, suffix.index(4));
    assertEquals(5, suffix.index(5));
    assertEquals(8, suffix.index(6));
    assertEquals(1, suffix.index(7));
    assertEquals(4, suffix.index(8));
    assertEquals(6, suffix.index(9));
    assertEquals(9, suffix.index(10));
    assertEquals(2, suffix.index(11)); 
  }

}
