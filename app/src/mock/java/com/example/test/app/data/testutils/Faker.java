package com.example.test.app.data.testutils;

import java.util.Random;

public class Faker {
    private static String[] fakeString = {
            "random text",
            "lorem ipsum",
            "nobody is perfect",
            "lorem ipsum is good for testing",
            "some long text is actually needed!"};

    private static String[] imageUrl = {
            "https://via.placeholder.com/600/56a8c2",
            "https://via.placeholder.com/600/51aa97",
            "https://via.placeholder.com/600/1ee8a4",
            "https://via.placeholder.com/600/66b7d2",
            "https://via.placeholder.com/600/197d29"
    };

    private static String[] thumbnailUrl = {
            "https://via.placeholder.com/150/771796",
            "https://via.placeholder.com/150/24f355",
            "https://via.placeholder.com/150/d32776",
            "https://via.placeholder.com/150/f66b97",
            "https://via.placeholder.com/150/56a8c2"
    };

    private static String[] videoUrl = {
            "https://www.radiantmediaplayer.com/media/bbb-360p.mp4",
    };

    private Faker() {
    }

    public static String fakeString() {
        Random random = new Random();
        return fakeString[random.nextInt(fakeString.length)];
    }

    public static String fakeImageUrl() {
        Random random = new Random();
        return imageUrl[random.nextInt(imageUrl.length)];
    }

    public static String fakeThumbnail() {
        Random random = new Random();
        return thumbnailUrl[random.nextInt(thumbnailUrl.length)];
    }

    public static String fakeVideoUrl() {
        Random random = new Random();
        return videoUrl[0];
    }
}
