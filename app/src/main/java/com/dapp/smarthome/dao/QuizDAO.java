package com.dapp.smarthome.dao;

import com.google.firebase.database.DataSnapshot;

public class QuizDAO {
    public String key;
    public String A,B,C,D, ans, question;

    public QuizDAO(String key, String a, String b, String c, String d, String ans, String question) {
        this.key = key;
        A = a;
        B = b;
        C = c;
        D = d;
        this.ans = ans;
        this.question = question;
    }

    public QuizDAO(String key, DataSnapshot snapshot) {
        this.key = key;
        this.A = snapshot.child("A").getValue() + "";
        this.B = snapshot.child("B").getValue() + "";
        this.C = snapshot.child("C").getValue() + "";
        this.D = snapshot.child("D").getValue() + "";
        this.ans = snapshot.child("answer").getValue() + "";
        this.question = snapshot.child("question").getValue() + "";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
