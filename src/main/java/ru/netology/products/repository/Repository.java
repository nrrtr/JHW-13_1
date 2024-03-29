package ru.netology.products.repository;

import ru.netology.products.Product;
import ru.netology.products.exceptions.AlreadyExistsException;
import ru.netology.products.exceptions.NotFoundException;

public class Repository {
    private Product[] storage = new Product[0];

    public Product[] getStorage() {
        return storage;
    }

    public void setStorage(Product[] storage) {
        this.storage = storage;
    }

    public void save(Product inputProduct) throws RuntimeException {
        if (findById(inputProduct.getId()) != null) {
            throw new AlreadyExistsException(
                    "Продукт с id: " + inputProduct.getId() + " уже существует"
            );
        } else {
            Product[] tmp = new Product[storage.length + 1];
            System.arraycopy(storage, 0, tmp, 0, storage.length);
            tmp[tmp.length - 1] = inputProduct;
            storage = tmp;
        }
    }

    public Product[] findAll() {
        return getStorage();
    }

    public void removeById(int valueOfId) throws RuntimeException {
        if ((storage.length == 0) || (findById(valueOfId) == null)) {
            throw new NotFoundException("Продукт с id:" + valueOfId + " не найден");
        } else {
            Product[] tmp = new Product[storage.length - 1];
            int tmpIndex = 0;

            for (int i = 0; i < storage.length; i++) {
                if (storage[i].getId() != valueOfId) {
                    tmp[tmpIndex] = storage[i];
                    tmpIndex++;
                }
            }
            setStorage(tmp);
        }
    }

    public Product findById(int inputValueOfId) {
        for (Product product : this.getStorage()) {
            if (product.getId() == inputValueOfId) {
                return product;
            }
        }
        return null;
    }
}
