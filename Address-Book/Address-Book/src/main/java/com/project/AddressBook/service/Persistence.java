package com.project.AddressBook.service;

public interface Persistence {
    void saveToFile(String fileName);
    void loadFromFile(String fileName);
}

