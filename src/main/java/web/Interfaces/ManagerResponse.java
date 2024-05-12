package web.Interfaces;

public abstract class ManagerResponse {
    public abstract String buildResponse(String command, String postTitle, String commentTitle, String userName);
    }

    // врмененаая реализация для одной строки
    // нужно определить что передать в этот метод
    // при использований такого метода развилки для трех функциональностей необхзодимо
    // добавлять новый абстрактный класс, либо же использовать rest or JSON

