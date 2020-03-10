package com.nny.Demo.CollectionLearn;

import java.util.*;

/**
 * List接口
 * 利用Collections.shuffle和List.subList实现扑克牌洗牌发牌功能
 */
public class Deal {

    public static void main(String[] args){

        int numHands = Integer.parseInt("4");
        int cardsPerHand = Integer.parseInt("5");

        // Make a normal 52-card deck.
        String[] suit = new String[] {
                "spades", "hearts", "diamonds", "clubs" };//黑桃 红心 方块 梅花

        String[] rank = new String[] {
                "ace","2","3","4","5","6","7","8",
                "9","10","jack","queen","king" };

        //准备52张牌
        List<String> deck = new ArrayList<String>();

        for (int i = 0; i < suit.length; i++)
            for (int j = 0; j < rank.length; j++)
                deck.add(rank[j] + " of " + suit[i]);

        // 洗牌
        Collections.shuffle(deck);

        if (numHands * cardsPerHand > deck.size()) {
            System.out.println("Not enough cards.");
            return;
        }

        //发牌：发numHands手牌，每手发cardsPerHand张牌，发剩下的牌不用管
        for (int i=0; i < numHands; i++)
            System.out.println(E.dealHand(deck, cardsPerHand));
    }

}

