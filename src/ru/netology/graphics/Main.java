package ru.netology.graphics;


import ru.netology.graphics.image.ConverterMy;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;


public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new ConverterMy();

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
        converter.setMaxRatio(2);


//         Или то же, но с выводом на экран:
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String url1 = "https://flomaster.club/uploads/posts/2021-12/thumbs/1638916477_2-flomaster-club-p-peizazhi-dlya-srisovki-krasivie-risunki-2.jpg";
//        String url2 = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        String imgTxt = converter.convert(url2);
//        System.out.println(imgTxt);
    }
}

