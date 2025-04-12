package com.lab3.bankmanagementsystem.models;

import com.lab3.bankmanagementsystem.util.errors.NotFoundException;

public class TransactionHistory {
  private Node tail;
  private int size;

  public TransactionHistory() {
    size = 0;

    setTail(null);
  }

  public void add(Transaction trans) {
    Node node = new Node(trans);

    node.setPrevious(tail);
    setTail(node);
    incrementSize();
  }

  public int getSize() {
    return size;
  }

  public Transaction[] getMany(int num) {
    if (tail == null)
      throw new NotFoundException("No transactions found");

    Transaction[] recentTransactions = new Transaction[num];
    Node current = tail;

    for (int i = 0; current != null && i < num; i++) {
      recentTransactions[i] = current.getData();
      current = current.getPrevious();
    }
    return recentTransactions;
  }

  private void setTail(Node node) {
    tail = node;
  }

  private void incrementSize() {
    size++;
  }

  private static class Node {
    private final Transaction data;
    private Node previous;

    public Node(Transaction data) {
      this.data = data;
      setPrevious(null);
    }

    public void  setPrevious(Node previous) {
      this.previous = previous;
    }

    public Transaction getData() {
      return data;
    }

    public Node getPrevious() {
      return previous;
    }
  }
}
