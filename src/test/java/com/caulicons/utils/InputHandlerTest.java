package com.caulicons.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InputHandlerTest {

  @Mock
  private Scanner mockScanner;

  private InputHandler input;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    input = new InputHandler(mockScanner);
  }

  @Test
  void testReadCPF_ValidCPF() {

    when(mockScanner.nextLine()).thenReturn("123.456.789-00");

    String result = input.readCPF("Enter CPF: ");

    assertEquals("123.456.789-00", result);
  }

  @Test
  void testReadCPF_InvalidCPF() {
    when(mockScanner.nextLine()).thenReturn("12345678900", "123.456.789-00");

    String result = input.readCPF("Enter CPF: ");

    assertEquals("123.456.789-00", result);
  }

  @Test
  void testReadCPF_EmptyInput() {

    when(mockScanner.nextLine()).thenReturn("", "123.456.789-00");

    String result = input.readCPF("Enter CPF: ");

    assertEquals("123.456.789-00", result);
  }

}
