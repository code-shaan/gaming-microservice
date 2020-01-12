package com.example.ec.domain;

public enum BestPlayedAt {
    Two("Two"), Three("Three"), Two_To_Four("Two_To_Four"), Three_To_five("Three_To_five"), Varies("Varies");
    private String groupSize;
    private BestPlayedAt(String groupSize) {
        this.groupSize = groupSize;
    }
    public static BestPlayedAt findByGroupSize(String byGroupSize) {
        for(BestPlayedAt bestAt: BestPlayedAt.values()) {
            if (bestAt.groupSize.equalsIgnoreCase(byGroupSize))
                return bestAt;
        }
        return null;
    }
}
