package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("도서"), FOOD("식품"), ETC("기타");
    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


//final static string 과 같은 방식으로 상수를 정의