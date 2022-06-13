package ru.netology.products;

public class Book extends Product {
    private String author;


    public Book() {
        super();
    }

    public Book(int id, String name, int cost, String author) {
        super(id, name, cost);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean matches(String search) {
        if (!super.matches(search)) {
            return this.getAuthor().contains(search);
        } else {
            return true;
        }
    }
}
