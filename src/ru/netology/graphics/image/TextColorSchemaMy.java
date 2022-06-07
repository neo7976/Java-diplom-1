package ru.netology.graphics.image;

public class TextColorSchemaMy implements TextColorSchema {

    char[] symbolChar = new char[]{
            '#', '$', '@', '%', '*', '+', '-', '\''
    };

    @Override
    public char convert(int color) {
        return symbolChar[(int) Math.floor(color / 256.0 * symbolChar.length)];

    }
}
