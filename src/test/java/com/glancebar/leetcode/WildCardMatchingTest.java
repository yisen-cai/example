package com.glancebar.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WildCardMatchingTest {
    @Test
    void isMatchABC() {
        Assertions.assertTrue(WildCardMatching.isMatch("abc", "abc"));
    }


    @Test
    void isMatch() {
        Assertions.assertFalse(WildCardMatching.isMatch("aa", "a"));
    }

    @Test
    void isMatchOne() {
        Assertions.assertTrue(WildCardMatching.isMatch("aa", "a?"));
    }

    @Test
    void isMatchWildOne() {
        Assertions.assertTrue(WildCardMatching.isMatch("aa", "a*"));
    }

    @Test
    void isMatchWildTwo() {
        Assertions.assertTrue(WildCardMatching.isMatch("aaa", "a*"));
    }


    @Test
    void isMatchWildFirst() {
        Assertions.assertTrue(WildCardMatching.isMatch("cb", "?b"));
        Assertions.assertFalse(WildCardMatching.isMatch("cb", "?a"));
    }

    @Test
    void isMatchWild() {
        Assertions.assertFalse(WildCardMatching.isMatch("acdcb", "a*c?b"));
    }

    @Test
    void isMatchEmpty() {
        Assertions.assertTrue(WildCardMatching.isMatch("adceb", "*a*b"));
    }
}