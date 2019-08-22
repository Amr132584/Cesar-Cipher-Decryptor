/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesar_cipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Youssef abo el makarem 136650
public class ENG_Words {

     int NUM = 1319; //prime number
     public LinkedList[] array;

    public ENG_Words() {
       

        array = new LinkedList[NUM];
        for (int i = 0; i < NUM; i++) {
            array[i] = new LinkedList();
        }
    }

    private int hash(String key) {
        return (key.hashCode() & 2147483647) % NUM;
    }

    //call hash() to decide which bucket to put it in, do it.
    public void add(String key) {
        array[hash(key)].put(key);
    }

    //call hash() to find what bucket it's in, get it from that bucket. 
    public boolean CheckWord(String input) {
        input = input.toLowerCase();
        return array[hash(input)].get(input);
    }

    public void Create(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                add(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    class LinkedList {

        public Node first;

        public boolean get(String in) {         //return key true if key exists
            Node next = first;
            
            while (next != null) {
                if (next.word.equals(in)) {
                    return true;
                }
                next = next.next;
            }
            return false;
        }

        public void put(String key) {
            for (Node curr = first; curr != null; curr = curr.next) {
                if (key.equals(curr.word)) {
                    return;                     //search hit: return
                }
            }
            first = new Node(key, first); //search miss: add new node
        }

        class Node {

           public String word;
           public Node next;

            public Node(String key, Node next) {
                this.word = key;
                this.next = next;
            }

        }

    }
}
