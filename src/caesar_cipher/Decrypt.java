/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesar_cipher;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Youssef abo el makarem 136650
 */
public class Decrypt {

    public ENG_Words dict;
    final static String filePath = "words1.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
Cesar used =new Cesar();
    public Decrypt(Cesar n) {
        used=n;
        dict = new ENG_Words();
        dict.Create(filePath);
    }

    public char[][] BruteForce(String Ciphertext) {//function returns 26 possible texts 
        String temp1 = Ciphertext.replaceAll("\\s", "");
        char[] ci = temp1.toLowerCase().toCharArray();
        char[][] a26 = new char[26][ci.length];
        char[] text = new char[ci.length];
        int[] temp = new int[ci.length];
        int index = 0;

        for (int j = 1; j <= 26; j++) {
    
            for (int i = 0; i < ci.length; i++) {

                index = ci[i] - j;
                if (index > 122) {
                    index %= 26;
                    index += 97;
                } else if (index < 97) {
                    index += 26;
                }
                temp[i] = index;
                text[i] = (char) temp[i];

            }
            a26[j - 1] = text;
            text = new char[ci.length];
     
        }

        return a26;

    }

    public void Dictionary_Attack(String Ciphertext) {
        String[] a26 = new String[26];
        int score = 0;
        int start = 0;
        int s2 = 0;
        String[][] words = new String[26][15];
        for (int L = 0; L < 26; L++) {
            a26[L] = String.copyValueOf(BruteForce(Ciphertext)[L]);
       
        }
        for (int k = 0; k < 26; k++) {
            start = 0;
            score = 0;
            s2 = 0;
            for (int i = a26[k].length(); i >= start; i--) {


                if (dict.CheckWord(a26[k].substring(start, i))) {
                    words[k][score] = a26[k].substring(start, i);
                    start = i;
                    i = a26[k].length() + 1;
                    score++;

                }

                if (score >= 4) {
                    used.Key.setText(Integer.toString(k+1));
                    used.Plain.setText(a26[k]);

                    return;
                }
                if (i == start && s2 < 5) {
                    start += 1;
                    i = a26[k].length();
                    s2++;
                if (score > 0 && i == start && dict.CheckWord(a26[k].substring(start, i - 1))) {
                        --start;
                    }
                }
            }

        }

    }

}
