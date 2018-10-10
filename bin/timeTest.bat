::@echo off
echo %time%
java BurrowsWheeler - < ..\assignment5_burrows\burrows-testing\mytest\bitmap3.bmp | java MoveToFront - | java edu.princeton.cs.algs4.Huffman - > ..\assignment5_burrows\burrows-testing\mytest\bitmap3_3StringQsort.bmp.bwt.mtf.huf
::java edu.princeton.cs.algs4.Huffman + < ..\assignment5_burrows\burrows-testing\mytest\bitmap3_ManberMyers.bmp.bwt.mtf.huf | java MoveToFront + | java BurrowsWheeler + > ..\assignment5_burrows\burrows-testing\mytest\bitmap3X.bmp

::java BurrowsWheeler - < ..\assignment5_burrows\burrows-testing\mytest\Manber.txt | java MoveToFront - | java edu.princeton.cs.algs4.Huffman - > ..\assignment5_burrows\burrows-testing\mytest\Manber_ManberMyers.txt.bwt.mtf.huf
::java edu.princeton.cs.algs4.Huffman + < ..\assignment5_burrows\burrows-testing\mytest\Manber_ManberMyers.txt.bwt.mtf.huf | java MoveToFront + | java BurrowsWheeler + > ..\assignment5_burrows\burrows-testing\mytest\ManberX.txt
echo %time%