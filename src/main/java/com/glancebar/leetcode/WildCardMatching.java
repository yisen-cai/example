package com.glancebar.leetcode;

/**
 * https://leetcode.cn/problems/wildcard-matching/
 */
public abstract class WildCardMatching {

    private static final Character QUESTION_MARK = '?';
    private static final Character WILD_CARD_MARK = '*';

    public static boolean isMatch(String s, String p) {
        int sInd = 0, pInd = 0;
        boolean prevWild = false;
        while (sInd < s.length() && pInd < p.length()) {
            char sChar = s.charAt(sInd);
            char pChar = p.charAt(pInd);
            // 标记值
            char pNext = pChar;
            if (prevWild && pInd + 1 < p.length()) {
                pNext = p.charAt(pInd + 1);
            }
            if (sChar != pChar && pChar != QUESTION_MARK && pChar != WILD_CARD_MARK) {
                return false;
            } else if (pChar == WILD_CARD_MARK && !prevWild) {
                sInd++;
                prevWild = true;
            } else if (prevWild && (sChar == pNext || pNext == WILD_CARD_MARK) ) {
                prevWild = false;
            } else {
                sInd++;
                pInd++;
            }
        }
        return sInd == s.length();
    }
}
