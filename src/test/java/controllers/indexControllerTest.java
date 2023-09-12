package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class indexControllerTest {

    @Test
    void index() {
        // mock the indexController
        indexController indexController = new indexController();
        // test the index method
        assertEquals("index", indexController.index());
    }
}