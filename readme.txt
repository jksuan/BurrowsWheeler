..\bin>java BurrowsWheeler - < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.txt
..\bin>java BurrowsWheeler - < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.txt > ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.bwt
..\bin>java MoveToFront - < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.txt > ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.mtf
..\bin>java edu.princeton.cs.algs4.Huffman - < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.txt > ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.huf
..\bin>java BurrowsWheeler - < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.txt | java MoveToFront - | java edu.princeton.cs.algs4.Huffman - > ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.bwt.mtf.huf
..\bin>java edu.princeton.cs.algs4.Huffman + < ..\assignment5_burrows\burrows-testing\mytest\HarryPotter5.bwt.mtf.huf | java MoveToFront + | java BurrowsWheeler + > ..\assignment5_burrows\burrows-testing\mytest\HarryPotter.txt
